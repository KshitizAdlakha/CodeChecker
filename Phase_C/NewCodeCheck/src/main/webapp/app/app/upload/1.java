package plagpolice;

// represents a clone between two source files
public class Clone {
    //Code Stub in file1
    private CodeStub file1Stub;
    //Code Stub in file2
    private CodeStub file2Stub;

    //constructor
    public Clone(CodeStub file1Stub, CodeStub file2Stub){
        this.file1Stub = file1Stub;
        this.file2Stub = file2Stub;
    }

    //getter for file1Stub
    public CodeStub getFile1Stub() {
        return file1Stub;
    }

    //getter for file2Stub
    public CodeStub getFile2Stub() {
        return file2Stub;
    }
}