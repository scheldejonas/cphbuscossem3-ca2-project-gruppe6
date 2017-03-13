package config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by scheldejonas on 13/03/2017.
 */
public class DataConfig {

    private static DataConfig singleton = null;
    private EntityManagerFactory entityManagerFactory = null;
    private String host = "";
    private String databaseName = "";
    private int port = 0;
    private String username = "";
    private String password = "";
    private String profile = "one";

    public static DataConfig getSingleton() {
        if (singleton == null) {
            singleton = new DataConfig();
        }
        return singleton;
    }

    private DataConfig() {
        if (profile.equals("one")) {
            this.host = "localhost";
            this.port = 3306;
            this.databaseName = "catwo";
            this.username = "catwouser";
            this.password = "catwopass";
            this.entityManagerFactory = createEntityManagerFactory();
        }
        if (profile.equals("two")) {
            this.host = "localhost";
            this.port = 3306;
            this.databaseName = "???";
            this.username = "???";
            this.password = "???";
            this.entityManagerFactory = createEntityManagerFactory();
        }
    }

    private EntityManagerFactory createEntityManagerFactory() {
        Map myProperties = new HashMap();
        myProperties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        myProperties.put("hibernate.connection.url", String.format("jdbc:mysql://%s:%s/%s", host, port, databaseName));
        myProperties.put("hibernate.connection.username", String.format("%s", this.username));
        myProperties.put("hibernate.connection.password", String.format("%s", this.password));
        myProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57InnoDBDialect");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate", myProperties);
        return entityManagerFactory;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return this.entityManagerFactory;
    }
}
