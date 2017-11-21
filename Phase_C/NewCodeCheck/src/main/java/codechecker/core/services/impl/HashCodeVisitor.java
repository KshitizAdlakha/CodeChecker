package codechecker.core.services.impl;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashSet;

//This class visits all the nodes in the AST, obtains their hashcode, and stores those hashcodes into a set.
public class HashCodeVisitor extends VoidVisitorAdapter<Void> {
    HashSet<Integer> nodeHashCodes = new HashSet<Integer>();

    /*
     * Getter to return the set containing the hashcodes of all the nodes in the AST
     */
    public HashSet<Integer> getNodeHashCodes(){
        return this.nodeHashCodes;
    }

    /*
     * Function to visit the nodes of the AST
     */
    @Override
    public void visit(CompilationUnit n, Void arg) {
        for(Node child : n.getChildNodes()) {
            nodeHashCodes.add(child.hashCode());
            visit(child, arg);
        }
    }
    
    /*
     * Function to visit the child nodes of the given node recursively
     */
    public void visit(Node n, Void arg) {
        for(Node child : n.getChildNodes()) {
            nodeHashCodes.add(child.hashCode());
            visit(child, arg);
        }
    }
}
