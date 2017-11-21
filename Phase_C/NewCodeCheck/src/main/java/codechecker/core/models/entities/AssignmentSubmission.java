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

/**
 * Assignment Submission Class
 */
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

    /**
     * get assignment submission title
     */
    public String getTitle() {
        return title;
    }

    /**
     * parse input file
     */
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

    /**
     * set assignment submission title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get assignment submission id
     */
    public Long getId() {
        return id;
    }

    /**
     * set assignment submission id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get assignment
     */
    public Assignment getAssignment() {
        return assignment;
    }

    /**
     * set assignment
     */
    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    /**
     * get assignment submission content
     */
    public String getContent() {
        return content;
    }

    /**
     * set assignment submission content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * get assignment submission file
     */
    public File getAssociatedFile() {
        return associatedFile;
    }

    /**
     * set assignment submission file
     */
    public void setAssociatedFile(File associatedFile) {
        this.associatedFile = associatedFile;
    }
}
