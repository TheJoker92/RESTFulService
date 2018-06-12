/* Author ALESSANDRO DE MICCO -- ALTEN ITALIA */


package hello;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//HOW PARAMETER ARE PASSED:
//-SUBMITION: (submit) --> submit.html --> (/user/{id})
//-SEARCHING: (search)--> search.html --> (/found)
//-HASHMAP: (hashmap)

@RestController
public class WebController {

    private final static AtomicLong counter = new AtomicLong();

    private final static String resource = "src/resources/";
    
    public Logger logger = LoggerFactory.getLogger("hello.WebController");
    
	static long id = counter.incrementAndGet();

	
	
    //GET controller
	//The function returns a html page in "http://localhost:8090/home" that allows to insert new data
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHome() throws IOException {    	
    	return FileTools.readFile(resource+"home.html");
    }
    
    //GET controller
	//The function returns a html page in "http://localhost:8090/submit" that allows to insert new data
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String getSubmit() throws IOException {    	
        return FileTools.readFile(resource+"submit.html").replaceAll("id_set", Long.toString(id++));
    }
    
    
    //POST controller
    //The function returns a html page in "http://localhost:8090//user/{id}" that returns a new value in JSON  format
    @RequestMapping(value = "/user/{id}")
    public ResponseEntity<Person> get(@PathVariable("id") long id,
    		@RequestParam(value="first_name", defaultValue="null") String first_name,
    		@RequestParam(value="last_name", defaultValue="null") String last_name,
    		@RequestParam(value="role", defaultValue="null") String role,
    		@RequestParam(value="email", defaultValue="null") String email,
    		@RequestParam(value="website", defaultValue="null") String website,
    	    @RequestParam(value="update", defaultValue= "0") String update,
    	    @RequestParam(value="delete", defaultValue= "0") String delete) throws IOException {
    	
    		//Create a new Person
    		Person person = new Person(id, first_name, last_name, role, email, website);
    		person.setId(id);
    		person.setFirstName(first_name);
    		person.setLastName(last_name);
    		person.setRole(role);
    		person.setEmail(email);
    		person.setWebSite(website);

    		//Verify the identity exists in the hashmap then add class
        	if(!Application.hashmap.hmap.containsKey(id)) {
	    	Application.hashmap.insertPerson(person);
	    	logger.debug("OK: id = " + id + " is inserted!");
    	    return new ResponseEntity<Person>(person, HttpStatus.OK);
        	}else {
    	    	logger.error("ERROR: id = " + id + " already exists!");
        	}
        	return null;
        	
    }


    
    //PUT controller
    //The function returns a html page in "http://localhost:8090/submit" that allows to update or remove a data
    @RequestMapping(value = "/update_delete")
    public void update_delete(@RequestParam(value="id", defaultValue="3") long id,
    		@RequestParam(value="first_name", defaultValue="null") String first_name,
    		@RequestParam(value="last_name", defaultValue="null") String last_name,
    		@RequestParam(value="role", defaultValue="null") String role,
    		@RequestParam(value="email", defaultValue="null") String email,
    		@RequestParam(value="website", defaultValue="null")  String website,
    	    @RequestParam(value="update", defaultValue= "0") String update,
    	    @RequestParam(value="delete", defaultValue= "0") String delete) {
    	System.out.println(id);
    	//Verify the identity exists then add class
	    if(update.equals("update")) {
	    	
    		Application.hashmap.updatePerson(id,first_name, last_name, role,email,website);
    		System.out.println("insideupdate"+update);
	    }
	    if(delete.equals("delete")) {
    		Application.hashmap.hmap.remove(id);
	    }
    	/*if(delete=="delete") {
    		Application.hashmap.hmap.remove(id);
    	}*/
    	System.out.println(update.getClass().getName());
		System.out.println("update"+update);

    }
    
    //GET controller
    //The function returns a html page in "http://localhost:8090/search" that allow to search a person by ID.
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public String search()  throws IOException {
        return FileTools.readFile(resource+"search.html");
    }
    
    //GET controller
    //The function returns a html page in "http://localhost:8090/found" that allows to modify value of a person .
    @RequestMapping(value = "/found")
    @ResponseBody
    public String found(@RequestParam(value="id", defaultValue="3") long id,
    		@RequestParam(value="first_name", defaultValue="null") String first_name,
    		@RequestParam(value="last_name", defaultValue="null") String last_name,
    		@RequestParam(value="role", defaultValue="null") String role,
    		@RequestParam(value="email", defaultValue="null") String email,
    		@RequestParam(value="website", defaultValue="null") String website)  throws IOException {
    	
    	if(Application.hashmap.hmap.containsKey(id)) {
    	first_name = Application.hashmap.hmap.get(id).getFirstName().toString();
    	last_name = Application.hashmap.hmap.get(id).getLastName().toString();
    	role = Application.hashmap.hmap.get(id).getRole().toString();
    	email = Application.hashmap.hmap.get(id).getEmail().toString();
    	website = Application.hashmap.hmap.get(id).getWebSite().toString();

    	String output = FileTools.readFile(resource+"found.html");
        return output.replaceAll("identity", Long.toString(id))
        				.replaceAll("old_first_name",first_name)
        				.replaceAll("old_last_name", last_name)
        				.replaceAll("old_role", role)
        				.replaceAll("old_email", email)
        				.replaceAll("old_website", website);   
	    }else {
	    	return "Error! Page Not Found";
	    }
    
    }
    
    //GET controller
    //The function returns a html page in "http://localhost:8090/hashmap" that returns all elements of the hashamp.
    @RequestMapping(value = "/hashmap", method = RequestMethod.GET)
    public String HashMapContained() throws IOException {
        return FileTools.HashMapHtml(resource+"HashMapContaining.html");
    }
    
    
   
    
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public FileSystemResource getFileJS(@RequestParam(value = "filename") String filename) {
    	if(filename.contains("js")) {
    		return new FileSystemResource(resource+"script/"+filename); 
    	} else if(filename.contains("css")) {
            return new FileSystemResource(resource+"css/"+filename); 
    	} else if(filename.contains("jpeg")) {
            return new FileSystemResource(resource+"img/"+filename);
    	}
    	
    	return null;
    }

   
}	

