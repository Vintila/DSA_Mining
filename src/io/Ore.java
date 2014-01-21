package io;


/**
 * Created with IntelliJ IDEA.
 * User: William
 * Date: 8/13/13
 * Time: 8:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class Ore
{

    private String Unit;
    private int Type;

    public static final int ORETYPE_NICKEL = 1;
    public static final int ORETYPE_IRON = 2;

    /**
     * @param inType The type of Ore being created
     * @param inUnit The weight unit used to measure this Ore
     * @throws NullPointerException if passed any null references
     */
    public Ore(int inType, String inUnit)
    {
        setOreType(inType);
        setUnits(inUnit);
    }

    /**
     * Copy Constructor
     *
     * @param inOre The ore that you want to copy
     */
    public Ore(Ore inOre)
    {
        setOreType(inOre.getOreType());
        setUnits(inOre.getUnits());
    }

    /**
     * Getter for Ore type
     *
     * @return returns the type of the io.Ore
     */
    public int getOreType()
    {
        return Type;
    }

    /**
     * Getter for Weight Unit
     *
     * @return returns the Weight Unit
     */
    public String getUnits()
    {
        return Unit;
    }

    /**Setter for Weight Units
     *
     * @param inUnit New Weight Unit to be assigned
     */
    public void setUnits(String inUnit)
    {

        if ( inUnit != "g" && inUnit != "k" && inUnit != "t" )
            throw new IllegalArgumentException("Weight Units must be either 'g' or ' k' or 't'");

        Unit = inUnit;

    }

    /**Setter for Ore Type
     *
     * @param inType New oretype to be assigned
     */
    public void setOreType(int inType)
    {
        if (inType != ORETYPE_IRON && inType != ORETYPE_NICKEL)
        {
            throw new IllegalArgumentException("Ore Type be IRON or NICKEL received:" + inType);
        }

        Type = inType;

    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Ore converted = (Ore) obj;
        return (this.getOreType() == converted.getOreType() && this.getUnits() == converted.getUnits());
    }


}

