
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import pojos.HealthProfile;
import pojos.Person;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HealthProfileReader {

    public static Map<Long, Person> database = new HashMap<Long, Person>();

    static {
        Person pallino = new Person();
        Person pallo = new Person(new Long(2), "Pinco", "Pallo");
        HealthProfile hp = new HealthProfile(68.0, 1.72);
        Person john = new Person(new Long(3), "John", "Doe", hp);

        database.put(pallino.getPersonId(), pallino);
        database.put(pallo.getPersonId(), pallo);
        database.put(john.getPersonId(), john);
    }

    /**
     * The health profile reader gets information from the command line about
     * weight and height and calculates the BMI of the person based on this
     * parameters
     *
     * @param args
     */
    public static void main(String[] args) {
        //initializeDatabase();
        int argCount = args.length;
        if (argCount < 2) {
            System.out.println("Insert the type of action (add, view, update) followed by the person info");
        } else {
            String op = args[0];

            switch (op) {
                case "add":
                    if (argCount == 5) {
                        Long id = Long.parseLong(args[1]);
                        String fname = args[2];
                        String lname = args[3];
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date birthday;
                        try {
                            birthday = formatter.parse(args[4]);
                            createPerson(id, fname, lname, birthday);
                        } catch (ParseException ex) {
                            System.out.println("Wrong birthdate format: dd/MM/yyyy");
                        }
                        
                    } else {
                        System.out.println("Wrong number of parameters: add personId firstname lastname birthday");
                    }
                    break;
                case "view":
                    if (argCount == 2) {
                        Long id = Long.parseLong(args[1]);
                        displayHealthProfile(id);
                    } else {
                        System.out.println("Wrong number of parameters: view personId");
                    }
                    break;
                case "update":
                    if (argCount == 4) {
                        Long id = Long.parseLong(args[1]);
                        Double height = Double.parseDouble(args[2]);
                        Double width = Double.parseDouble(args[3]);
                        updateHealthProfile(id, height, width);
                    } else {
                        System.out.println("Wrong number of parameters: update personId height width");
                    }
                    break;
                default:
                    System.out.println("Unrecognized action");
            }

//			// read the person from the DB
//			Person p= database.get(id);
//			if (p!=null) { 
//				System.out.println(id+"'s health profile is: "+p.gethProfile().toString());
//			} else {
//				System.out.println("Person with id "+id+" is not in the database");
//			}
        }
        // add the case where there are 3 parameters, the third being a string that matches "weight", "height" or "bmi"
    }

	//public static void initializeDatabase() {
    //	Person pallino = new Person();
    //	Person pallo = new Person("Pinco","Pallo");
    //	HealthProfile hp = new HealthProfile(68.0,1.72);
    //	Person john = new Person("John","Doe",hp);
    //	
    //	database.put(pallino.getFirstname()+" "+pallino.getLastname(), pallino);
    //	database.put(pallo.getFirstname()+" "+pallo.getLastname(), pallo);
    //	database.put(john.getFirstname()+" "+john.getLastname(), john);
    //}
    public static void createPerson(Long personId, String firstname, String lastname, Date birthday) {
        Person newPerson = new Person(personId, firstname, lastname, birthday);
        database.put(newPerson.getPersonId(), newPerson);
        System.out.println(firstname + " " + lastname + " (id " + personId + ") added");
    }

    public static void displayHealthProfile(Long personId) {
        Person p = database.get(personId);
        if(p != null) {
            System.out.println(p.getFirstname() + " " + p.getLastname() + "'s health profile is: " + p.gethProfile().toString());
        } else {
            System.out.println("Person with id " + personId + " is not in the database");
        }
    }

    public static void updateHealthProfile(Long personId, Double height, Double weight) {
        Person p = database.get(personId);
        if (p != null) {
            HealthProfile hp = new HealthProfile(weight, height);
            p.sethProfile(hp);
            System.out.println(p.getFirstname() + " " + p.getLastname() + " (id " + personId + ") updated");
        } else {
            System.out.println("Person with id " + personId + " is not in the database");
        }

    }

}
