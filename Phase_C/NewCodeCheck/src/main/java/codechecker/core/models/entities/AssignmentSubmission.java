package codechecker.core.models.entities;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Entity
public class AssignmentSubmission {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    private File associatedFile;

    @ManyToOne
    private Assignment assignment;

    public String getTitle() {
        return title;
    }

    public String textualRepresentation() {
        FileInputStream in;
        try {
            in = new FileInputStream(associatedFile);
            // parse the file
            CompilationUnit cu = JavaParser.parse(in);

            // prints the resulting compilation unit to default system output
            return cu.toString();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public File getAssociatedFile() {
        return associatedFile;
    }

    public void setAssociatedFile(File associatedFile) {
        this.associatedFile = associatedFile;
    }
}
