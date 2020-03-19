package pl.edu.agh.school;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.guice.SchoolModule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SchoolModuleTest {

    Logger log1 = Logger.getInstance();
    Injector injector = Guice.createInjector(new SchoolModule());
    School school = injector.getInstance(School.class);
    Logger log2 = Logger.getInstance();

    @Test
    public void loggerTest() {
        assertEquals(log1, log2);
        assertEquals(log2, Logger.getInstance());
    }
}
