package studentsTable;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;

public class MainClass {
    static Connection connection;
    static Statement stmt;

    public static void main(String[] args) {
        prepareTable(Student.class);
        Student s = new Student(1, "Bob", 100, "bob@gmail.com");
        addObject(s);
    }

    private static void addObject(Object obj) {
        Class c = obj.getClass();
        if (!c.isAnnotationPresent(XTable.class)) throw new RuntimeException("XTable missed");
        try {
            connect();
            String tableName = ((XTable)c.getAnnotation(XTable.class)).name();
            String query = "INSERT INTO " + tableName + " (";
            Field[] fields = c.getDeclaredFields();
            for (Field o : fields) {
                if (o.isAnnotationPresent(XField.class)) {
                    query += o.getName();
                }
            }
            query = query.substring(0, query.length() - 2);
            query += ") VALUES (";
            for (Field o : fields) {
                if (o.isAnnotationPresent(XField.class)) {
                    query += "?, ";
                }
            }
            query = query.substring(0, query.length() - 2);
            PreparedStatement ps = connection.prepareStatement(query);
            int counter = 1;
            for (Field o : fields) {
                if(o.isAnnotationPresent(XField.class)) {
                    ps.setObject(counter, o.get(obj));
                    counter++;
                }
            }
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void prepareTable(Class c) {
        if (!c.isAnnotationPresent(XTable.class)) throw new RuntimeException("XTable missed");
        HashMap<Class, String> hm = new HashMap<>();
        hm.put(int.class, "INTEGER");
        hm.put(String.class, "TEXT");
        try {
            connect();
            String tableName = ((XTable)c.getAnnotation(XTable.class)).name();
            stmt.executeUpdate("DROP TABLE IF EXIST " + tableName + ";");
            String query = "CREATE TABLE IF NOT EXIST " + tableName + " (";
            Field[] fields = c.getDeclaredFields();
            for (Field o: fields) {
                if (o.isAnnotationPresent(XField.class)) {
                    query += o.getName() + " " + hm.get(o.getType()) + ", ";
                }
            }
            query = query.substring(0, query.length()-2);
            query += ");";
            stmt.executeUpdate(query);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }
}
