import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AliensCreator {
    static int generation;
    static int compositeCounter;
    static int leafCounter;
    static Statement connection;

    public static void main(String[] args) {
        CompositeAlien alpha = new CompositeAlien(0, 0, 0);
/*        Scanner in = new Scanner(System.in);
        generation = in.nextInt();*/
        String dbUrl = "jdbc:derby:aliensDb;create=true";
        try {
            Connection connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();
            AliensCreator.connection = statement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        alpha.generateChildren();
/*        countChildren(alpha);
        int totalAliens = leafCounter + compositeCounter;
        System.out.println("Composite aliens " + compositeCounter + "; Leaf aliens " + leafCounter + "; Total aliens " + totalAliens);*/
    }

    public static void countChildren(CompositeAlien alien) {
        for (var child : alien.children) {
            if (child instanceof CompositeAlien) {
                if (child.getGeneration() == generation) {
                    compositeCounter++;
                }
                countChildren((CompositeAlien) child);
            } else {
                if (child.getGeneration() == generation) {
                    leafCounter++;
                }
            }
        }
    }

    public static void getGeneration() {
        String query = "SELECT * FROM aliens_data WHERE generation = " + generation;
    }

    public static void createDb() {
        String dbUrl = "jdbc:derby:aliensDb;create=true";
        try {
            Connection connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();
            AliensCreator.connection = statement;
            String query = "CREATE TABLE aliens_data (ID INT, generation INT, father INT, isComposite BOOLEAN)";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
           // System.out.println("db already exists");
        }
    }

}
