package codechecker.core.services;

import codechecker.core.models.entities.Account;
import codechecker.core.models.entities.Assignment;
import codechecker.core.repositories.AccountRepo;
import codechecker.core.repositories.AssignmentRepo;
import codechecker.core.repositories.AssignmentSubmissionRepo;
import codechecker.core.services.impl.AssignmentSubmissionServiceImpl;
import codechecker.core.services.impl.SimilarityPercentGenerator;
import codechecker.core.services.impl.visitors.CommentRemovalVisitor;
import codechecker.core.services.impl.visitors.FunctionStandardizationVisitor;
import codechecker.core.services.impl.visitors.HashCodeVisitor;
import codechecker.core.services.impl.visitors.VariableStandardizationVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ASTManipulationTests {


    @Autowired
    private AccountRepo repo;
    private AssignmentRepo assignmentRepo;
    private AssignmentSubmissionRepo assignmentSubmissionRepo;
    private Account account;


    /*Checks to see that a test file with its comments removed is identical to the same code
	 * with no comments in it.
	 */
	@Test
	public void commentRemovalTest() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//Test1.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//TestCommentsRemoved.test"));

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

    /*Checks to see that two files are same in similarity percentage
     */
    @Test
    public void similarityPercentageCalculatorTest() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//SimilarityPercentageTest_1.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//SimilarityPercentageTest_2.test"));

            // parse the file
            CompilationUnit cu1 = JavaParser.parse(in1);
            CompilationUnit cu2 = JavaParser.parse(in2);

	        /*
	         * Compare files for similarity
	         */
            HashCodeVisitor hcv1 = new HashCodeVisitor();
            HashCodeVisitor hcv2 = new HashCodeVisitor();


            SimilarityPercentGenerator sg = new SimilarityPercentGenerator();
            sg.getSimilarityPercent(hcv1,hcv2);

//            cu1.accept(sg, null); //All the nodes in the AST generated from the first submission are visited and the nodes identified as comments are removed
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


    /*Checks to see that two files are same in similarity percentage
     */
    @Test
    public void similarityPercentageCalculatorTest2() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//SimilarityPercentageTest_1.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//BlankPage.test"));

            // parse the file
            CompilationUnit cu1 = JavaParser.parse(in1);
            CompilationUnit cu2 = JavaParser.parse(in2);

	        /*
	         * Compare files for similarity
	         */
            HashCodeVisitor hcv1 = new HashCodeVisitor();
            HashCodeVisitor hcv2 = new HashCodeVisitor();


            SimilarityPercentGenerator sg = new SimilarityPercentGenerator();
            sg.getSimilarityPercent(hcv1,hcv2);

//            cu1.accept(sg, null); //All the nodes in the AST generated from the first submission are visited and the nodes identified as comments are removed
            cu1.accept(hcv1, null); //All the nodes in the AST generated from the first submission are visited.
            cu2.accept(hcv2, null); //All the nodes in the AST generated from the second submission are visited.

            HashSet<Integer> a = hcv1.getNodeHashCodes();
            HashSet<Integer> b = hcv2.getNodeHashCodes();

            assertEquals(false, a.equals(b));

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
    public void emptyFileTest() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//BlankPage.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_1.test"));

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

            assertEquals(false, a.equals(b));

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            fail("Not yet implemented");
        }
    }

    /*Checks to see that a test file with its comments removed is identical to the same code
	 * with no comments in it.
	 */
    @Test
    public void assignmentSubmissionTest() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//Test1.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//TestCommentsRemoved.test"));

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

    /*Checks to see that a test file with its comments removed is identical to the same code
	 * with no comments in it.
	 */
    @Test
    public void assignmentSubmissionTest2() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//Test1.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_1.test"));

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

            assertEquals(false, a.equals(b));

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
                in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_1.test"));
                in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_2.test"));

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
                in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableStandardizationTest_1.test"));
                in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableStandardizationTest_2.test"));

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
    	public void standardVariableTest3() {
            FileInputStream in1;
            FileInputStream in2;

            try {
                in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableStandardizationTest_3.test"));
                in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableStandardizationTest_4.test"));

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
    public void standardVariableTest4() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableStandardizationTest_4.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableStandardizationTest_5.test"));

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
    public void standardVariableTest5() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableStandardizationTest_5.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableStandardizationTest_6.test"));

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
    public void standardVariableTest6() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_2.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_3.test"));

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
    public void standardVariableTest7() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_4.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_5.test"));

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
    public void standardVariableTest8() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_1.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_5.test"));

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

            assertEquals(false, a.equals(b));

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
    public void standardVariableTest9() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_2.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//VariableDeclarationTest_7.test"));

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

            assertEquals(false, a.equals(b));

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            fail("Not yet implemented");
        }
    }

    /*
		 * Checks to see if function declarations and calls within a single 
		 * file are properly renamed.
    	 */
    	@Test
    	public void standardFunctionTest1() {
            FileInputStream in1;
            FileInputStream in2;

            try {
                in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//FunctionStandardizationTest_1.test"));
                in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//FunctionStandardizationTest_2.test"));

                // parse the file
                CompilationUnit cu1 = JavaParser.parse(in1);
                CompilationUnit cu2 = JavaParser.parse(in2);

    	        /*
    	         * Visitors for standardizing functions and obtaining the hash codes
    	         */
                FunctionStandardizationVisitor fsv1 = new FunctionStandardizationVisitor();
                FunctionStandardizationVisitor fsv2 = new FunctionStandardizationVisitor();
                HashCodeVisitor hcv1 = new HashCodeVisitor();
                HashCodeVisitor hcv2 = new HashCodeVisitor();

                /*
                 * All the nodes in the AST generated from the first submission are visited and 
                 * the function nodes are properly renamed.
                 */
                cu1.accept(fsv1, null); 
                /*
                 * All the nodes in the AST generated from the second submission are visited and 
                 * the function nodes are properly renamed.
                 */
                cu2.accept(fsv2, null);
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
            	fail("Not yet implemented");
            }
	}

    /*
         * Checks to see if function declarations and calls within a single
         * file are properly renamed.
         */
    @Test
    public void standardFunctionTest2() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//FunctionStandardizationTest_3.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//FunctionStandardizationTest_4.test"));

            // parse the file
            CompilationUnit cu1 = JavaParser.parse(in1);
            CompilationUnit cu2 = JavaParser.parse(in2);

    	        /*
    	         * Visitors for standardizing functions and obtaining the hash codes
    	         */
            FunctionStandardizationVisitor fsv1 = new FunctionStandardizationVisitor();
            FunctionStandardizationVisitor fsv2 = new FunctionStandardizationVisitor();
            HashCodeVisitor hcv1 = new HashCodeVisitor();
            HashCodeVisitor hcv2 = new HashCodeVisitor();

                /*
                 * All the nodes in the AST generated from the first submission are visited and
                 * the function nodes are properly renamed.
                 */
            cu1.accept(fsv1, null);
                /*
                 * All the nodes in the AST generated from the second submission are visited and
                 * the function nodes are properly renamed.
                 */
            cu2.accept(fsv2, null);
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

            assertEquals(false, a.equals(b));

        } catch (FileNotFoundException e) {
            fail("Not yet implemented");
        }
    }

    /*
         * Checks to see if function declarations and calls within a single
         * file are properly renamed.
         */
    @Test
    public void standardFunctionTest3() {
        FileInputStream in1;
        FileInputStream in2;

        try {
            in1 = new FileInputStream(new File("src//test//java//codechecker//core//services//FunctionStandardizationTest_5.test"));
            in2 = new FileInputStream(new File("src//test//java//codechecker//core//services//FunctionStandardizationTest_6.test"));

            // parse the file
            CompilationUnit cu1 = JavaParser.parse(in1);
            CompilationUnit cu2 = JavaParser.parse(in2);

    	        /*
    	         * Visitors for standardizing functions and obtaining the hash codes
    	         */
            FunctionStandardizationVisitor fsv1 = new FunctionStandardizationVisitor();
            FunctionStandardizationVisitor fsv2 = new FunctionStandardizationVisitor();
            HashCodeVisitor hcv1 = new HashCodeVisitor();
            HashCodeVisitor hcv2 = new HashCodeVisitor();

                /*
                 * All the nodes in the AST generated from the first submission are visited and
                 * the function nodes are properly renamed.
                 */
            cu1.accept(fsv1, null);
                /*
                 * All the nodes in the AST generated from the second submission are visited and
                 * the function nodes are properly renamed.
                 */
            cu2.accept(fsv2, null);
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

            assertEquals(false, a.equals(b));

        } catch (FileNotFoundException e) {
            fail("Not yet implemented");
        }
    }



//    /*Checks to see if two assignments with two different Ids are identical.
//	 */
//    @Test
//    public void compareAssignmentSubmissionsTest() {
//        Assignment assignment1 = new Assignment();
//        Assignment assignment2 = new Assignment();
//        AssignmentSubmissionServiceImpl  asl = new AssignmentSubmissionServiceImpl();
//
//        assertEquals("0",asl.compareAssignmentSubmissions(assignment1.getId(),assignment2.getId()));
//    }

}


