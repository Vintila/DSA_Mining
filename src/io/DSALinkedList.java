package io;

import java.util.NoSuchElementException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Bill
 * Date: 25/08/13
 * Time: 5:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class DSALinkedList<T> implements Iterable<T>
{
    private Node head = null;
    private Node tail = null;
    private int count = 0;

    public DSALinkedList()
    {


    }

    public void insertFirst(T inData)
    {
        if (isEmpty())
        {
            Node node = new Node(inData, null);
            head = node;
            tail = node;
        } else
        {
            Node node = new Node(inData, head);
            head = node;
        }
        count++;
    }

    public void insertLast(T inData)
    {

        if (isEmpty())
        {
            Node node = new Node(inData, null);
            head = node;
            tail = node;

        }
	else
        {
            Node node = new Node(inData, null);
            tail.nextNode = node;
            tail = node;

        }
        count++;
    }

    public T removeFirst()
    {
        if (isEmpty())
            throw new NoSuchElementException("Linked list is Empty");

        T data = head.data;
        head = head.nextNode;
        count--;
        return data;
    }

    public T removeLast()
    {
        T output;
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException("Linked List is empty");

        if (count == 1)
        {
            output = tail.data;
            tail = null;
            head = null;
        }
	else
        {
            Node currentNode = head;

            while (currentNode.nextNode.nextNode != null)
            {
                currentNode = currentNode.nextNode;

            }

            output = currentNode.nextNode.data;

            tail = currentNode;
            currentNode.nextNode = null;
        }
        count--;

        return output;

    }

    public T peekLast()
    {
        T data;

        if (isEmpty())
            data = null;
        else
            data = tail.data;
        return data;

    }

    public T peekFirst()
    {
        T data;

        if (isEmpty())
            data = null;
        else
            data = head.data;

        return data;
    }

    public int Size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return count == 0;
    }

    public Iterator<T> iterator()
    {
        return new DSALinkedListIterator();
    }

    private class DSALinkedListIterator implements Iterator<T>
    {
        Node currentNode;

        public DSALinkedListIterator()
        {
            currentNode = head;
        }

        public boolean hasNext()
        {
            return currentNode != null;
        }

        public T next()
        {
            if (!hasNext())
                throw new NoSuchElementException("Iterator has no more Elements");
            T Data = currentNode.data;
            currentNode = currentNode.nextNode;
            return Data;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Unsupported Operation");
        }
    }

    private class Node
    {

        T data;
        Node nextNode;

        public Node(T inData, Node inNode)
        {
            data = inData;
            nextNode = inNode;
        }

    }
}
