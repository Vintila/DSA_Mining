package Old;

public class DSAStackArray
{
    private Object[] Stack;
    private int StackPointer;
    private int Count;
    private final int MaxStackSize;

    public DSAStackArray(int inStackSize)
    {

        Stack = new Object[inStackSize];

        StackPointer = 0;

        MaxStackSize = inStackSize;

        Count = 0;
    }

    public DSAStackArray()
    {

        Stack = new Object[100];

        StackPointer = 0;

        MaxStackSize = 100;

        Count = 0;

    }

    public int getCount()
    {
        return Count;
    }

    public boolean isFull()
    {
        return Count == MaxStackSize;
    }

    public boolean isEmpty()
    {
        return Count == 0;
    }

    public void push(Object inData)
    {
        if (inData == null)
            throw new NullPointerException("Data cannot be null");
        if (this.isFull())
            throw new ArrayIndexOutOfBoundsException("Stack is full");

        Stack[StackPointer++] = inData;
        Count++;
    }

    public Object pop()
    {
        if (this.isEmpty())
            throw new ArrayIndexOutOfBoundsException("Stack is empty");

        Object output = Stack[--StackPointer];
        Count--;

        return output;

    }

    public Object top()
    {
        if (this.isEmpty())
            throw new ArrayIndexOutOfBoundsException("Stack is Empty");

        Object output = Stack[StackPointer - 1];
        return output;
    }
}
