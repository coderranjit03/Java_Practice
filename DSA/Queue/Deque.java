package DSA.Queue;
import java.util.*;


public class Deque {
    //stack and queue using deque

    static class stack{
        java.util.Deque<Integer> deque = new LinkedList<>();

        public void push(int data){
            deque.addLast(data);
        }

        public int pop(){
            return deque.removeLast();
        }

        public int peek(){
            return deque.getLast();
        }
    }

    static class Queue{
        java.util.Deque<Integer> deque = new LinkedList<>();

        public void add(int data){
            deque.addLast(data);
        }

        public int remove(){
            return deque.removeFirst();
        }

        public int peek(){
            return deque.getFirst();
        }

    }

    public static void main(String[] args) {
        stack s = new stack();
        s.push(1);
        s.push(2);
        s.push(3);
        System.out.println(s.peek());
        System.out.println(s.pop());
        System.out.println(s.peek());
        Queue q = new Queue();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.peek());
        System.out.println(q.remove());
        System.out.println(q.peek());


        java.util.Deque<Integer> deque = new LinkedList<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);   
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        System.out.println(deque);
        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());

    }
}
