package day01.linkedList;

public class LinkedList {

    private String data;
    public LinkedList next;

    public LinkedList() {
        data = null;
        next = null;
    }

    public LinkedList(String data) {
        this.data = data;
        this.next = null;
    }

    public LinkedList(String data, LinkedList link) {
        this.data = data;
        this.next = link;
    }

    public String getData() {
        return data;
    }
}
