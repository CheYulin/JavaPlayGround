import com.google.common.base.Optional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yche on 6/6/17.
 */
public class OptionalUsage {
    public static void main(String[] args) {
        Optional<HashMap<String, Object>> updateInfo = Optional.absent();
        System.out.println(updateInfo.isPresent());

        updateInfo = Optional.of(new HashMap<String, Object>());
        System.out.println(updateInfo.isPresent());
        updateInfo.get().put("key1", "val1");
        updateInfo.get().put("key2", 2);

        for (Map.Entry<String, Object> entry : updateInfo.get().entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
