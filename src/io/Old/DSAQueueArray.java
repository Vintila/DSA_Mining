package Old;

import java.lang.*;

public class DSAQueueArray
{
    private Object[] Queue;
    private int QueueOutputPointer;
    private int QueueInputPointer;
    private int Count;
    private int QueueSize;

    public DSAQueueArray(int inQueueSize)
    {

        Queue = new Object[inQueueSize];
        QueueInputPointer = 0;

        QueueSize = inQueueSize;

        QueueOutputPointer = 0;

        Count = 0;
    }

    public DSAQueueArray()
    {

        Queue = new Object[100];
        QueueInputPointer = 0;

        QueueSize = 100;

        QueueOutputPointer = 0;

        Count = 0;
    }

    public void enqueue(Object inData)
    {
        if (inData == null)
            throw new NullPointerException("Data must not be Null");
        if (this.isFull())
            throw new ArrayIndexOutOfBoundsException("Queue is Full");

        Queue[QueueInputPointer % QueueSize] = inData;
        QueueInputPointer = (QueueInputPointer % QueueSize) + 1;
        Count++;
    }

    public Object dequeue()
    {
        if (this.isEmpty())
            throw new ArrayIndexOutOfBoundsException("Queue is Empty");

        if (this.isFull())
            throw new ArrayIndexOutOfBoundsException("Queue is Full");

        Object output = Queue[QueueOutputPointer % QueueSize];
        QueueOutputPointer = (QueueOutputPointer % QueueSize) + 1;
        Count--;

        return output;

    }

    public Object peek()
    {
        if (this.isEmpty())
            throw new ArrayIndexOutOfBoundsException("Queue is empty");

        return Queue[QueueOutputPointer];
    }

    public boolean isFull()
    {
        return Count >= QueueSize;
    }

    public boolean isEmpty()
    {
        return Count == 0;
    }

    public int getCount()
    {

        return Count;
    }
}
