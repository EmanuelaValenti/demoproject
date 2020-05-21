import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMIServices extends Remote {

        //Questi sono metodi del server che chiameranno quelli della PersonList
        //Li chiamo allo stesso modo ma non sono quelli
        public String getDate() throws RemoteException;
        public String toUP(String s) throws RemoteException;
        public ArrayList<Person> getList() throws RemoteException;
        public void addPerson(Person p) throws RemoteException;
}












