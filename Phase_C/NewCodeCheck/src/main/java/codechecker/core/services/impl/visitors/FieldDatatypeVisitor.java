package codechecker.core.services.impl.visitors;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class FieldDatatypeVisitor extends VoidVisitorAdapter<Void> {
    /*
     * Function to visit all the fields declarations in the submitted java program
     */
    @Override
    public void visit(FieldDeclaration n, Void arg) {
        List<VariableDeclarator> myVars = n.getVariables(); //get all the variables declared.
        for (VariableDeclarator vars: myVars){
            vars.setType("T"); // Set the datatype of the variables to T
        }
    }
}
