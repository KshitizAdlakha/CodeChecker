package codechecker.core.services.impl.visitors;

import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class ObjectDatatypeVisitor extends VoidVisitorAdapter<Void> {
//    Function to visit all the Object Creation Expressions in the submitted java program
    @Override
    public void visit(ObjectCreationExpr n, Void arg) {
        n.setType("T"); // Set the datatype of the object as T.
        super.visit(n,arg);
    }
}
