class NumberThread extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }
}

public class ThreadExample1 {
    public static void main(String[] args) {
        NumberThread t1 = new NumberThread();
        t1.start();
    }
}
