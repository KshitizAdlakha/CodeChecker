package codechecker.core.services.impl.visitors;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodVisitor extends VoidVisitorAdapter<Void> {
    /* Function to visit all the methods in the submitted java program */
    public void visit(MethodDeclaration n, Void arg) {
        n.setType("T"); //Set the return type of the method to T
        for(Parameter p : n.getParameters()){
            p.setType("T"); // Set the type of the parameters of the method to T
        }

        super.visit(n, arg);
    }
}
