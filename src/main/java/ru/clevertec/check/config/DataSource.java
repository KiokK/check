package ru.clevertec.check.config;

import ru.clevertec.check.exception.InternalServerException;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource {
    public static final String DATASOURCE_URL;
    public static final String DATASOURCE_USERNAME;
    public static final String DATASOURCE_PASSWORD;
    public static final String driver = "org.postgresql.Driver";

    static {
        DATASOURCE_URL = System.getProperty("datasource.url");
        DATASOURCE_USERNAME = System.getProperty("datasource.username");
        DATASOURCE_PASSWORD = System.getProperty("datasource.password");
    }

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(DATASOURCE_URL, DATASOURCE_USERNAME, DATASOURCE_PASSWORD);
            if (connection == null) {
                throw new InternalServerException();
            }

            return connection;
        } catch (Exception e) {
            throw new InternalServerException();
        }
    }
}
