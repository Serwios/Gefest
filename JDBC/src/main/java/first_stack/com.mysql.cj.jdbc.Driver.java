package first_stack;

import java.sql.*;

class first_stack{

    static final String DATABASE_URL = "jdbc:mysql://new:3306/new?useLegacyDatetimeCode=false&amp&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        System.out.println("Registering JDBC driver...");
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement...");
        statement = connection.createStatement();

        String sql;
        sql = "SELECT * FROM movies";

        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("\nMovies:");
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("Name");
            String Director = resultSet.getString("Director");
            int year = resultSet.getInt("Year");
            String Country = resultSet.getString("Country");

            System.out.println("\n================\n");
            System.out.println("id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Director: " + Director);
            System.out.println("Year: " + year);
            System.out.println("Country: " + Country);

        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}

