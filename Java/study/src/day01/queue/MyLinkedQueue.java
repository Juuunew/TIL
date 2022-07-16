package day01.queue;

import day01.linkedList.MyLinkedList;
import day01.linkedList.MyListNode;

interface Queue {
    public void enQueue(String data);
    public String deQueue();
    public void printQueue();
}

public class MyLinkedQueue extends MyLinkedList implements Queue {

    MyListNode front;
    MyListNode rear;

    @Override
    public void enQueue(String data) {
        MyListNode newNode;

        if (isEmpty()) {    // 비어있는 Queue 에 맨 처음으로 add
            newNode = addElement(data);
            front = newNode;
            rear = newNode;
        } else {
            newNode = addElement(data);
            rear = newNode;
        }

        System.out.println(newNode.getData() + " added");
    }

    @Override
    public String deQueue() {

        if (isEmpty()) {
            return null;
        }

        String data = front.getData();
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return data;
    }

    @Override
    public void printQueue() {
        printAll();
    }
}
