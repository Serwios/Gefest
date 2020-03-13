package first_stack;

import java.net.URL;
import java.sql.*;
import java.util.Scanner;
import java.io.IOException;
import java.net.URL;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class first_stack{

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/new?useLegacyDatetimeCode=false&amp&serverTimezone=UTC&useSSL=false";
    static final String USER = "root";
    static final String PASSWORD = "root";

    static Connection connection;
    static Statement statement;

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        JDBC.Connection();
        Quee.welcome();
        Quee.SQL();
        find_films.Film_Site();
    }

}

class JDBC extends first_stack{
    public static void Connection() throws SQLException, ClassNotFoundException {

        System.out.println("Registering JDBC driver...");
        Class.forName("com.mysql.cj.jdbc.Driver");

        try {
            System.out.println("Creating database connection...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Executing statement...");
            statement = connection.createStatement();
        }
         catch (Exception e){
            System.out.println("JDBC cann't connect to mySQL");

        }
         finally {
            System.out.println("@Excellent@");
            System.out.println();
        }
    }

}
class Quee extends first_stack{

     public static String welcome(){
        System.out.println("Write your name");
        Scanner name = new Scanner(System.in);
        String call = name.nextLine();
        System.out.println("Hello " + call + " Friend ");

        return call;
    }

    public static void SQL() throws SQLException {
        String sql;
        sql = "SELECT * FROM movies";

        ResultSet resultSet = statement.executeQuery(sql);

        Scanner in = new Scanner(System.in);
        System.out.print("Input a film: ");
        String film = in.nextLine();
        String AI_film = film.substring(0, 1).toUpperCase() + film.substring(1);
        System.out.println(AI_film);

        while (resultSet.next()) {

            String names = resultSet.getString("Name");
            String Director = resultSet.getString("Director");
            int year = resultSet.getInt("Year");
            String Country = resultSet.getString("Country");

            if (AI_film.equals(names)) {

                int id = resultSet.getInt("ID");
                System.out.println("\n================\n");
                System.out.println("id: " + id);
                System.out.println("Name: " + names);
                System.out.println("Director: " + Director);
                System.out.println("Year: " + year);
                System.out.println("Country: " + Country);
            }


        }

        System.out.println("\n");
        System.out.println("Did you want to look on DB?");
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        answer = answer.toLowerCase();

        ResultSet new_resultSet = statement.executeQuery(sql);
        if (answer.equals("так") || answer.equals("yes")) {
            System.out.println("Okay");

            while (new_resultSet.next()) {

                int id = new_resultSet.getInt("ID");
                String name = new_resultSet.getString("Name");
                String Director = new_resultSet.getString("Director");
                int year = new_resultSet.getInt("Year");
                String Country = new_resultSet.getString("Country");

                System.out.println("\n================\n");
                System.out.println("id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Director: " + Director);
                System.out.println("Year: " + year);
                System.out.println("Country: " + Country);
            }
        }else {
            System.out.println("This is end");
        }

        System.out.println("Maybe you want to add something newest?");

        in.close();
        input.close();
        resultSet.close();
        statement.close();
        connection.close();

    }

}

class find_films extends first_stack {
    public static Document getPage() throws IOException {

        String url = "https://www.kinoafisha.info/rating/movies/#";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;

    }

    public static void Film_Site() throws IOException {

        Document page = getPage();

        Element list = page.select("div[class=layer layer-reverse]").first();
        Elements films = list.select("a[class=films-list-item-link]");


        for (Element i : films) {

            String name = i.select("a[class=films-list-item-link").text();


            String str = "\t";

            System.out.println(name);


        }


    }
}

