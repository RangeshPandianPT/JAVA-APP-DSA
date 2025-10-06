class SquareRunnable implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Square of " + i + " is " + (i * i));
        }
    }
}

public class ThreadExample3 {
    public static void main(String[] args) {
        SquareRunnable obj = new SquareRunnable();
        Thread t = new Thread(obj);
        t.start();
    }
}
