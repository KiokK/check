package ru.clevertec.check.config;

import ru.clevertec.check.exception.InternalServerException;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource {
    public static String DATASOURCE_URL = null;
    public static String DATASOURCE_USERNAME = null;
    public static String DATASOURCE_PASSWORD = null;
    public static String driver = "org.postgresql.Driver";

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
