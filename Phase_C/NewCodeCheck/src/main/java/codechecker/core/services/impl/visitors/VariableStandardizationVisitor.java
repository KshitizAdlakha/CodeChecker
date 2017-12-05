package codechecker.core.services.impl.visitors;

import java.io.File;
import java.util.Collections;
import java.util.Hashtable;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;

//This class visits all the nodes in the AST and renames the variables
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
     * Function to visit each of the child nodes of an AST
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
    		replaceVariable(target);
    		
    		//Change this to a visitor 
    		Node valueExpr = ((AssignExpr) n).getValue();
    		replaceVariable(valueExpr);
    		String x = "6";
    	}
    	
    	else if(n instanceof UnaryExpr) {
    		UnaryExpr unary = ((UnaryExpr) n);
    		Node operand = unary.getExpression();
    		replaceVariable(operand);
    	}
    	else if(n instanceof BinaryExpr) {
    		BinaryExpr binary = ((BinaryExpr) n);
    		Node leftSide = binary.getLeft();
    		Node rightSide = binary.getRight();
    		replaceVariable(leftSide);
    		replaceVariable(rightSide);
    	}
    	else {
	        for(Node child : n.getChildNodes()) {
	            visit(child, arg);
	        }
    	}
    }

	/*
     * Function to replace the variable with a named expression
     */
    private void replaceVariable(Node e) {
		if(e instanceof NameExpr) {
			NameExpr ne = (NameExpr)e;
			System.out.println(ne);
			ne.setName(replacementMap.get(ne.getName()));
		}
    }

}
