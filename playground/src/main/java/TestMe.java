
/**
 * Created by yche on 7/13/17.
 */
public class TestMe {
    private static String transform(int i) {
        return Integer.toString(i * i);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i < 1000000; i++) {
            System.out.println(transform(i));
        }
    }
}
