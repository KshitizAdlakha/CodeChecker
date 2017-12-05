package codechecker.core.services.impl.visitors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


//This class visits all the nodes in the AST and renames the variables
public class VariableStandardizationVisitor extends VoidVisitorAdapter<Void> {

	private static final String VARIABLE_PREFIX = "v";
	private int variableCount = 0;
	private Hashtable<SimpleName, SimpleName> replacementMap =
			new Hashtable<SimpleName, SimpleName>();
	
	/*
	 * Returns the next standard variable name to be used.
	 */
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
    	

    	if(n instanceof ConstructorDeclaration){
			List<Node> nodes = n.getChildNodes();
			for (Node node:nodes) {
				if(node instanceof BlockStmt){
//					System.out.println(node);
					visit(node, null);
				}
			}
		}
		else if(n instanceof BlockStmt){
			List<Node> nodes = n.getChildNodes();
			for (Node node:nodes) {
				visit(node, null);
			}
		}
		/*
    	 * If this node is an initialization or a declaration,
    	 * replace the variable being set with a standard value.
    	 */
    	else if(n instanceof VariableDeclarationExpr) {
    		VariableDeclarator vd = ((VariableDeclarationExpr) n).getVariables().get(0);
    		replaceVariableDeclarator(vd);
    		
    	}
    	
    	/*
    	 * If this node is a declarator,
    	 * replace the variable being set with a standard value.
    	 */
    	else if(n instanceof VariableDeclarator) {
    		VariableDeclarator vd = (VariableDeclarator) n;
    		replaceVariableDeclarator(vd);
    		
    	}
    	
    	
    	/*
    	 * If this node is a Parameter,
    	 * replace the variable being set with a standard value.
    	 */
    	else if(n instanceof Parameter) {
    		
    		Parameter p = ((Parameter) n);
    		SimpleName newName = getNextVariable();
    		SimpleName oldName = p.getName();

	    	replacementMap.put(oldName, newName);
    		
    		//Replace the variable name
    		p.setName(newName);
    		
    	}
    	
    	/*
    	 * If this node is an AssignExpr,
    	 * replace the variable being set with a standard value.
    	 */
    	else if(n instanceof AssignExpr) {
    		Node target = ((AssignExpr) n).getTarget();
    		replaceVariable(target);
    		
    		Node valueExpr = ((AssignExpr) n).getValue();
    		replaceVariable(valueExpr);
    	}
    	
    	/*
    	 * If this node is a UnaryExpr,
    	 * replace the variable being set with a standard value.
    	 */
    	else if(n instanceof UnaryExpr) {
    		UnaryExpr unary = ((UnaryExpr) n);
    		Node operand = unary.getExpression();
    		replaceVariable(operand);
    	}
    	
    	/*
    	 * If this node is a BinaryExpr,
    	 * replace both variables with standard values.
    	 */
    	else if(n instanceof BinaryExpr) {
    		BinaryExpr binary = ((BinaryExpr) n);
    		Node leftSide = binary.getLeft();
    		Node rightSide = binary.getRight();
    		replaceVariable(leftSide);
    		replaceVariable(rightSide);
    	}
    	
    	/*
    	 * If this node is a NameExpr,
    	 * replace the variable being set with a standard value.
    	 */
    	else if(n instanceof NameExpr) {
    		replaceVariable(n);
    	}
    	
    	/* If none of the cases above match, visit all the children
    	 * of this Node.
    	 */
    	else {
	        for(Node child : n.getChildNodes()) {
	            visit(child, arg);
	        }
    	}
    }


	/*
     * Function to replace the variable with a named expression
    /**
     * Takes a Node and replaces its NameExpr with a standard one
     * @param e - the Node to be renamed
     */
    private void replaceVariable(Node e) {
//    	System.out.println(e);
//    	System.out.println(e.getClass());
//		System.out.println();
		if(e instanceof NameExpr) {
			NameExpr ne = (NameExpr)e;
			
			//Check to see if this NameExpr has already been encountered
			//and get the corresponding value.
			SimpleName replacementName = replacementMap.get(ne.getName());
			
			//Only replace the NameExpr's value, if there is a non-null 
			//value to replace it.
			if(replacementName != null) {
				ne.setName(replacementName);
			} else {
				ne.setName(getNextVariable());
			}
		} else if(e instanceof IntegerLiteralExpr){
			IntegerLiteralExpr ile = (IntegerLiteralExpr) e;
			ile.setInt(0);
		} else if(e instanceof StringLiteralExpr){
			StringLiteralExpr sle = (StringLiteralExpr) e;
			sle.setString("");
		} else if(e instanceof FieldAccessExpr){
			FieldAccessExpr fae = (FieldAccessExpr) e;
			SimpleName replacementName = replacementMap.get(fae.getName());
			if(replacementName != null) {
				fae.setName(replacementName);
			} else {
				fae.setName(getNextVariable());
			}

			String currentScopeName = fae.getScope().toString();

			SimpleName replacementScopeName = replacementMap.get(currentScopeName);
			if(replacementScopeName==null){
				replacementScopeName=getNextVariable();
			}
			NameExpr ex = new NameExpr(replacementScopeName);

			fae.setScope(ex);
		}
    }
    
    
    /**
     * Replaces a Variable Declarator with the next standard value
     * @param vd - the VariableDeclarator to rename
     */
    private void replaceVariableDeclarator(VariableDeclarator vd) {
    	//The new standard variable name that will be associated with this
    	//VariableDeclarator.
		SimpleName newName = getNextVariable();
		SimpleName oldName = vd.getName();

    	replacementMap.put(oldName, newName);
		
		//Replace the variable name
		vd.setName(newName);
    }
}
