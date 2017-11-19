package codechecker.core.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import codechecker.core.entities.Assignment;
import codechecker.core.services.util.AssignmentList;

public class AssignmentService {
    public Assignment findAssignment(Long userId, Long assignmentId) {
    	//This needs to be implemented.
    	return new Assignment();
    }
    public AssignmentList findAllAssignments(Long userId) {
    	//This needs to be implemented.
    	return new AssignmentList();
    }
    public Assignment createAssignment(Assignment assignment) {
    	//This needs to be implemented.
    	return new Assignment();
    }
    public double compareAssignmentSubmissions(Long assignmentId, Long otherAssignmentId) {
    	//This needs to be implemented.
    	FileInputStream in1;
    	FileInputStream in2;
		try {
			in1 = new FileInputStream(new File("src//test//java//codechecker//Test1.java"));
			in2 = new FileInputStream(new File("src//test//java//codechecker//Test2.java"));
	        // parse the file
	        CompilationUnit cu1 = JavaParser.parse(in1);
	        CompilationUnit cu2 = JavaParser.parse(in2);
	        /*
	         * This is where all the visitors will go.
	         */
	        
	        /*
	         * This is where the diff function will go
	         * retur diff(cu1.toString(), cu2.toString());
	         */

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return 0;
    }
}
