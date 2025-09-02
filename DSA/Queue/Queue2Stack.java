package DSA.Queue;
import java.util.Stack;

public class Queue2Stack {


    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    // Enqueue
    void enqueue(int x) {
        s1.push(x);
        System.out.println(x + " enqueued");
    }

    // Dequeue
    int dequeue() {
        if (s2.isEmpty()) {
            if (s1.isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    int peek() {
        if (s2.isEmpty()) {
            if (s1.isEmpty()) {
                System.out.println("Queue is empty");
                return -1;
            }
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }



    public static void main(String[] args) {
        Queue2Stack q = new Queue2Stack();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Front: " + q.peek());
        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Dequeued: " + q.dequeue()); // empty
    }
}


