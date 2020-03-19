package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import pl.edu.agh.school.persistence.IPersistenceManager;
import pl.edu.agh.school.persistence.SerializableIPersistenceManager;


public class SchoolModule extends AbstractModule {
    protected void configure() {
        bind(IPersistenceManager.class).to(SerializableIPersistenceManager.class);
        bind(String.class).annotatedWith(Names.named("teachersStorageFileName")).toInstance("guice-teachers.dat");
        bind(String.class).annotatedWith(Names.named("classStorageFileName")).toInstance("guice-classes.dat");
        bind(String.class).annotatedWith(Names.named("filename")).toInstance("persistence.log");
    }
}
