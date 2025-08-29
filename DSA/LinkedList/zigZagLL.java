package DSA.LinkedList;

public class zigZagLL {
    public class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    // Add node at last
    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    // Print list
    public void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // ZigZag reordering
    public void zigZag() {
        if (head == null || head.next == null) return;

        // 1️⃣ Find mid
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;

        // 2️⃣ Reverse 2nd half
        Node curr = mid.next;
        mid.next = null;
        Node prev = null;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // 3️⃣ Merge alternate
        Node left = head;
        Node right = prev;
        Node nextLeft, nextRight;

        while (left != null && right != null) {
            nextLeft = left.next;
            left.next = right;
            nextRight = right.next;
            right.next = nextLeft;

            // move forward
            left = nextLeft;
            right = nextRight;
        }
    }

    public static void main(String[] args) {
        zigZagLL list = new zigZagLL();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);

        System.out.println("Original List:");
        list.printList(head);

        list.zigZag();

        System.out.println("ZigZag List:");
        list.printList(head);
    }
}
