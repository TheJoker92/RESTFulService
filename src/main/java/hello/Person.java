/* Author ALESSANDRO DE MICCO -- ALTEN ITALIA */
/* In this class it is presented the model of data */

package hello;

public class Person {

	//Declaration
    private long id;
    private String first_name;
    private String last_name;
    private String role;
    private String email;
    private String website;



    //Initialization
    public Person(long id, String first_name, String last_name,String role, String email, String website) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
        this.email = email;
        this.website = website;
    }

    
    //get and set methods
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return first_name;
    }
    
    public String getLastName() {
        return last_name;
    }

    public String getRole() {
        return role;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getWebSite() {
        return website;
    }

    public void setId(long value) {
    	this.id = value;
    }
    
    public void setFirstName(String value) {
    	this.first_name = value;
    }
    
    public void setLastName(String value) {
    	this.last_name = value;
    }

    public void setRole(String value) {
    	this.role = value;
    }
    
    public void setEmail(String value) {
    	this.email = value;
    }
    public void setWebSite(String value) {
    	this.website = value;
    }
    
    
}