package chat;

import java.rmi.RemoteException;
import java.util.List;

public interface ChatService extends java.rmi.Remote {

    public boolean anmelden(ClientService q) throws RemoteException;
    public boolean abmelden(ClientService q) throws RemoteException;
    public void nachricht(String q) throws RemoteException;
    public List<String> getUserList() throws RemoteException;
}