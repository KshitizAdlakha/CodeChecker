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

/*
 * Function Ordering Visitor class
 * extends Void Visitor Adapter
 */
public class FunctionOrderingVisitor extends VoidVisitorAdapter<Void> {
    @Override
    public void visit(CompilationUnit cu, Void arg) {

        NodeList<TypeDeclaration<?>> types = cu.getTypes();

        for (TypeDeclaration<?> type : types) {
            visit(type, arg);
        }
    }

    /*
     * Function to visit all the method declarations in the submitted java program
     */
    private void visit(TypeDeclaration type, Void arg){
        List<MethodDeclaration> methodDeclarations = type.getMethods();

        List<MethodDeclaration> temp = new ArrayList<MethodDeclaration>(methodDeclarations);

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

        Collections.sort(temp, new Comparator<MethodDeclaration>() {
            @Override
            public int compare(MethodDeclaration o1, MethodDeclaration o2) {
                return o1.getType().asString().compareTo(o2.getType().asString());
            }
        });

        /*
        * Function to remove all method declarations
        */
        for(MethodDeclaration m: methodDeclarations){
            type.remove(m);
        }

        /*
         * Function to add method declartations from list
        */
        for (MethodDeclaration t: temp){
            type.addMember(t);
        }

        /*
         * Function to get instance of child nodes from declaration
        */
        for(Node node : type.getChildNodes()){
            if(node instanceof TypeDeclaration){
                visit((TypeDeclaration) node, arg);
            }
        }
    }

}