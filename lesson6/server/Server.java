package lesson6.server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {

    static final Logger log = LogManager.getLogger(Server.class);

    private Vector<ClientHandler> clients;

    public Server() {

        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            //System.out.println("Сервер запущен. Ожидаем клиентов...");
            log.info("server is started. waiting for clients");
            while (true) {
                socket = server.accept();
                //System.out.println("Клиент подключился");
                log.info("client connected");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                log.info("socket closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void sendPersonalMsg(ClientHandler from, String nickTo, String msg) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickTo)) {
                o.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                log.info("private message : " + from.getNick() + " -> " + nickTo);
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + " не найден в чате");
        log.warn("try to send private message : "  + from.getNick() + " -> " + nickTo);
    }

    public void broadcastMsg(ClientHandler from, String msg) {
        for (ClientHandler o : clients) {
            if (!o.checkBlackList(from.getNick())) {
                o.sendMsg(msg);
            }
            log.info(msg);
        }
    }

    public boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                log.warn("nick " + o.getNick() + " is busy on trying to connect");
                return true;
            }
        }
        return false;
    }

    public void broadcastClientsList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientslist ");
        for (ClientHandler o : clients) {
            sb.append(o.getNick() + " ");
        }
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        log.info("client " + client.getNick() + " subscribed for chat");
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        log.info("client " + client.getNick() + " unsubscribed from chat");
        broadcastClientsList();
    }
}
