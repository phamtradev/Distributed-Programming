package vn.edu.iuh.fit.db;

import org.neo4j.driver.*;

public class ConnectDB {

    private static final String DB_NAME = "tra23633471";
    private static final String URI = "neo4j://localhost:7687";
    private static final String DB_USERNAME = "neo4j";
    private static final String DB_PASSWORD = "12345678";

    private static Driver driver;

    public static Driver getDriver() {
        if (driver == null) {
            driver = GraphDatabase.driver(URI, AuthTokens.basic(DB_USERNAME, DB_PASSWORD));
        }
        return driver;
    }

    public static Session getSession() {
        return getDriver().session(SessionConfig.forDatabase(DB_NAME));
    }
}
