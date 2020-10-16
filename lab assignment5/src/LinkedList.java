public class LinkedList<E> {

    private Node<E> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void print() {
        Node temp = head;
        while (temp.next != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println(temp.data);
    }

    private class Node<E> {
        E data;
        Node<E> next;


        public Node(E value) {
            data = value;
            next = null;

        }
    }

    public E get(int pos) {
        Node curr = head;
        for (int i = 0; i < pos; i++) {
            curr = curr.next;
        }
        System.out.println(curr.data);
        return (E) curr.data;
    }

    public boolean add(E item) {
        Node node = new Node(item);

        if (head == null) {
            head = node;
        } else {
            Node prev = head;
            while (prev.next != null)
                prev = prev.next;
            prev.next = node;
        }

        ++size;
        return true;
    }

    public void add(int pos, E item) {
        Node node = new Node(item);
        if (pos == 0) {
            node.next = head;
            head = node;
            ++size;
        } else {
            Node prev = head;
            int curPos = 0;
            while (curPos < pos - 1 && prev.next != null) {
                prev = prev.next;
            }
            node.next = prev.next;
            prev.next = node;
            ++size;
        }

    }

    public E remove(int pos) {
        if (pos == 0) {
            Node node = head;
            head = head.next;
            --size;
            return (E) node.data;
        } else {
            Node prev = head;
            for (int i = 0; i < pos - 1; i++) {
                prev = prev.next;
            }
            Node node = prev.next;
            prev.next = node.next;
            --size;
            return (E) node.data;
        }
    }
    public void reverse() {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;

        }

        this.head = prev;
    }




    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add(3, "4");
        list.reverse();
        list.print();
        //list.get(0);

        //list.remove(0);

        //list.print();
    }
}

