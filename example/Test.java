package example;

import src.NSON;

public class Test {
    public static void main(String[] args) {
        NSON nson = new NSON();
        String jsonString = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\"}";    
        System.out.println("Is valid JSON: " + nson.isValidJSON(jsonString)); // Should return true
        System.out.println("--------------------------------------------------------------");
        System.out.println( nson.loads(jsonString)); // Output the NSON object as a string



        
    }   
}
