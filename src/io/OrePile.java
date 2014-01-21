package io;

/**
 * Created with IntelliJ IDEA.
 * User: William
 * Date: 8/13/13
 * Time: 8:59 AM
 * To change this template use File | Settings | File Templates.
 */

public class OrePile
{

    private double Weight;
    private double Grade;
    private Ore Ore;

    /**
     *  Creates a new Orepile
     *
     * @param inOre The io.Ore object that the pile contains
     * @param inWeight The Weight of ore in this pile
     * @param inGrade  Grade of ore in decimal between 0 and 100
     */
    public OrePile(Ore inOre, double inWeight, double inGrade)
    {
        setWeight(inWeight);
        setGrade(inGrade);
        setOre(inOre);
    }

    public OrePile(OrePile inOrePile)
    {
        setWeight(inOrePile.getWeight());
        setGrade(inOrePile.getGrade());
        setOre(inOrePile.getOre());

    }

    /** Getter for the Ore contained in this pile
     *
     * @return returns the Ore contained in the pile
     */
    public Ore getOre()
    {
        return Ore;
    }

    private void setOre(Ore inOre)
    {
        if (inOre == null)
        {
            throw new IllegalArgumentException("Ore must not be null");
        }
        Ore = inOre;
    }


    /**
     * Gets the grade of the Ore
     * @return returns the grade of the io.Ore
     */
    public double getGrade()
    {
        return Grade;
    }

    /**Setter for Grade of Orepile
     *
     * @param inGrade The new grade to be assigned
     */
    public void setGrade(double inGrade)
    {
        if (inGrade <= 0 || inGrade > 100)
        {
            throw new IllegalArgumentException("Grade must be above 0 and below 100");
        }

        Grade = inGrade;
    }

    /** Getter for Weight
     *
     * @return returns the Weight of the io.Ore Pile
     */
    public double getWeight()
    {

        return Weight;

    }

    /** Setter for Weight of Orepile
     *
     * @param inWeight the new Weight to be assigned
     */
    public void setWeight(double inWeight)
    {


        if (inWeight <= 0)
        {
            throw new IllegalArgumentException("Weight must be greater than 0");
        }

        Weight = inWeight;

    }

    /** Calculates the actual Metal Weight for this io.Ore Pile
     *
     * @return returns the Metal Weight for this io.Ore Pile
     */
    public double calcMetalWeight()
    {
        return Weight * (Grade / 100);

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
        final OrePile converted = (OrePile) obj;
        return (this.getGrade() == converted.getGrade() && this.getWeight() == converted.getWeight() && this.getOre().equals(converted.getOre()));
    }

}
