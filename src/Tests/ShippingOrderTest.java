package Tests; /**
 * Created with IntelliJ IDEA.
 * User: Bill
 * Date: 17/08/13
 * Time: 6:00 PM
 * To change this template use File | Settings | File Templates.
 */
import static org.junit.Assert.*;

import io.Ore;
import io.OreTypes;
import io.ShipmentOrder;
import io.WeightUnits;
import org.junit.Assert;
import org.junit.Test;

public class ShippingOrderTest
{

    @Test
    public void Test_Builder_Correct_Construction()
    {
        //Arrange
        String inName = "William Anderson";
        int inOrderedMetalWeight = 15;
        String inAddress = "123 Fake Street";
        int inUnitPrice = 100;
        Ore inOre = new Ore(OreTypes.Nickel, WeightUnits.Kilo);

        ShipmentOrder order = new ShipmentOrder.Builder()
                    .CustomerName(inName)
                    .Ore(inOre)
                    .orderedMetalWeight(inOrderedMetalWeight)
                    .shippingAddress(inAddress)
                    .UnitPrice(inUnitPrice)
                    .build();

        //Act

        //Assert
        Assert.assertEquals(order.getOre(), inOre);
        Assert.assertEquals(order.getCustomerName(), inName);
        Assert.assertEquals(order.getOrderedMetalWeight(), inOrderedMetalWeight, 0);
        Assert.assertEquals(order.getShippingAddress(), inAddress);
        Assert.assertEquals(order.getUnitPrice(), inUnitPrice, 0);


    }

    @Test(expected = IllegalArgumentException.class)
    public void Test_Builder_CustomerName_Null()
    {

        //Arrange
        String inName = null;
        int inOrderedMetalWeight = 15;
        String inAddress = "123 Fake Street";
        int inUnitPrice = 100;
        Ore inOre = new Ore(OreTypes.Nickel, WeightUnits.Kilo);

        ShipmentOrder order = new ShipmentOrder.Builder()
                .CustomerName(inName)
                .Ore(inOre)
                .orderedMetalWeight(inOrderedMetalWeight)
                .shippingAddress(inAddress)
                .UnitPrice(inUnitPrice)
                .build();

        //Act

        //Assert


    }

    @Test(expected = IllegalArgumentException.class)
    public void Test_Builder_Ore_Null()
    {
        //Arrange
        String inName = "William Anderson";
        int inOrderedMetalWeight = 15;
        String inAddress = "123 Fake Street";
        int inUnitPrice = 100;
        Ore inOre = null;

        ShipmentOrder order = new ShipmentOrder.Builder()
                .CustomerName(inName)
                .Ore(inOre)
                .orderedMetalWeight(inOrderedMetalWeight)
                .shippingAddress(inAddress)
                .UnitPrice(inUnitPrice)
                .build();


        //Act

        //Assert
    }

    @Test(expected = IllegalArgumentException.class)
    public void Test_Builder_OrderedMetalWeight_Invalid()
    {


        String inName = "William Anderson";
        int inOrderedMetalWeight = -1;
        String inAddress = "123 Fake Street";
        int inUnitPrice = 100;
        Ore inOre = new Ore(OreTypes.Nickel, WeightUnits.Kilo);

        ShipmentOrder order = new ShipmentOrder.Builder()
                .CustomerName(inName)
                .Ore(inOre)
                .orderedMetalWeight(inOrderedMetalWeight)
                .shippingAddress(inAddress)
                .UnitPrice(inUnitPrice)
                .build();

        //Act

        //Assert
    }

    @Test(expected = IllegalArgumentException.class)
    public void Test_Builder_ShippingAddress_Null()
    {
        //Arrange
        String inName = "William Anderson";
        int inOrderedMetalWeight = 15;
        String inAddress = null;
        int inUnitPrice = 100;
        Ore inOre = new Ore(OreTypes.Nickel, WeightUnits.Kilo);

        ShipmentOrder order = new ShipmentOrder.Builder()
                .CustomerName(inName)
                .Ore(inOre)
                .orderedMetalWeight(inOrderedMetalWeight)
                .shippingAddress(inAddress)
                .UnitPrice(inUnitPrice)
                .build();

        //Act

        //Assert
    }
    @Test(expected = IllegalArgumentException.class)
    public void Test_Builder_UnitPrice_Invalid()
    {

        //Arrange
        String inName = "William Anderson";
        int inOrderedMetalWeight = 15;
        String inAddress = "123 Fake Street";
        int inUnitPrice = -1;
        Ore inOre = new Ore(OreTypes.Nickel, WeightUnits.Kilo);

        ShipmentOrder order = new ShipmentOrder.Builder()
                .CustomerName(inName)
                .Ore(inOre)
                .orderedMetalWeight(inOrderedMetalWeight)
                .shippingAddress(inAddress)
                .UnitPrice(inUnitPrice)
                .build();

        //Act

        //Assert
    }

    @Test
    public void Test_CalcShipmentValue()
    {

        //Act
        String inName = "William Anderson";
        int inOrderedMetalWeight = 15;
        String inAddress = "123 Fake Street";
        int inUnitPrice = 100;
        Ore inOre = new Ore(OreTypes.Nickel, WeightUnits.Kilo);

        ShipmentOrder order = new ShipmentOrder.Builder()
                .CustomerName(inName)
                .Ore(inOre)
                .orderedMetalWeight(inOrderedMetalWeight)
                .shippingAddress(inAddress)
                .UnitPrice(inUnitPrice)
                .build();



        //Act

        double result = order.calcShipmentValue();

        //Assert

        assertEquals(result, inOrderedMetalWeight * inUnitPrice, 0);
    }
}
