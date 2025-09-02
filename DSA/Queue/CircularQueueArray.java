package DSA.Queue;

public class CircularQueueArray {
    
    int front, rear, size;
    int capacity;
    int arr[];

    public CircularQueueArray(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    // Check if queue is full
    boolean isFull() {
        return size == capacity;
    }

    // Check if queue is empty
    boolean isEmpty() {
        return size == 0;
    }

    // Enqueue
    void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = item;
        size++;
        System.out.println(item + " enqueued");
    }

    // Dequeue
    int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int item = arr[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    // Peek front element
    int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return arr[front];
    }

    // Print queue
    void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue: ");
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.print(arr[index] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueueArray q = new CircularQueueArray(5);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);

        q.printQueue();

        q.enqueue(60); // Queue is full

        System.out.println("Dequeued: " + q.dequeue());
        System.out.println("Dequeued: " + q.dequeue());

        q.printQueue();

        q.enqueue(60);
        q.enqueue(70);

        q.printQueue();

        System.out.println("Front element: " + q.peek());

        // Remove all
        while (!q.isEmpty()) {
            System.out.println("Removed: " + q.dequeue());
        }

        q.printQueue(); // Queue is empty
    }

}
