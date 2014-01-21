import java.io.*;
import io.*;


public class UnitTestOre
{	
	public static void main(String args[])
	{
        int iNumPassed;
        int iNumTests;
        Ore ore;
        int iOreType;
        String sUnits;

        iNumPassed = 0;
        iNumTests = 0;

        // Test with normal conditions (shouldn't throw exceptions)

        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Constructor");
        System.out.println("=======================================");

        try {   
            iNumTests++;
            System.out.print("Testing creation of Ore: ");
            ore = new Ore(Ore.ORETYPE_IRON, "t");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inOreType: ");
            iOreType = ore.getOreType();
            if (iOreType != Ore.ORETYPE_IRON)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing inUnits: ");
            sUnits = ore.getUnits();
            if (sUnits.equals("t") == false)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

            System.out.println("\n");
            System.out.println("Testing Normal Conditions - Setters and Getters");
            System.out.println("===============================================");

            iNumTests++;
            System.out.print("Testing setUnits('g'): ");
            ore.setUnits("g");	
            iNumPassed++;
            System.out.println("passed");

            iNumTests++;
            System.out.print("Testing getUnits(): ");
            sUnits = ore.getUnits();
            if (sUnits.equals("g") == false)
                throw new IllegalArgumentException("FAILED");
            iNumPassed++;
            System.out.println("passed");

        } catch(Exception e) { System.out.println("FAILED");}

        // Tests with error conditions (SHOULD throw exceptions)
        // Testing constructor's parameters of ore type and units.

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Constructor");
        System.out.println("======================================");

        // Testing ORE TYPE
        // This test would also be redundant if they choose to use enums instead of constants.

        try {
            iNumTests++;
            System.out.print("Testing constructor (inOreType=-1): ");
            ore = new Ore(-1, "t");
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing constructor (inOreType=33): ");
            ore = new Ore(33, "t");
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // Testing UNITS
        try {
            iNumTests++;
            System.out.print("Testing constructor (inUnits='a'): ");
            ore = new Ore(Ore.ORETYPE_NICKEL, "a");	
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            System.out.print("Testing constructor (inUnits=''): ");
            ore = new Ore(Ore.ORETYPE_NICKEL, "");	
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        // Testing setters and getters - note we don't have an Ore Type setter
        // so we can't test whether the getter is accurate.

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Setters");
        System.out.println("==================================");

        // Testing UNITS
        try {
            iNumTests++;
            ore = new Ore(Ore.ORETYPE_NICKEL, "kg");
            System.out.print("Testing setUnits('') (units=''): ");
            ore.setUnits("");
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
            iNumTests++;
            ore = new Ore(Ore.ORETYPE_NICKEL, "kg");
            System.out.print("Testing setUnits('abc') (units not equal to 't', 'kg', 'g'): ");
            ore.setUnits("abc");
            System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
    }
}
