import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by yche on 6/3/17.
 */
public class Compression {
    static final int BUFFER = 10240;

    public static void main(String[] argv) throws Exception {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            value.append("java gzip 压缩测试");
        }
//        byte[] input = "www.everycoding.com".getBytes();
        byte[] input = value.toString().getBytes();

        Deflater compressor = new Deflater();
        compressor.setLevel(Deflater.BEST_COMPRESSION);
        compressor.setInput(input);
        compressor.finish();

        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);

        byte[] buf = new byte[BUFFER];
        while (!compressor.finished()) {
            int count = compressor.deflate(buf);
            bos.write(buf, 0, count);
        }
        bos.close();
        byte[] compressedData = bos.toByteArray();
        System.out.println("压缩后的byte结果：" + Arrays.toString(compressedData));
        System.out.println(Arrays.toString(compressedData).length());
        // 对压缩数据进行解压
        decompressor(compressedData);
    }

    /**
     * 对压缩数据进行解压
     *
     * @param compressedData
     * @throws Exception
     */
    public static void decompressor(byte[] compressedData) throws Exception {
        Inflater decompressor = new Inflater();
        decompressor.setInput(compressedData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(compressedData.length);
        byte[] buf = new byte[BUFFER];
        while (!decompressor.finished()) {
            int count = decompressor.inflate(buf);
            bos.write(buf, 0, count);

        }
        bos.close();
        byte[] decompressedData = bos.toByteArray();
        System.out.println("解压后的byte结果：" + Arrays.toString(decompressedData));
        System.out.println(Arrays.toString(decompressedData).length());
        System.out.println("解压出字符串：" + bos.toString());
    }
}
