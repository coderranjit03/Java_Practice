package DSA.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class Stack2Queue {

    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    // Push element
    void push(int x) {
        // Enqueue into q2
        q2.add(x);

        // Move all elements from q1 to q2
        while (!q1.isEmpty()) {
            q2.add(q1.remove());
        }

        // Now q2 has all elements, make q2 as q1
        while (!q2.isEmpty()) {
            q1.add(q2.remove());
        }

        System.out.println(x + " pushed");
    }

    // Pop element
    int pop() {
        if (q1.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return q1.remove();
    }

    // Peek top element
    int top() {
        if (q1.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return q1.peek();
    }

    boolean isEmpty() {
        return q1.isEmpty();
    }



    public static void main(String[] args) {
        Stack2Queue s = new Stack2Queue();

        s.push(10);
        s.push(20);
        s.push(30);

        System.out.println("Top: " + s.top());      // 30
        System.out.println("Popped: " + s.pop());   // 30
        System.out.println("Popped: " + s.pop());   // 20
        System.out.println("Popped: " + s.pop());   // 10
        System.out.println("Popped: " + s.pop());   // Stack is empty
    }
}

