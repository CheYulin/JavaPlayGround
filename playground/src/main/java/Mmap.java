import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    static void unmap(MappedByteBuffer mbb) {
        try {
            Method cleaner = mbb.getClass().getMethod("cleaner");
            cleaner.setAccessible(true);
            Method clean = Class.forName("sun.misc.Cleaner").getMethod("clean");
            clean.invoke(cleaner.invoke(mbb));
            System.out.println("unmap successful");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        RandomAccessFile memoryMappedFile = new RandomAccessFile("/tmp/bigFile", "rw");
        FileChannel fileChannel = memoryMappedFile.getChannel();
        MappedByteBuffer mBuf = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, memoryMappedFile.length());
        mBuf.load();

        System.out.println(mBuf.limit());
        for (int i = 0; i < mBuf.limit(); i++) {
            System.out.println((char) mBuf.get());
        }
        memoryMappedFile.close();
//      unmap(fileChannel, mBuf);
        unmap(mBuf);
    }
}
