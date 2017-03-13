package config;

import rest.PersonResource;

import javax.ws.rs.core.Application;
import java.util.Set;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Mar 6, 2017 
 */
@javax.ws.rs.ApplicationPath("api")
public class ApiConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(PersonResource.class);
    }

}
