package pl.edu.agh.school.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import pl.edu.agh.school.SchoolClass;
import pl.edu.agh.school.Teacher;
import pl.edu.agh.school.guice.SchoolModule;

public final class SerializableIPersistenceManager implements IPersistenceManager {

    private pl.edu.agh.logger.Logger log = new pl.edu.agh.logger.Logger();

	private String teachersStorageFileName;

	private String classStorageFileName;

	public SerializableIPersistenceManager() {
		teachersStorageFileName = "teachers.dat";
		classStorageFileName = "classes.dat";
	}

	@Inject
	public void setTeachersStorageFileName(@Named("teachersStorageFileName") String teachersStorageFileName) {
		this.teachersStorageFileName = teachersStorageFileName;
	}

	@Inject
	public void setClassStorageFileName(@Named("classStorageFileName") String classStorageFileName) {
		this.classStorageFileName = classStorageFileName;
	}

	public void saveTeachers(ArrayList<Teacher> teachers) {
		if (teachers == null) {
			throw new IllegalArgumentException();
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(teachersStorageFileName))) {
			oos.writeObject(teachers);
			log.log("Teachers saved!");
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			log.log("There was an error while saving the teachers data");
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Teacher> loadTeachers() {
		ArrayList<Teacher> res = null;
		try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(teachersStorageFileName))) {
			log.log("Teachers loaded!");
			res = (ArrayList<Teacher>) ios.readObject();
		} catch (FileNotFoundException e) {
			res = new ArrayList<Teacher>();
		} catch (IOException e) {
			log.log("There was an error while loading the teachers data");
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		return res;
	}

	public void saveClasses(ArrayList<SchoolClass> classes) {
		if (classes == null) {
			throw new IllegalArgumentException();
		}
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(classStorageFileName))) {
			log.log("Classes saved!");
			oos.writeObject(classes);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			log.log("There was an error while saving the classes data");
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SchoolClass> loadClasses() {
		ArrayList<SchoolClass> res = null;
		try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream(classStorageFileName))) {
			res = (ArrayList<SchoolClass>) ios.readObject();
			log.log("Classes loaded!");
		} catch (FileNotFoundException e) {
			res = new ArrayList<SchoolClass>();
		} catch (IOException e) {
			log.log("There was an error while loading the classes data");
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
		return res;
	}
}
