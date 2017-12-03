package codechecker.core.services.impl;

import java.util.Hashtable;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

//This class visits all the nodes in the AST and removes the nodes identified as comments
public class VariableStandardizationVisitor extends VoidVisitorAdapter<Void> {

	private static final String VARIABLE_PREFIX = "v";
	private int variableCount = 0;
	private Hashtable<SimpleName, SimpleName> replacementMap =
			new Hashtable<SimpleName, SimpleName>();
	
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
     * Function to visit the child nodes of the given node recursively and 
     * standardize the variable names
     */
    public void visit(Node n, Void arg) {
    	
    	/*
    	 * If this is an initialization or a declaration,
    	 * replace the variable being set with a standard value.
    	 */
    	if(n instanceof VariableDeclarationExpr) {
    		
    		VariableDeclarator vd = ((VariableDeclarationExpr) n).getVariables().get(0);
    		SimpleName newName = getNextVariable();
    		SimpleName oldName = vd.getName();

	    	replacementMap.put(oldName, newName);
    		
    		//Replace the variable name
    		vd.setName(newName);
    		
    	}
    	else if(n instanceof AssignExpr) {
    		Node target = ((AssignExpr) n).getTarget();
    		if(target instanceof NameExpr) {
    			NameExpr ne = (NameExpr)target;
    			ne.setName(replacementMap.get(ne.getName()));
    		}
    		String x = "6";
    	}
    	else {
	        for(Node child : n.getChildNodes()) {
	            visit(child, arg);
	        }
    	}
    }
}
