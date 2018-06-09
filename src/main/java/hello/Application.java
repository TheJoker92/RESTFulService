/* Author ALESSANDRO DE MICCO -- ALTEN ITALIA */

package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//Contains the main function

@SpringBootApplication
public class Application {
	
	//Declarations
	public static HashMapTools hashmap = new HashMapTools();
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println(hashmap.hmap.keySet());
    }
    
    
}