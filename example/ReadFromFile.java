package example;

import java.util.Map;
import src.NSON;
import src.utility.FileAccess;

public class ReadFromFile {
    

    public static void main(String[] args) {
        NSON nson = new NSON();
        String filepath = "example\\example.json";
        FileAccess file = new FileAccess(filepath);

        String jsonString = file.read_raw(); 
        Map<String, Object> jsonObject = nson.loads(jsonString); 



        System.out.println("Is valid JSON: " + nson.isValidJSON(jsonString)); 
        System.out.println("----------------------------------------------------");
        System.out.println(nson.dumps(jsonObject, 5, true)); 

    }
}
