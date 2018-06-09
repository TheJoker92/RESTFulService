/* Author ALESSANDRO DE MICCO -- ALTEN ITALIA */
/* The handler of hashmapping is created */

package hello;

import java.util.HashMap;

public class HashMapTools {

	//Declarations
    HashMap<Long, Person> hmap = new HashMap<Long, Person>();
	static String substitute = "";


	//Add elements to HashMap
	public void insertPerson(Person person) {
		//Object[] arrayString = {id,first_name, last_name, role,email,website};
		this.hmap.put(person.getId(), person);
	}
	
	

	
	//This method allows to write the hashmap in html and the result is inserted in the body of HashmapContaining.hmtl
	public String HashMaptoString() {
		 substitute = "<h2> <tr> <th>ID </th><th> First name </th><th> Last name </th> <th> Role </th> <th> Email </th> <th> Web site </th></tr> <h2>";
		 this.hmap.entrySet().stream().forEach(item -> {
         System.out.println(item.getKey() + ": " + item.getValue());
         substitute += "<tr><th> "+item.getKey().toString() + "</th><th> " + item.getValue().getFirstName() + "</th><th> " + item.getValue().getLastName() + "</th><th> " + item.getValue().getRole()  + "</th><th> " + item.getValue().getEmail() + "</th><th> " + item.getValue().getWebSite() +  "</th></tr>";
         System.out.println(substitute);}
     );					    
	    return substitute;
	}



	//Update values
	public void updatePerson(long id, String first_name, String last_name, String role,
			String email, String website) {
		
		if(Application.hashmap.hmap.containsKey(id)) {
			this.hmap.get(id).setFirstName(first_name);
			this.hmap.get(id).setLastName(last_name);
			this.hmap.get(id).setRole(role);
			this.hmap.get(id).setEmail(email);
			this.hmap.get(id).setWebSite(website);

		}

	}	
	

}
