import java.lang.reflect.InvocationTargetException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yche on 5/30/17.
 */
public class Mmap {
    static void unmap(FileChannel fc, MappedByteBuffer mbb) {
        Class<?> fcClass = fc.getClass();
        java.lang.reflect.Method unmapMethod = null;
        try {
            unmapMethod = fcClass.getDeclaredMethod("unmap", new Class[]{MappedByteBuffer.class});
            unmapMethod.setAccessible(true);
            unmapMethod.invoke(null, new Object[]{mbb});
            System.out.println("unmap successful");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
