package main;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dbID;

    private String firstname;
    private String lastname;

    public Student() {
    }

    public Student(String firstname, String lastname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }
}
