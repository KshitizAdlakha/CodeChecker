package codechecker.core.services.impl.visitors;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class VariableDatatypeVisitor extends VoidVisitorAdapter<Void> {
    @Override
    public void visit(VariableDeclarationExpr n, Void arg) {
        List<VariableDeclarator> myVars = n.getVariables();
        for (VariableDeclarator vars: myVars){
            vars.setType("T");
        }
    }
}
