import javax.persistence.*;


@Entity
@Table(name="students_table")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private String first_name;
    private String last_name;

    private int enrollment_id;

    public Student() {

    }

    public Student(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int id) {
        this.ID = id;
    }
}
