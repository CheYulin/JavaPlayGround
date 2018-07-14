package serialization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Created by yche on 5/31/17.
 */
public class ObjectSerialization {
    public static void main(String[] args) throws Exception {
        OneObject instance = new OneObject();
        instance.anotherObject = new AnotherObject();
        AnotherObject anotherObject = instance.anotherObject;
        anotherObject.integers = new int[]{1, 2, 3};
        anotherObject.lengthBytes = 3;
        anotherObject.myBytes = new byte[]{0x01, 0x02, 0x03, 0x04};

        instance.hashMap = new HashMap<String, Object>();
        instance.hashMap.put("key1", "val1");
        instance.hashMap.put("key2", 2);
        instance.myId = 0;
        instance.myString = "yche";

        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(new File("/tmp/o.ser")));
        oos.writeObject( instance );
        // close the writing.
        oos.close();
    }
}
