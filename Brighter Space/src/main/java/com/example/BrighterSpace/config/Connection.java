package com.example.BrighterSpace.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

public class Connection {

    private static final Logger logger = LoggerFactory.getLogger(Connection.class);
    private static java.sql.Connection connection = null;

    private Connection() {
    }

    public static java.sql.Connection getInstance(){
        if(connection == null){
            try{
                Properties properties = new Properties();

                String propertyFile = System.getProperty("user.dir") + "/src/main/resources/application.properties";

                InputStream inputStream = new FileInputStream(propertyFile);
                properties.load(inputStream);
                logger.debug("Attempting to create connection to the database");
                connection = DriverManager.getConnection(
                        properties.getProperty("spring.datasource.url"),
                        properties.getProperty("spring.datasource.username"),
                        properties.getProperty("spring.datasource.password")
                );
                logger.debug("Successfully created the connection to the database");
            } catch (Exception e) {
                logger.error("Error creating a connecting to mysql");
                e.printStackTrace();
            }
        }


        return connection;
    }
}
