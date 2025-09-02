package DSA.Queue;


    // Node class
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Queue class
class QueueLL {
    Node head, tail;

    public QueueLL() {
        head = tail = null;
    }

    // Check if queue is empty
    boolean isEmpty() {
        return head == null;
    }

    // Enqueue (insert at tail)
    void enqueue(int data) {
        Node newNode = new Node(data);
        if (tail == null) { // Empty queue
            head = tail = newNode;
            System.out.println(data + " enqueued");
            return;
        }
        tail.next = newNode;
        tail = newNode;
        System.out.println(data + " enqueued");
    }

    // Dequeue (remove from head)
    int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int data = head.data;
        head = head.next;
        if (head == null) { // Queue became empty
            tail = null;
        }
        return data;
    }

    // Peek front element
    int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return head.data;
    }

    // Print queue
    void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        Node temp = head;
        System.out.print("Queue: ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        QueueLL q = new QueueLL();

        // Enqueue elements
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);

        q.printQueue();

        // Dequeue elements
        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Front element: " + q.peek());

        q.printQueue();

        // Dequeue remaining
        q.dequeue();
        q.dequeue();
        q.dequeue(); // Queue empty

        q.printQueue(); // Queue is empty
    }

}
    


