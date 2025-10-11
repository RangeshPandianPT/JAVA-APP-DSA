import java.util.Scanner;

class Stack {
    int top;
    int capacity;
    String[] arr;

    Stack(int capacity) {
        this.capacity = capacity;
        arr = new String[capacity];
        top = -1;
    }

    boolean isEmpty() {
        return top == -1;
    }

    boolean isFull() {
        return top == capacity - 1;
    }

    void push(String x) {
        if (isFull()) {
            System.out.println("Stack Overflow");
        } else {
            arr[++top] = x;
        }
    }

    String pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return null;
        } else {
            return arr[top--];
        }
    }

    String peek() {
        if (isEmpty()) return null;
        return arr[top];
    }

    void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println("Stack contents (top to bottom):");
        for (int i = top; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }
}

public class StackArrayUndoRedo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = 5; // Max capacity for undo/redo
        Stack undoStack = new Stack(M);
        Stack redoStack = new Stack(M);

        String document = "";

        undoStack.push(document);  // initial state


        document += "hi";
        undoStack.push(document);
        redoStack = new Stack(M);

        document += " there";
        undoStack.push(document);
        redoStack = new Stack(M); 

        System.out.println(document); 

        if (!undoStack.isEmpty()) {
            redoStack.push(undoStack.pop());
            document = undoStack.peek();
        }
        System.out.println(document); 

        if (!redoStack.isEmpty()) {
            document = redoStack.pop();
            undoStack.push(document);
        }
        System.out.println(document);

        System.out.println("\nTest Case 2:");
        M = 3;
        undoStack = new Stack(M);
        redoStack = new Stack(M);
        document = "";
        undoStack.push(document);

        document += "A"; undoStack.push(document); redoStack = new Stack(M);
        document += "B"; undoStack.push(document); redoStack = new Stack(M);
        document += "C"; undoStack.push(document); redoStack = new Stack(M);

        for (int i = 0; i < 2; i++) {
            if (!undoStack.isEmpty()) redoStack.push(undoStack.pop());
        }
        document = undoStack.peek();

        document += "D"; undoStack.push(document); redoStack = new Stack(M);

        System.out.println(document); // Output: AD

        sc.close();
    }
}
