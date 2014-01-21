package io;

/**
 * Created with IntelliJ IDEA.
 * User: Bill
 * Date: 17/08/13
 * Time: 5:02 PM
 * To change this template use File | Settings | File Templates.
 */

public class ShipmentOrder
{

    private static int nextOrderID = 0;
    private final int orderID;
    private Ore ore;
    private double unitPrice;
    private String customerName;
    private String shippingAddress;
    private double orderedMetalWeight;
    private double shippedOreWeight;
    private boolean isPending;


    /** Creates a new Shipping Order object
     *
     * @param inName the Customers Name
     * @param inAddress the Customers Address
     * @param inOre the type of Ore being ordered
     * @param inOrderedMetalWeight the weight of metal being ordered
     * @param inUnitPrice the price per unit of metal
     */
    public ShipmentOrder(String inName, String inAddress, Ore inOre, double inOrderedMetalWeight, double inUnitPrice)
    {

        orderID = ++nextOrderID;

        setUnitPrice(inUnitPrice);
        setCustomerName(inName);
        setShippingDest(inAddress);
        setOrderedMetalWt(inOrderedMetalWeight);
        setIsPending(true);
        setOre(inOre);

    }

    /**
     * Getter for contained ore
     *
     * @return returns the ore
     */
    public Ore getOre()
    {
        return ore;
    }

    /** Setter for Ore object
     *
     * @param inOre the new Ore to set
     */
    private void setOre(Ore inOre)
    {
        if (inOre == null)
            throw new IllegalArgumentException("io.Ore must not be Null");

        ore = inOre;
    }

    /**Getter for Unit Price
     *
     * @return returns the Unit Price
     */
    public double getUnitPrice()
    {
        return unitPrice;
    }

    /** Setter for Unit Price
     *
     * @param inPrice the new Unit Price
     */
    public void setUnitPrice(double inPrice)
    {
        if (inPrice <= 0)
            throw new IllegalArgumentException("Price must be greater than 0");

        unitPrice = inPrice;
    }

    /**Getter for Customer Name
     *
     * @return returns Customer Name
     */
    public String getCustomerName()
    {
        return customerName;
    }

    /**Setter for Customer Name
     *
     * @param inCustomerName the new Customer Name
     */
    public void setCustomerName(String inCustomerName)
    {
        if (inCustomerName == null || inCustomerName == "")
            throw new IllegalArgumentException("Customer Name must not be Null");

        customerName = inCustomerName;
    }

    /**Getter for Shipping Address
     *
     * @return returns the Shipping Address
     */
    public String getShippingDest()
    {
        return shippingAddress;
    }

    /**Setter for Shipping Destination
     *
     * @param inShippingAddress the new Shipping Destination
     */
    public void setShippingDest(String inShippingAddress)
    {
        if (inShippingAddress == null || inShippingAddress.isEmpty())
            throw new IllegalArgumentException("Shipping Address must not be null");

        shippingAddress = inShippingAddress;
    }

    /**Getter for the Ordered Metal Weight
     *
     * @return returns the Ordered Metal Weight
     */
    public double getOrderedMetalWt()
    {
        return orderedMetalWeight;
    }

    /**Setter for Ordered Metal Weight
     *
     * @param inOrderedMetalWeight the new Metal Weight
     */
    public void setOrderedMetalWt(double inOrderedMetalWeight)
    {
        if (inOrderedMetalWeight <= 0)
            throw new IllegalArgumentException("Ordered Metal must be greater than 0");

        orderedMetalWeight = inOrderedMetalWeight;
    }

    /**Getter for the shipped ore weight
     *
     * @return returns Shipped Ore Weight
     * @throws IllegalArgumentException if the order is still pending
     */
    public double getShippedOreWt()
    {
        //Unknown to me why we are throwing an exception in a getter.
        if (isPending)
            throw new IllegalStateException("Still Pending");
        return shippedOreWeight;
    }

    /** Setter for Shipped Ore Weight
     *
     * @param inShippedOreWeight the new Shipped Ore Weight
     */
    public void setShippedOreWt(double inShippedOreWeight)
    {
        if (inShippedOreWeight <= 0)
            throw new IllegalArgumentException("Shipped Metal must be greater than 0");
        setIsPending(false);

        shippedOreWeight = inShippedOreWeight;
    }

    /**Getter for the pending status of this order
     *
     * @return Returns the pending status
     */
    public boolean getIsPending()
    {
        return isPending;
    }

    /**Setter for Pending status
     *
     * @param inIsPending the new Pending status for this Shipping Order
     */
    public void setIsPending(boolean inIsPending)
    {
        isPending = inIsPending;
    }

    /**Getter for Order ID
     *
     * @return returns the Order ID
     */
    public int getOrderID()
    {
        return orderID;
    }

    /**Calculates the average grade of the ore being shipped
     *
     * @return the average grade of ore
     */
    public double calcAverageGrade()
    {
        if (getIsPending() == true)
            throw new IllegalStateException("No Ore has been shipped to calculate the grade of");

        return (orderedMetalWeight / shippedOreWeight) * 100;
    }

    /**Calculates the cost of the shipment
     *
     * @return returns the Shipment Cost
     */
    public double calcShipmentValue()
    {
        return orderedMetalWeight * unitPrice;

    }

}

