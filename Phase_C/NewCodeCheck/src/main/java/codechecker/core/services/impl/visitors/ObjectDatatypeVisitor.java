package codechecker.core.services.impl.visitors;

import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class ObjectDatatypeVisitor extends VoidVisitorAdapter<Void> {
    @Override
    public void visit(ObjectCreationExpr n, Void arg) {
        n.setType("T");
        super.visit(n,arg);
    }
}
