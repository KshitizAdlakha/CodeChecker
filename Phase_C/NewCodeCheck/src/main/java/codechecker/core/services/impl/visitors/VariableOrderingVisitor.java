package codechecker.core.services.impl.visitors;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Visitor class to put the variables at the end of each block statement in a class
 */
public class VariableOrderingVisitor extends VoidVisitorAdapter<Void> {
    @Override
    public void visit(CompilationUnit cu, Void arg) {

        NodeList<TypeDeclaration<?>> types = cu.getTypes();

        for (TypeDeclaration<?> type : types) {
            visit(type, arg);
        }
    }

    private void visit(TypeDeclaration type, Void arg){
        List<BodyDeclaration> bodyDeclarations = type.getMembers();

        List<BodyDeclaration> temp = new ArrayList<BodyDeclaration>(bodyDeclarations);

        Collections.sort(temp, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof FieldDeclaration){
                    if(!(o2 instanceof FieldDeclaration)) return 1;
                    else {
                        String temp1 = ((FieldDeclaration) o1).asFieldDeclaration().toString().split(" ")[1];
                        String temp2 = ((FieldDeclaration) o2).asFieldDeclaration().toString().split(" ")[1];
                        return temp1.compareTo(temp2);
                    }
                } else if (o2 instanceof FieldDeclaration) return -1;
                else return 0;
            }
        });

        for(Object node : type.getMembers()){
            if(node instanceof TypeDeclaration){
                visit((TypeDeclaration) node, arg);
            }
        }

        for(BodyDeclaration m: temp){
            type.remove(m);
        }

        for (BodyDeclaration t: temp){
            type.addMember(t);
        }
    }
}
