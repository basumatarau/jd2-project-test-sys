package by.htp.basumatarau.jd2TestSystem;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInit {
    static void init() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/?useSSL=false", "root",
                "password")) {
            new ScriptRunner(connection).runScript(new FileReader("jd2-project-db-script-v.0.0.1.sql"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
