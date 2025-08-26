package DSA.LinkedList;

public class PalindromeLL {

    public  class Node{
        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }

    }

        public static Node head;
        public static Node tail;
        public static int size;

        //add node at start
        public void addFirst(int data){
            //create a new node
            Node newNode = new Node(data);
            size++;
            if(head==null){
                head = tail=newNode;
                return;
            }
            newNode.next = head;
            head = newNode;
        }

        //add node at last
        public void addLast(int data){
            Node newNode = new Node(data);
            size++;
            if(head ==null){
                head = tail = newNode;
                return;
            }
            tail.next= newNode;
            tail = newNode;
        }


        //add node at mid - given index
        public void addMid(int idx,int data){
            Node newNode = new Node(data);
            size++;
            Node temp= head;
            int i =0;

            while(i<idx-1){
                temp = temp.next;
                i++;
            }

            newNode.next = temp.next;
            temp.next=newNode;
        }

        
        
        //remove node from start
        public int removeFirst(){
            if(size==0){
                System.out.println("LL is Empty");
                return Integer.MIN_VALUE;
            }else if(size==1){
                int val = head.data;
                head = tail = null;
                size=0;
                return val;
            }
            
            int val = head.data;
            head = head.next;
            size--;
            return val;
        }
        
        
        //find mid of LL
        public Node findMid(Node head){
            Node slow = head;
            Node fast= head;

            while(fast!=null && fast.next!= null){
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

        //Check if LL is Palindrome or Not
        public boolean Palindrome(){
            if(head == null || head.next==null){
                return true;
            }

            Node midNode = findMid(head);

            Node prev = null;
            Node curr = midNode;
            Node next;
            while(curr!= null){
                next = curr.next;
                curr.next= prev;
                prev = curr;
                curr=next;
            }

            Node right = prev;
            Node left= head;

            while(right!=null){
                if(left.data != right.data ){
                    return false;
                }
                left = left.next;
                right = right.next;
            }
            return true;
        }
        
        //print LinkedList
        public void printLL(){
            if (head == null){
                System.out.print("LL is Empty");
            }
            Node temp = head;
            while(temp != null){
                System.out.print(temp.data+"->");
                temp = temp.next;
            }
            System.out.println();
        }
        
        public static void main(String[] args) {
            PalindromeLL ll = new PalindromeLL();
            ll.addFirst(1);
            ll.printLL();
            ll.addLast(5);
            ll.printLL();
            ll.addMid(1,2);
            ll.printLL();
            ll.addLast(2);
            ll.printLL();
            ll.addLast(1);
            ll.printLL();

            System.out.println("after Checking Palindrome");

            System.out.println(ll.Palindrome());

            
        }
    

}
