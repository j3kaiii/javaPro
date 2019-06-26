package lesson6.server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addNewUser(String login, String nick, String pass) {
        String sql = String.format("INSERT INTO main ( login, password, nickname)\n" +
                "VALUES ('%s', '%s', '%s')", login,pass.hashCode(),nick);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname, password FROM main WHERE login = '" + login + "'");
            int myHash = pass.hashCode();
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);
                if (myHash == dbHash) {
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveHistory(String login, String msg) {
        String sql = String.format("INSERT INTO history (post, nick)\n" +
                "VALUES ('%s', '%s')", msg, login);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder getHistoryChat() {
        StringBuilder stringBuilder = new StringBuilder();
        String sql = String.format("SELECT nick, post from history\n" +
                "    ORDER BY ID");
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                stringBuilder.append(rs.getString("nick") + " " + rs.getString("post") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  stringBuilder;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
