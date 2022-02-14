package chat;

import java.rmi.Naming;
import java.rmi.Remote;

public class ChatServerMain {

    public static void main(String[] args) throws Exception {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.setSecurityManager(new NullSecurityManager());
        } catch (Exception e) {
            // nichts
        }
        Remote remote = new ChatServer();
        Naming.rebind("echo", remote);
        System.out.println("Chat-Server started...");
    }
}