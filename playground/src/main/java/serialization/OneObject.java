package serialization;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by yche on 5/31/17.
 */
public class OneObject implements Serializable {
    public HashMap<String, Object> hashMap;
    public String myString;
    public Integer myId;
    public AnotherObject anotherObject;


}
