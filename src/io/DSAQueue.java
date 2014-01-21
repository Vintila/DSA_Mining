package io;

import java.lang.*;
import java.util.Iterator;

public class DSAQueue<T> implements Iterable<T>
{
    private DSALinkedList<T> Queue;
    private int Count;

    public DSAQueue()
    {
        Queue = new DSALinkedList<T>();

        Count = 0;
    }

    /** Adds an item to the back of the queue
     *
     * @param inData the data to be added to the queue
     */
    public void enqueue(T inData)
    {
        if (inData == null)
            throw new NullPointerException("Data must not be Null");

        Queue.insertFirst(inData);

        Count++;
    }

    /**returns and removes the first item in the queue
     * @throws ArrayIndexOutOfBoundsException if the queue is empty
     * @return returns the data from the front of the queue
     */
    public T dequeue()
    {
        if (this.isEmpty())
            throw new ArrayIndexOutOfBoundsException("Queue is Empty");

        T output = Queue.removeLast();

        Count--;

        return output;

    }

    /**Retreives the first item of the queue without removing it
     *
     * @throws ArrayIndexOutOfBoundsException if the queue is empty
     *
     * @return returns the first item of the queue
     */
    public T peek()
    {
        if (this.isEmpty())
            throw new ArrayIndexOutOfBoundsException("Queue is empty");

        return Queue.peekLast();
    }

    /**Checks to see if the queue contains any data
     *
     * @return returns true if the queue is empty otherwise returns false
     */
    public boolean isEmpty()
    {
        return Count == 0;
    }

    /**Gets the number of items in the queue
     *
     * @return returns the number of items in the queue
     */
    public int getCount()
    {
        return Count;
    }

    /** Provides the iterator for the queue
     *
     * @return returns the iterator
     */

    @Override
    public Iterator<T> iterator()
    {
        return Queue.iterator();
    }
}
