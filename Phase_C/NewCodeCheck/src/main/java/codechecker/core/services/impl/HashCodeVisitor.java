package codechecker.core.services.impl;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.HashSet;

public class HashCodeVisitor extends VoidVisitorAdapter<Void> {
    HashSet<Integer> nodeHashCodes = new HashSet<Integer>();

    public HashSet<Integer> getNodeHashCodes(){
        return this.nodeHashCodes;
    }

    @Override
    public void visit(CompilationUnit n, Void arg) {
        for(Node child : n.getChildNodes()) {
            nodeHashCodes.add(child.hashCode());
            visit(child, arg);
        }
    }

    public void visit(Node n, Void arg) {
        for(Node child : n.getChildNodes()) {
            nodeHashCodes.add(child.hashCode());
            visit(child, arg);
        }
    }
}