package DSA.Queue;
import java.util.Queue;
import java.util.LinkedList;

public class QueueJCF {
    

    public static void main(String[] args) {
        // Create a queue of integers
        Queue<Integer> q = new LinkedList<>();

        // Enqueue elements (add)
        q.add(10);
        q.add(20);
        q.add(30);
        q.add(40);

        System.out.println("Queue: " + q);

        // Peek front element
        System.out.println("Front element: " + q.peek()); // 10

        // Dequeue elements (remove)
        System.out.println("Removed: " + q.remove()); // 10
        System.out.println("Removed: " + q.remove()); // 20

        System.out.println("Queue after removal: " + q);

        // Check if empty
        System.out.println("Is queue empty? " + q.isEmpty());

        // Add more elements
        q.add(50);
        q.add(60);

        System.out.println("Queue now: " + q);
    }


}
