/* Author ALESSANDRO DE MICCO -- ALTEN ITALIA */
/*This class contains methods for handling file. For example open .html */

package hello;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileTools {

	
	public static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader (file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
	
	//This class create a html for showing the data contained in the HashMap
	public static String HashMapHtml(String filename) throws IOException {
		String substitute = "";
		substitute = Application.hashmap.HashMaptoString();
		return readFile(filename).replaceAll("replace", substitute);
		
	}


}
