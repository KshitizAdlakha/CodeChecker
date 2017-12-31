package codechecker.core.services.impl.visitors;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;

/**
 * Sort variables inside BlockStatements
 */

public class VariableOrderInMethodVisitor extends VoidVisitorAdapter<Void> {

    @Override
    public void visit(CompilationUnit n, Void arg) {
        for(Node child : n.getChildNodes()) {
            visit(child, arg);
        }
    }

    // Visit each node inside BlockStatements to sort them in an order depending upon their data types
    public void visit(Node n, Void arg){
        if(n instanceof BlockStmt){
            NodeList<Statement> nodeList = ((BlockStmt) n).getStatements();

            Collections.sort(nodeList, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if(o1 instanceof ExpressionStmt && o2 instanceof ExpressionStmt){
                        ExpressionStmt temp1 = ((ExpressionStmt) o1);
                        ExpressionStmt temp2 = ((ExpressionStmt) o2);
                        if(temp1.getExpression().isVariableDeclarationExpr() && temp2.getExpression().isVariableDeclarationExpr()){
                            temp1.getExpression().asVariableDeclarationExpr().getVariable(0).getType().asString()
                                    .compareTo(temp2.getExpression().asVariableDeclarationExpr().getVariable(0).getType().asString());
                        } else {
                            if(temp1.getExpression().isVariableDeclarationExpr()){
                                return 1;
                            } else {
                                if(temp2.getExpression().isVariableDeclarationExpr())
                                    return -1;
                                else
                                    return 0;
                            }
                        }
                    } else {
                        if(o1 instanceof ExpressionStmt){
                            ExpressionStmt temp1 = ((ExpressionStmt) o1);
                            if(temp1.getExpression().isVariableDeclarationExpr()){
                                return 1;
                            } else {
                                return 0;
                            }
                        } else {
                            if(o2 instanceof ExpressionStmt){
                                ExpressionStmt temp2 = ((ExpressionStmt) o2);
                                if(temp2.getExpression().isVariableDeclarationExpr()){
                                    return 1;
                                } else {
                                    return 0;
                                }
                            } else {
                                return 0;
                            }
                        }
                    }
                    return 0;
                }
            });
        }

        for(Node child: n.getChildNodes()){
            visit(child, null);
        }
    }


}
