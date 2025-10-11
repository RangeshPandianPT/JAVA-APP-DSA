import java.util.Scanner;

class CircularQueue {
    int front, size, capacity;
    String[] Q;

    CircularQueue(int capacity) {
        this.capacity = capacity;
        Q = new String[capacity];
        front = 0;
        size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == capacity;
    }

    void enqueue(String x) {
        if (isFull()) {
            System.out.println("Queue Overflow");
            return;
        }
        int rear = (front + size) % capacity;
        Q[rear] = x;
        size++;
    }

    String dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow");
            return null;
        }
        String item = Q[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    String frontElement() {
        if (isEmpty()) return null;
        return Q[front];
    }

    void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue elements: ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(Q[index] + " ");
        }
        System.out.println();
    }
}

public class QueueArrayDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularQueue q = new CircularQueue(5);

        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        q.display();

        System.out.println("Dequeued: " + q.dequeue());
        q.display();

        q.enqueue("D");
        q.enqueue("E");
        q.display();

        System.out.println("Front Element: " + q.frontElement());
        sc.close();
    }
}
