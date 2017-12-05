package codechecker.core.services.impl.visitors;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class VariableDatatypeVisitor extends VoidVisitorAdapter<Void> {
    /*
     * Function to visit all the variable declaration expressions in the submitted java program
     */
    @Override
    public void visit(VariableDeclarationExpr n, Void arg) {
        List<VariableDeclarator> myVars = n.getVariables(); // get the variables declared
        for (VariableDeclarator vars: myVars){
            vars.setType("T"); // Set the datatype of variables to T
        }
        super.visit(n, arg);
    }

}
