package pl.edu.agh.iisg.to.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import pl.edu.agh.iisg.to.executor.QueryExecutor;

public class Course {

    public static final String TABLE_NAME = "course";
    
    private static final Logger logger = Logger.getGlobal();

    private final int id;

    private final String name;
    
    private List<Student> enrolledStudents;
    
    private boolean isStudentsListDownloaded = false;

    private Course(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static Optional<Course> create(final String name) {
    	String insertSql = String.format("INSERT INTO %s (name) VALUES ('%s');", TABLE_NAME, name);
		try {
			int id = QueryExecutor.createAndObtainId(insertSql);
	        return Course.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Optional.empty();
    }

    public static Optional<Course> findById(final int id) {
    	String findByIdSql = String.format("SELECT * FROM %s WHERE id = %d", TABLE_NAME, id);
        
    	try {
			ResultSet rs = QueryExecutor.read(findByIdSql);
	        return Optional.of(new Course(rs.getInt("id"), rs.getString("name")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
    }

    public boolean enrollStudent(final Student student) {
        String enrollStudentSql = String.format("INSERT INTO student_course (student_id, course_id) VALUES (%d, %d)", student.id(), this.id);
        try {
            final int id = QueryExecutor.createAndObtainId(enrollStudentSql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Student> studentList() {
    	String findStudentListSql = String.format("SELECT student.id, student.first_name, student.last_name, student.index_number FROM student " +
                "JOIN student_course ON student.id = student_course.id WHERE student_course.id = %d", this.id);
    	
    	List<Student> resultList = new LinkedList<>();
    	try {
            ResultSet rs = QueryExecutor.read(findStudentListSql);
            while (rs.next()) {
                //resultList.add(Student.create(rs.getString("first_name"), rs.getString("last_name"), rs.getInt("index_number")));
            }
        } catch (SQLException ex) {
    	    ex.printStackTrace();
        }
    	return resultList;
    }
    
    public List<Student> cachedStudentsList() {
    	//TOTO implement
		return enrolledStudents;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public static class Columns {

        public static final String ID = "id";

        public static final String NAME = "name";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        return name.equals(course.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
