package DSA.Queue;

class QueueArray {
    int front, rear, size;
    int capacity;
    int arr[];

    public QueueArray(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        size = 0;
        rear = capacity - 1;
    }

    // Enqueue
    void enqueue(int item) {
        if (size == capacity) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = item;
        size++;
        System.out.println(item + " enqueued to queue");
    }

    // Dequeue
    int dequeue() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return -1;
        }
        int item = arr[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    // Peek
    int peek() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return -1;
        }
        return arr[front];
    }

    boolean isEmpty() {
        return size == 0;
    }

    // Print the queue
    void printQueue() {
        if (size == 0) {
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
        QueueArray q= new QueueArray(5); // capacity = 5

        // Enqueue elements
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);

        q.printQueue();

        // Trying to insert when queue is full
        q.enqueue(60); // should print "Queue is full"

        // Peek the front element
        System.out.println("Front element: " + q.peek());

        // Dequeue some elements
        System.out.println("Removed: " + q.dequeue());
        System.out.println("Removed: " + q.dequeue());

        q.printQueue();

        // Peek again
        System.out.println("Front element now: " + q.peek());

        // Check empty
        System.out.println("Is queue empty? " + q.isEmpty());

        // Remove all elements
        q.dequeue();
        q.dequeue();
        q.dequeue();

        q.printQueue();

        // Queue becomes empty
        System.out.println("Is queue empty? " + q.isEmpty());

        // Try dequeue on empty queue
        q.dequeue();
    }
}