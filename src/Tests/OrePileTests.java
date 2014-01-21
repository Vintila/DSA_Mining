package Tests;
/**
 * Created with IntelliJ IDEA.
 * User: Bill
 * Date: 17/08/13
 * Time: 12:46 PM
 * To change this template use File | Settings | File Templates.
 */
import io.*;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrePileTests
{
    @Test
    public void Test_OrePile_Correct_Construction()
    {
        //Arrange
        double oreWeight = 15;
        double oreGrade = 30;
        double delta = 0;
        OrePile pile = new OrePile(new Ore(OreTypes.Iron, WeightUnits.Kilo), oreWeight, oreGrade);

        //Act

        //Assert
        Assert.assertEquals(pile.getGrade(), oreGrade, delta);
        Assert.assertEquals(pile.getWeight(), oreWeight, delta);
        Assert.assertEquals(pile.getOre(), new Ore(OreTypes.Iron, WeightUnits.Kilo));
    }

    @Test(expected = IllegalArgumentException.class)
    public void Test_Low_Weight_Constructor_Arguments()
    {
        //Arrange
        double oreWeight = -1;
        double oreGrade = 30;
        OrePile pile = new OrePile(new Ore(OreTypes.Iron, WeightUnits.Kilo), oreWeight, oreGrade);

        //Act

        //Assert

    }

    @Test(expected = IllegalArgumentException.class)
    public void Test_Low_Grade_Constructor_Arguments()
    {
        //Arrange
        double oreWeight = 15;
        double oreGrade = -1;
        OrePile pile = new OrePile(new Ore(OreTypes.Iron, WeightUnits.Kilo), oreWeight, oreGrade);

        //Act

        //Assert

    }

    @Test(expected = IllegalArgumentException.class)
    public void Test_High_Grade_Constructor_Arguments()
    {
        //Arrange
        double oreWeight = 15;
        double oreGrade = 101;
        OrePile pile = new OrePile(new Ore(OreTypes.Iron, WeightUnits.Kilo), oreWeight, oreGrade);

        //Act

        //Assert

    }

    @Test(expected = IllegalArgumentException.class)
    public void Test_Null_Ore_Constructor_Arguments()
    {
        //Arrange
        OrePile pile = new OrePile(null, 15, 15);

        //Act

        //Assert

    }

    @Test
    public void Test_Calc_Metal_Weight()
    {
        //Arrange
        double oreWeight = 100;
        double oreGrade = 15;
        double expectedWeight = 15;
        double delta = 0;
        OrePile pile = new OrePile(new Ore(OreTypes.Iron, WeightUnits.Kilo), oreWeight, oreGrade);


        //Act

        //Assert
        Assert.assertEquals(pile.CalcMetalWeight(), expectedWeight, delta);


    }

    @Test
    public void Test_Copy_Constructor()
    {
        //Arrange
        double oreWeight = 15;
        double oreGrade = 30;
        double delta = 0;
        OrePile original = new OrePile(new Ore(OreTypes.Iron, WeightUnits.Kilo), oreWeight, oreGrade);

        //Act
        OrePile copied = new OrePile(original);

        //Assert
        assertTrue(original.equals(copied));
    }

    @Test
    public void Test_Equals_Override()
    {

        //Arrange
        double oreWeight = 15;
        double oreGrade = 30;
        OrePile original = new OrePile(new Ore(OreTypes.Iron, WeightUnits.Kilo), oreWeight, oreGrade);
        OrePile copy = new OrePile(new Ore(OreTypes.Iron, WeightUnits.Kilo), oreWeight, oreGrade);
        OrePile different = new OrePile(new Ore(OreTypes.Nickel, WeightUnits.Tonnes), oreWeight+1, oreGrade-1);

        //Act


        //Assert
        assertTrue(original.equals(copy));
        assertFalse(original.equals(different));

    }

}
