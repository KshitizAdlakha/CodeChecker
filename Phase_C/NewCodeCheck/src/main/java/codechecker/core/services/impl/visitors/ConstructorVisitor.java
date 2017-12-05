package codechecker.core.services.impl.visitors;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ConstructorVisitor extends VoidVisitorAdapter<Void> {
    @Override
    public void visit(ConstructorDeclaration cd, Void arg) {
        for(Parameter p : cd.getParameters()) {
            p.setType("T");
        }
        super.visit(cd, arg);
    }
}
