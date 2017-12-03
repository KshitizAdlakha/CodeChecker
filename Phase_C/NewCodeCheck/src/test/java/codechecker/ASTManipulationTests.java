package codechecker;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;

import org.junit.Test;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import codechecker.core.services.impl.CommentRemovalVisitor;
import codechecker.core.services.impl.HashCodeVisitor;
import codechecker.core.services.impl.VariableStandardizationVisitor;

public class ASTManipulationTests {

	/*Checks to see that a test file with its comments removed is identical to the same code
	 * with no comments in it.
	 */
	@Test
	public void commentRemovalTest() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//Test1.java"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//Test1_Comments_Removed.java"));

            // parse the file
            CompilationUnit cu1 = JavaParser.parse(in1);
            CompilationUnit cu2 = JavaParser.parse(in2);

	        /*
	         * Visitors for removal of comments and obtaining the hash codes
	         */
            CommentRemovalVisitor crv1 = new CommentRemovalVisitor();
            HashCodeVisitor hcv1 = new HashCodeVisitor();
            HashCodeVisitor hcv2 = new HashCodeVisitor();

            cu1.accept(crv1, null); //All the nodes in the AST generated from the first submission are visited and the nodes identified as comments are removed
            cu1.accept(hcv1, null); //All the nodes in the AST generated from the first submission are visited.
            cu2.accept(hcv2, null); //All the nodes in the AST generated from the second submission are visited.
            
            HashSet<Integer> a = hcv1.getNodeHashCodes();
            HashSet<Integer> b = hcv2.getNodeHashCodes();

            assertEquals(true, a.equals(b));

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
        	fail("Not yet implemented");
        }
	}
		
        
    	/*
		 * Checks to see if two files, that are identical except for their variable declarations,
		 * are found to match after their variable declarations are standardized.
    	 */
    	@Test
    	public void standardVariableTest1() {
            FileInputStream in1;
            FileInputStream in2;

            try {
                in1 = new FileInputStream(new File("src//test//java//codechecker//VariableDeclarationSample_1.java"));
                in2 = new FileInputStream(new File("src//test//java//codechecker//VariableDeclarationSample_2.java"));

                // parse the file
                CompilationUnit cu1 = JavaParser.parse(in1);
                CompilationUnit cu2 = JavaParser.parse(in2);

    	        /*
    	         * Visitors for removal of comments and obtaining the hash codes
    	         */
                VariableStandardizationVisitor vsv1 = new VariableStandardizationVisitor();
                VariableStandardizationVisitor vsv2 = new VariableStandardizationVisitor();
                HashCodeVisitor hcv1 = new HashCodeVisitor();
                HashCodeVisitor hcv2 = new HashCodeVisitor();

                /*
                 * All the nodes in the AST generated from the first submission are visited and 
                 * the nodes identified as comments are removed.
                 */
                cu1.accept(vsv1, null); 
                /*
                 * All the nodes in the AST generated from the second submission are visited and 
                 * the nodes identified as comments are removed.
                 */
                cu2.accept(vsv2, null);
                /*
                 * Each node in first submission is visited and hashed.
                 */
                cu1.accept(hcv1, null); 
                /*
                 * Each node in second submission is visited and hashed.
                 */
                cu2.accept(hcv2, null);
                
                HashSet<Integer> a = hcv1.getNodeHashCodes();
                HashSet<Integer> b = hcv2.getNodeHashCodes();

                assertEquals(true, a.equals(b));

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
            	fail("Not yet implemented");
            }
	}
    	
    	/*
		 * Checks to see if two files, that are identical except for their variable declarations,
		 * are found to match after their variable declarations are standardized.
    	 */
    	@Test
    	public void standardVariableTest2() {
            FileInputStream in1;
            FileInputStream in2;

            try {
                in1 = new FileInputStream(new File("src//test//java//codechecker//VariableStandardizationSample_1.java"));
                in2 = new FileInputStream(new File("src//test//java//codechecker//VariableStandardizationSample_2.java"));

                // parse the file
                CompilationUnit cu1 = JavaParser.parse(in1);
                CompilationUnit cu2 = JavaParser.parse(in2);

    	        /*
    	         * Visitors for removal of comments and obtaining the hash codes
    	         */
                VariableStandardizationVisitor vsv1 = new VariableStandardizationVisitor();
                VariableStandardizationVisitor vsv2 = new VariableStandardizationVisitor();
                HashCodeVisitor hcv1 = new HashCodeVisitor();
                HashCodeVisitor hcv2 = new HashCodeVisitor();

                /*
                 * All the nodes in the AST generated from the first submission are visited and 
                 * the nodes identified as comments are removed.
                 */
                cu1.accept(vsv1, null); 
                /*
                 * All the nodes in the AST generated from the second submission are visited and 
                 * the nodes identified as comments are removed.
                 */
                cu2.accept(vsv2, null);
                /*
                 * Each node in first submission is visited and hashed.
                 */
                cu1.accept(hcv1, null); 
                /*
                 * Each node in second submission is visited and hashed.
                 */
                cu2.accept(hcv2, null);
                
                HashSet<Integer> a = hcv1.getNodeHashCodes();
                HashSet<Integer> b = hcv2.getNodeHashCodes();

                assertEquals(true, a.equals(b));

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
            	fail("Not yet implemented");
            }
	}

}
