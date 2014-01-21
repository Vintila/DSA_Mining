package io;

import java.util.Iterator;

public class DSAStack<T> implements Iterable<T>
{

    private DSALinkedList<T> Stack;
    private int StackPointer;
    private int Count;

    public DSAStack()
    {
        Stack = new DSALinkedList<T>();

        StackPointer = 0;

        Count = 0;
    }

    public int getCount()
    {
        return Count;
    }

    public boolean isEmpty()
    {
        return Count == 0;
    }

    public void push(T inData)
    {
        if (inData == null)
            throw new NullPointerException("Data cannot be null");

        Stack.insertFirst(inData);
        Count++;
    }

    public T pop()
    {
        if (this.isEmpty())
            throw new ArrayIndexOutOfBoundsException("Stack is empty");

        T output = Stack.removeFirst();
        Count--;

        return output;

    }

    public T top()
    {
        if (this.isEmpty())
            throw new ArrayIndexOutOfBoundsException("Stack is Empty");

        T output = Stack.peekFirst();
        return output;
    }

    public Iterator<T> iterator()
    {
        return Stack.iterator();
    }
}
