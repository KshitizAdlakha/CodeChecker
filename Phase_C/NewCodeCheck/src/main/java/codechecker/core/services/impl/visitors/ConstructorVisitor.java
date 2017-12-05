package codechecker.core.services.impl.visitors;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ConstructorVisitor extends VoidVisitorAdapter<Void> {
    /*
     *  Function to visit all the constructors in the submitted java program
     */
    @Override
    public void visit(ConstructorDeclaration cd, Void arg) {
        for(Parameter p : cd.getParameters()) {
            p.setType("T"); // Set the datatype of the parameters of the constructor as T
        }
        super.visit(cd, arg);
    }
}
