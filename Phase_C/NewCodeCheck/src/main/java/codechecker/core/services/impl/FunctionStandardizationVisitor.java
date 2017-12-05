package codechecker.core.services.impl;

import java.io.File;
import java.util.Hashtable;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.expr.UnaryExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;

//This class visits all the nodes in the AST and renames the functions
public class FunctionStandardizationVisitor extends VoidVisitorAdapter<Void> {

	private static final String FUNCTION_PREFIX = "v";
	private int functionCount = 0;
	private Hashtable<SimpleName, SimpleName> replacementMap =
			new Hashtable<SimpleName, SimpleName>();
	
	private SimpleName getNextFunction() {
		SimpleName nextName = new SimpleName(FUNCTION_PREFIX+functionCount);
		functionCount++;
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
    	if(n instanceof MethodDeclaration) {
    		
    		MethodDeclaration md = ((MethodDeclaration) n);
    		SimpleName newName = getNextFunction();
    		SimpleName oldName = md.getName();

	    	replacementMap.put(oldName, newName);
    		
    		//Replace the function name
    		md.setName(newName);
    	}
    	
    	else if(n instanceof MethodCallExpr) {
    		
    		MethodCallExpr mce = ((MethodCallExpr) n);
    		SimpleName newName;
    		SimpleName oldName = mce.getName();
    		
    		if(replacementMap.get(mce.getName()) != null) {
    			newName = replacementMap.get(mce.getName());
    		}
    		
    		else {
    			newName = getNextFunction();
    			replacementMap.put(oldName, newName);
    		}
 
    		//Replace the function name
    		mce.setName(newName);
    		
    	}
    	
        for(Node child : n.getChildNodes()) {
            visit(child, arg);
        }
    }
    
}
