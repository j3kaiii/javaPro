package lesson2;

import java.sql.*;

public class dataBase {

    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // ------------------    CREATE    ---------------------------
        System.out.println("--  Создание таблицы  --");
        String sql = "CREATE TABLE IF NOT EXISTS students (\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name  TEXT    NOT NULL,\n" +
                "    score INTEGER NOT NULL\n" +
                ");";

        try {
            int table = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // ---------------       INSERT     ----------------------
        System.out.println("--  Заполнение данными  --");
        for (int i = 0; i < 5; i++) {
            String name = "Bob" + i;
            sql = String.format("INSERT INTO students (name, score) VALUES ('%s', '%s')", name, i*2);

            try {
                int query = stmt.executeUpdate(sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        tableInfo();

        //  -------------   UPDATE   -------------------
        try {
            sql = "UPDATE students SET name = 'Danny' WHERE id = 3";
            stmt.executeUpdate(sql);
            System.out.println("\n -- Обновление таблицы --");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableInfo();

        //  --------------  DELETE   ---------------------

        try {
            sql = "DELETE FROM students WHERE score > 3";
            stmt.executeUpdate(sql);
            System.out.println("\n -- Обновление таблицы --");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tableInfo();

        //  ------------  DROP   --------------
        System.out.println("--  Удаление таблицы  --");
        try {
            stmt.executeUpdate("DROP TABLE students");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void connect() throws ClassNotFoundException, SQLException {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:MainDB.db");
            stmt = connection.createStatement();
    }

    // ------------       SELECT     --------------------------
    public static void tableInfo() {
        String query = "SELECT * FROM students";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i+1) + "\t");
            }
            System.out.println("\n");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString("name") + "\t" + rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
