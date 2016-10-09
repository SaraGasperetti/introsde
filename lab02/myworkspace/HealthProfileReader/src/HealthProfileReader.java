import java.util.HashMap;
import java.util.Map;

import pojos.HealthProfile;
import pojos.Person;

public class HealthProfileReader {

	/* Solution to Exercise #02-2b */
	public static Map<Long, Person> database = new HashMap<Long, Person>(); //

	static {
		Person pallino = new Person();
		Person pallo = new Person(new Long(1), "Pinco", "Pallo", "1984-06-21");
		HealthProfile hp = new HealthProfile(68.0, 1.72);
		Person john = new Person(new Long(2), "John", "Doe", "1985-03-20", hp);

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

	/* Solution to Exercise #01-2b */
	public String createPerson(Long personId, String firstname,
			String lastname, String birthdate) {
		Person p = new Person(personId, firstname, lastname, birthdate);
		database.put(p.getPersonId(), p);
		return "A new person record (" + p.getPersonId()
				+ ") has been created for " + p.getLastname() + ", "
				+ p.getFirstname() + " born on " + p.getBirthdate();
	}

	/* Solution to Exercise #01-2c */
	public String displayHealthProfile(Long personId) {
		Person p = database.get(personId);
		HealthProfile hp = p.gethProfile();
		return p.getFirstname() + " has a weight of "
				+ hp.getWeight() + " Kg. and a height of " + hp.getHeight();
	}

	/* Solution to Exercise #01-2d */
	public String updateHealthProfile(Long personId, Double height,
			Double weight) {
		Person p = database.get(personId);
		HealthProfile hp = p.gethProfile();
		hp.setHeight(height);
		hp.setWeight(weight);
		return p.getFirstname() + " has updated weight to "
				+ hp.getWeight() + " Kg. and updated height to "
				+ hp.getHeight();
	}
	
	public String displayBMI(Long personId) {
		Person p = database.get(personId);
		HealthProfile hp = p.gethProfile();
		return p.getFirstname() + "'s BMI is " + hp.getBMI();
	}


}
