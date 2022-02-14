package chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import chat.ChatService;
import chat.ClientService;

public class ChatServer extends UnicastRemoteObject implements ChatService {

    List<ClientService> clients;

    public ChatServer() throws RemoteException {
        clients = new LinkedList<ClientService>();
    }

    @Override
    public boolean abmelden(ClientService arg0) throws RemoteException {
        boolean removed = false;
        synchronized (clients) {
            //here comes the code to do the abmelden
            return removed;
        }
    }

    @Override
    public boolean anmelden(ClientService arg0) throws RemoteException {
        boolean returnAnmeldung = true;
        synchronized (clients) {
            for (ClientService q : clients) {
                if (q.getName().equals(arg0.getName())) {
                    returnAnmeldung = false;
                    break;
                }
            }
            // Bind client
            try {
                Naming.rebind(arg0.getName(), arg0);
            } catch (MalformedURLException e) {
                System.err.println("could not bind client");
            }

            if (returnAnmeldung) {
                clients.add(arg0);
                for (ClientService c : clients) {
                    c.setNames(getUserList());
                }
            } else {
                // Name bereits vergeben? Weise Client ab.
                try {
                    Naming.unbind(arg0.getName());
                } catch (MalformedURLException e) {
                    // nichts
                } catch (NotBoundException e) {
                    // nichts
                }
            }
        }
        return returnAnmeldung;
    }

    @Override
    public List<String> getUserList() throws RemoteException {
        List<String> userList = new LinkedList<String>();
        synchronized (clients) {
            for (ClientService clientServ : clients) {
                userList.add(clientServ.getName());
            }
        }
        return userList;
    }

    @Override
    public void nachricht(String arg0) throws RemoteException {
        for (ClientService clientServ : clients) {
            clientServ.nachricht(arg0);
        }
    }
}