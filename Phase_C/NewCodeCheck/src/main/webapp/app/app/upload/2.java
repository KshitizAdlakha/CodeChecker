package plagpolice;

// represents a continuous part of a source file
public class CodeStub {
    //first line number of the code stub
    private int startLine;
    //last line number of the code stub
    private int endLine;

    //constructor
    public CodeStub(int startLine,int endLine){
        this.startLine = startLine;
        this.endLine = endLine;
    }

    //getter for startLine
    public int getStartLine() {
        return startLine;
    }

    //getter for endLine
    public int getEndLine() {
        return endLine;
    }

    //checks if the stub is equal to this object
    public boolean isEqual(CodeStub stub){
        if(stub.startLine==this.startLine && stub.endLine==this.endLine)
            return true;
        return false;
    }
}
