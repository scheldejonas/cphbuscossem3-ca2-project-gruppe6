package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;


public class DataConfig {

    private static final DataConfig singleton = new DataConfig();
    private EntityManagerFactory entityManagerFactory = null;
    private String host = "";
    private String databaseName = "";
    private int port = 0;
    private String username = "";
    private String password = "";
    private String profile = "two";

    public static DataConfig getSingleton() {
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
            this.host = "viter.dk";
            this.port = 3306;
            this.databaseName = "yellowpages3";
            this.username = "transformer";
            this.password = "bookworm#17laesehest";
            this.entityManagerFactory = createEntityManagerFactory();
        }
    }

    private EntityManagerFactory createEntityManagerFactory() {
        Map myProperties = new HashMap();
        myProperties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        myProperties.put("hibernate.connection.url", String.format("jdbc:mysql://%s:%s/%s", host, port, databaseName));
        myProperties.put("hibernate.connection.username", String.format("%s", this.username));
        myProperties.put("hibernate.connection.password", String.format("%s", this.password));
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate", myProperties);
        return entityManagerFactory;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return this.entityManagerFactory;
    }

}