package codechecker.core.services.impl;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CommentRemovalVisitor extends VoidVisitorAdapter<Void> {

    @Override
    public void visit(CompilationUnit n, Void arg) {
        n.removeComment();
        for(Node child : n.getChildNodes()) {
            visit(child, arg);
        }
    }

    public void visit(Node n, Void arg) {
        n.removeComment();
        for(Node child : n.getChildNodes()) {
            visit(child, arg);
        }
    }
}
