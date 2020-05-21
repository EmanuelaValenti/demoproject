import java.util.ArrayList;

public class PersonList {
    private ArrayList<Person> list = new ArrayList<>();

    //Potrebbe tornare un booleano se voglio sapere se una persona è stata aggiunta
    public void addPerson(Person p){
        list.add(p);
    }

    public ArrayList<Person> getList(){
        //return list;
        ArrayList<Person> anotherlist = new ArrayList<>();
        for(Person p){
            //costruisco una nuova persona (buono per oggetti immutabili)
            Person x = new Person(p.getName(),p.getAge());
            anotherlist.add(x);
        }
        return anotherlist;
    }
}
