package DSA.LinkedList;

public class LinkedList {
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

    // add node at start
    public void addFirst(int data) {
        // create a new node
        Node newNode = new Node(data);
        size++;
        if (head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    // add node at last
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

    // add node at mid - given index
    public void addMid(int idx, int data) {
        if (idx == 0) {
            addFirst(data);
            return;
        } else if (idx == size) {
            addLast(data);
            return;
        } else if (idx > size) {
            System.out.println("Index out of range");
            return;
        }

        Node newNode = new Node(data);
        size++;
        Node temp = head;
        for (int i = 0; i < idx - 1; i++) {
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

    // remove node from start
    public int removeFirst() {
        if (size == 0) {
            System.out.println("LL is Empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }

        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    // remove node from last
    public int removeLast() {
        if (size == 0) {
            System.out.println("LL is Empty");
            return Integer.MIN_VALUE;
        } else if (size == 1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }

        Node prev = head;
        for (int i = 0; i < size - 2; i++) {
            prev = prev.next;
        }
        int val = prev.next.data;
        prev.next = null;
        tail = prev;
        size--;
        return val;
    }

    // remove node from nth position
    public void removeNth(int n) {
        if (head == null)
            return;

        // Count size
        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        if (n == length) { // remove head
            head = head.next;
            return;
        }

        int i = 1;
        Node prev = head;
        int indexToFind = length - n;
        while (i < indexToFind) {
            prev = prev.next;
            i++;
        }

        prev.next = prev.next.next;
    }

    // search node iteratively
    public int iterativeSearch(int key) {
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.data == key) {
                return i;
            }
            temp = temp.next;
            i++;
        }

        return -1;
    }

    // helper function for recursion
    public int helper(Node head, int key) {
        if (head == null) {
            return -1;
        }

        if (head.data == key) {
            return 0;
        }

        int idx = helper(head.next, key);
        if (idx == -1) {
            return -1;
        }

        return idx + 1;
    }

    // search node recursively
    public int recursiveSearch(int key) {
        return helper(head, key);
    }

    // reverse a linked list
    public void reverse() {
        Node prev = null;
        Node curr = tail = head;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    //Detect Loop in LL- Floyds Cycle Finding alg
    public static boolean isCycle() {
        Node slow = head;
        Node fast = head;
        while(fast!=null && fast.next !=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }

    //Remove Cycle in LL
    public static void removeCycle(){
        Node slow = head;
        Node fast = head;
        boolean cycle = false;

        // Detect cycle using Floyd's algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                cycle = true;
                break;
            }
        }

        if (!cycle) {
            return;
        }

        // Find the start of the cycle
        slow = head;
        Node prev = null; // To keep track of the node before meeting point
        while (slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the cycle
        prev.next = null;
    }

    // print LinkedList
    public void printLL() {
        if (head == null) {
            System.out.print("LL is Empty");
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        ll.addFirst(1);
        ll.addLast(2);
        ll.addLast(2);
        ll.addLast(1);
        ll.printLL();
    }

}
