import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class RMIServer extends UnicastRemoteObject implements RMIServices {
    PersonList person_list = new PersonList();

    protected RMIServer() throws RemoteException {

   //------------------------------
        super(7500);//qui ci va la nostra porta
  //-------------------------------

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
        System.out.println("LOG SERVER: invoking getList()");
        return person_list.getList();
    }

    @Override
    public void addPerson(Person p) throws RemoteException {
        System.out.println("LOG SERVER: invoking addPerson");
        person_list.addPerson(p);
    }

    @Override
    public synchronized void doIntensiveTask() throws RemoteException {
        System.out.println("Thread that invoked doIntensiveTask: "+Thread.currentThread().getName());
        System.out.println("doing something....");
        int i = 0;
        while (i<100) {
            System.out.println("completed "+i+"% ");
            i = i +10;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("COMPLETED!");

    }

    public static void main(String args[]) {
        try {

            // for local tests
            //Naming.rebind("listserver",services);
          System.setProperty("java.rmi.server.hostname","whitelodge.ns0.it");
          Registry registry = LocateRegistry.getRegistry();
          //-------------------------------------------------------
          //Prima di creare un'istanza dell'oggetto Server RMI, è necessario impostare la proprietà
          // dell'hostname di RMI utilizzando il setProperty.
          // Succcessivamente è necessario individuare rmiregistry con il metodo statico
          //getRegistry() e solo dopo si può creare il nuovo server in modo che presenti le proprietà da noi inserite.
          //Se invece facessimo nascere il server prima, questo presenterebbe le condizioni di default,
          // come il riferimento alla porta 1099, e non le proprietà desiderate dato che in quel caso verrebbero dichiarate
          // dopo la sua nascita.
          //--------------------------------------------------------
          RMIServices services = new RMIServer();

          registry.rebind("rmiservices", services);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
