package codechecker.core.services.impl.visitors;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

//This class visits all the nodes in the AST and removes the nodes identified as comments
public class CommentRemovalVisitor extends VoidVisitorAdapter<Void> {

    /*
     * Function to visit the nodes of the AST and remove the nodes identified as comments
     */
    @Override
    public void visit(CompilationUnit n, Void arg) {
        n.removeComment();
        for(Node child : n.getChildNodes()) {
            visit(child, arg);
        }
    }

    /*
     * Function to visit the child nodes of the given node recursively and remove the nodes identified as comments
     */
    public void visit(Node n, Void arg) {
        n.removeComment();
        for(Node child : n.getChildNodes()) {
            visit(child, arg);
        }
    }
}
