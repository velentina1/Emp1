package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

        private static String url;
        private static String username;
        private static String password;
        private static String driver;
        static {
            try {
            InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("JDBC/util/jdbc.properties");
            Properties properties = new Properties();

                properties.load(inputStream);
                url = properties.getProperty("url");
                username = properties.getProperty("username");
                password = properties.getProperty("password");
                driver = properties.getProperty("driver");
                Class.forName(driver);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url, username, password);
        }

        public static PreparedStatement getPreparedStatement(String sql,Connection connection) throws SQLException {
            return connection.prepareStatement(sql);
        }

        public static void bindPstmt(PreparedStatement preparedStatement,Object[] objects) throws SQLException {
            for (int i = 1; i <= objects.length ; i++) {
                preparedStatement.setObject(i,objects[i-1]);
            }
        }

        public static void close(ResultSet resultSet,Statement statement,Connection connection) throws SQLException {
            if (resultSet != null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                statement.close();
            }
        }

}
