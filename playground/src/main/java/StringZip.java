import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yche on 6/3/17.
 */
public class StringZip {
    static final int BUFFER = 10240;

    public static void main(String[] args) throws Exception {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            value.append("java gzip 压缩测试");
        }

        byte[] values = value.toString().getBytes();
        System.out.println("解压前大小" + values.length);

        values = compress(values);
        System.out.println("解压后大小" + values.length);

        values = decompress(values);
        System.out.println(new String(values));
    }

    public static byte[] decompress(byte[] data) throws Exception {

        GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(data));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        int count;
        byte dataBuffer[] = new byte[BUFFER];
        while ((count = gis.read(dataBuffer, 0, BUFFER)) != -1) {
            baos.write(dataBuffer, 0, count);
        }
        gis.close();
        baos.close();

        data = baos.toByteArray();

        return data;
    }


    public static byte[] compress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 压缩
        GZIPOutputStream gos = new GZIPOutputStream(baos);

        int count;
        byte dataBuffer[] = new byte[BUFFER];
        while ((count = bais.read(dataBuffer, 0, BUFFER)) != -1) {
            gos.write(data, 0, count);
        }

        gos.close();

        byte[] output = baos.toByteArray();

        baos.close();
        bais.close();

        return output;
    }


}
