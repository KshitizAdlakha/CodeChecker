package codechecker.core.services.impl.visitors;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Sorts the function nameson the basis of number of their return type, number of parameters and child nodes
 */
public class FunctionOrderingVisitor extends VoidVisitorAdapter<Void> {
    @Override
    public void visit(CompilationUnit cu, Void arg) {

        NodeList<TypeDeclaration<?>> types = cu.getTypes();

        for (TypeDeclaration<?> type : types) {
            visit(type, arg);
        }
    }

    private void visit(TypeDeclaration type, Void arg){
        /**
         * Get all the methods contained in the class
         */
        List<MethodDeclaration> methodDeclarations = type.getMethods();

        List<MethodDeclaration> temp = new ArrayList<MethodDeclaration>(methodDeclarations);

        /**
         * Sorts on the number of child nodes contained
         */
        Collections.sort(temp, new Comparator<MethodDeclaration>() {
            @Override
            public int compare(MethodDeclaration o1, MethodDeclaration o2) {
                int o1VariableSize =0;
                int o2VariableSize =0;
                for(Node p: o1.getChildNodes()){
                    if(p instanceof VariableDeclarationExpr){
                        o1VariableSize++;
                    }
                }

                for(Node p: o2.getChildNodes()){
                    if(p instanceof VariableDeclarationExpr){
                        o2VariableSize++;
                    }
                }
                if(o1VariableSize > o2VariableSize)
                    return 1;
                else {
                    if(o1VariableSize == o2VariableSize)
                        return 0;
                    else return -1;
                }
            }
        });

        /**
         * Sort on the basis of number of function parameters
         */

        Collections.sort(temp, new Comparator<MethodDeclaration>() {
            @Override
            public int compare(MethodDeclaration o1, MethodDeclaration o2) {
                if(o1.getParameters().size() > o2.getParameters().size())
                    return 1;
                else {
                    if(o1.getParameters().size() == o2.getParameters().size())
                        return 0;
                    else return -1;
                }
            }
        });

        /**
         * Sort on the basis of return type
         */
        Collections.sort(temp, new Comparator<MethodDeclaration>() {
            @Override
            public int compare(MethodDeclaration o1, MethodDeclaration o2) {
                return o1.getType().asString().compareTo(o2.getType().asString());
            }
        });

        for(MethodDeclaration m: methodDeclarations){
            type.remove(m);
        }

        for (MethodDeclaration t: temp){
            type.addMember(t);
        }

        for(Node node : type.getChildNodes()){
            if(node instanceof TypeDeclaration){
                visit((TypeDeclaration) node, arg);
            }
        }
    }

}