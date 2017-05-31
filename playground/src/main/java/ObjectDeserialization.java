import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by yche on 5/31/17.
 */
public class ObjectDeserialization {
    public static void main(String[] args) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(new File("/tmp/o.ser")));
        OneObject myObject = (OneObject) ois.readObject();


        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(myObject);
        System.out.println(jsonInString);
        System.out.println(myObject.anotherObject.myBytes.length);
    }
}
