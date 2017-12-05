package codechecker.core.services.impl.visitors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.javaparser.Navigator;
import com.github.javaparser.symbolsolver.javaparsermodel.JavaParserFacade;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;

//This class visits all the nodes in the AST and renames the functions, and classes
public class FunctionStandardizationVisitor extends VoidVisitorAdapter<Void> {

	private static final String FUNCTION_PREFIX = "v";
    private static final String CLASS_PREFIX = "c";

    private int functionCount = 0;
    private int classCount = 0;
	private Hashtable<SimpleName, SimpleName> replacementMap =
			new Hashtable<SimpleName, SimpleName>();
	
	private SimpleName getNextFunction() {
		SimpleName nextName = new SimpleName(FUNCTION_PREFIX+functionCount);
		functionCount++;
		return nextName;
	}

	private SimpleName getNextClassName() {
        SimpleName nextName = new SimpleName(CLASS_PREFIX+classCount);
        classCount++;
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

        // rename classes and their constructors to a standard name
    	if(n instanceof ClassOrInterfaceDeclaration){

    	    List<ConstructorDeclaration> constructors = ((ClassOrInterfaceDeclaration) n).getConstructors();

            ClassOrInterfaceDeclaration cd = (ClassOrInterfaceDeclaration) n;
            SimpleName oldName = cd.getName();
            SimpleName newName;
            if(replacementMap.get(oldName)!=null){
                newName = replacementMap.get(oldName);
            } else {
                newName = getNextClassName();
                replacementMap.put(oldName, newName);
            }
            for(ConstructorDeclaration constructorDeclaration: constructors){
                constructorDeclaration.setName(newName);
            }
            cd.setName(newName);
        }

        // rename the package to a standard name
        else if(n instanceof PackageDeclaration){
            PackageDeclaration pd = (PackageDeclaration) n;
            Name sn = new Name("package");
            pd.setName(sn);
        }

    	/*
    	 * If this is an initialization or a declaration,
    	 * replace the variable being set with a standard value.
    	 */
    	else if(n instanceof MethodDeclaration) {
    		
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
