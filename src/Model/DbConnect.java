package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    protected String dbUrl;
    protected String dbPassword;
    protected String dbUser;
    protected Connection connection;

    public DbConnect() throws SQLException {
        dbUrl = "jdbc:postgresql://localhost/cigar_in";
        dbUser = "postgres";
        dbPassword = "123";
        connection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
    }

}
