package codechecker.core.services.impl;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

//This class visits all the nodes in the AST and removes the nodes identified as comments
public class VariableStandardizationVisitor extends VoidVisitorAdapter<Void> {

	private static final String VARIABLE_PREFIX = "v";
	private int variableCount = 0;
	
	private SimpleName getNextVariable() {
		SimpleName nextName = new SimpleName(VARIABLE_PREFIX+variableCount);
		variableCount++;
		return nextName;
	}
    /*
     * Function to visit the nodes of the AST and remove the nodes identified as comments
     */
    @Override
    public void visit(CompilationUnit n, Void arg) {
        for(Node child : n.getChildNodes()) {
            visit(child, arg);
        }
    }

    /*
     * Function to visit the child nodes of the given node recursively and remove the nodes identified as comments
     */
    public void visit(Node n, Void arg) {
    	if(n instanceof VariableDeclarationExpr) {
    		SimpleName nn = getNextVariable();
    		((VariableDeclarationExpr) n).getVariables().get(0).setName(nn);
    		nn = null;
    	}
        for(Node child : n.getChildNodes()) {
            visit(child, arg);
        }
    }
}
