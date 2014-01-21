package Tests; /**
 * Created with IntelliJ IDEA.
 * User: William
 * Date: 8/14/13
 * Time: 12:48 PM
 * To change this template use File | Settings | File Templates.
 */

import io.OreTypes;
import io.WeightUnits;
import org.junit.*;
import org.junit.Assert;
import io.Ore;

public class OreTests
{

    @Test
    public void Test_Ore_Correct_Construction()
    {
        //Arrange
        Ore ore = new Ore(OreTypes.Iron, WeightUnits.Tonnes);

        //Act

        //Assert

        Assert.assertEquals(ore.getOreType(), OreTypes.Iron);

        Assert.assertEquals(ore.getUnits(), WeightUnits.Tonnes);

    }

    @Test(expected = NullPointerException.class)
    public void Test_Null_WeightUnits_Constructor_Arguments()
    {

        //Arrange
        Ore ore = new Ore(OreTypes.Iron, null);
        //Act

        //Assert
    }

    @Test(expected = NullPointerException.class)
    public void Test_Null_OreType_Constructor_Arguments()
    {

        //Arrange
        Ore ore = new Ore(null, WeightUnits.Tonnes);

        //Act

        //Assert
    }

    @Test
    public void Test_Copy_Constructor()
    {
        //Arrange
        Ore original = new Ore(OreTypes.Iron, WeightUnits.Tonnes);
        Ore copied;
        Ore notCopy;


        //Act

        copied = new Ore(original);
        notCopy = new Ore(OreTypes.Nickel, WeightUnits.Kilo);

        //Assert
        Assert.assertEquals(original, copied);

        Assert.assertNotEquals(original, notCopy);

    }




}
