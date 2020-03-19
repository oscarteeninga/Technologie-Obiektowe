package pl.edu.agh.school.persistence;

import java.util.ArrayList;

public interface IPersistenceManager {
    void saveTeachers(ArrayList<pl.edu.agh.school.Teacher> teachers);
    ArrayList<pl.edu.agh.school.Teacher> loadTeachers();
    void saveClasses(ArrayList<pl.edu.agh.school.SchoolClass> classes);
    ArrayList<pl.edu.agh.school.SchoolClass> loadClasses();
}
