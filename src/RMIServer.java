import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class RMIServer extends UnicastRemoteObject implements RMIServices {
   //Il server deve avere la PersonList
    PersonList person_list = new PersonList();
    protected RMIServer() throws RemoteException {
    }

    public void Otherstuff() {
        // maybe something other is done here....
        // but it's not part of the interface, so will be not registered
        // and visible externally...
    }

    @Override
    public String getDate() throws RemoteException {
        System.out.println("SERVER LOG: invoked getDate()");
        return new Date().toString();
    }

    @Override
    public String toUP(String s) throws  RemoteException {
        System.out.println("SERVER LOG: invoked toUP()");
        return s.toUpperCase();
    }

    @Override
    public ArrayList<Person> getList() throws RemoteException {
       //quando chiamo da remoto getList, ritorno a chi mi ha chiamato:
        //invoco Person list, chiamo get list e la torno a chi mi ha chiamato
        //all'altro lato arriva tutta la lista
        System.out.println("LOG SERVER: invoking getList()");
       return person_list.getList();
    }

    @Override
    public void addPerson(Person p) throws RemoteException {
        System.out.println("LIG SERVER: invoking addPerson");
        person_list.addPerson(p);
    }

    public static void main(String args[]) {
        try {
            RMIServices RMIServices = new RMIServer();
            Naming.rebind("dateandtup", RMIServices);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //Assegno il nuovo server, all'interfaccia Services
    }
}
