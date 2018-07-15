package io;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.io.File;
import java.lang.String;
import java.nio.file.StandardOpenOption;

public class JavaMmapPrivate {
    private static String filePath = "/tmp/malloc_fd.tmp";

    public static void main(String[] args) throws IOException {
        FileChannel channel = FileChannel.open(new File(filePath).toPath(),
                StandardOpenOption.READ, StandardOpenOption.WRITE,
                StandardOpenOption.CREATE, StandardOpenOption.SPARSE, StandardOpenOption.TRUNCATE_EXISTING
        );

        // thread local off-heap memory
        long size = 3L * 1024 * 1024 * 1024 / 2;
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.PRIVATE, 0, size);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 1000; i++) {
            mappedByteBuffer.putInt(i * 4, i);
        }
        for (int i = 0; i < 1000; i++) {
            System.out.println(mappedByteBuffer.getInt(i * 4));
        }

        Mmap.unmap(mappedByteBuffer);
    }
}
