package chat;

import java.rmi.RemoteException;
import java.util.List;

public interface ClientService extends java.rmi.Remote {
	public void anmelden(String s)throws RemoteException;
	public void abmelden(String s)throws RemoteException;
	public void nachricht(String s) throws RemoteException;
	public List<String> getUserList() throws RemoteException;
	public String getName() throws RemoteException;
	public void setNames(final List<String> a) throws RemoteException;
}