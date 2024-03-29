package io;

import java.util.*;	// for Iterator and Iterable

/**
 * Represents a FIFO (first-in-first-out) Shed for storing OrePiles in the STMining application.
 *
 * @author	Patrick Peursum
 * @version	5.0
 */

public class ShedNickel implements IShed 
{

/************************************************************
 * MEMBER FIELDS
 ************************************************************/

/**
  * Shed name
  */
   private String m_sName;

/**
  * Type of ore that this shed can contain (allows for more than just Nickel later on)
  */
   private int m_oreType;

/**
  * Stack of OrePiles in the Shed
  */
   private DSAStack<OrePile> m_orePileStack;


/************************************************************
 * CONSTRUCTORS
 ************************************************************/

/**
  * io.ShedNickel constructor
  *
  * @param name	Name of the shed to allow later lookup by name
  */
   public ShedNickel(String name) 
   {
      setName(name);
      // For now we hardcode to Nickel, but could make it flexible
      m_oreType = Ore.ORETYPE_NICKEL;
      m_orePileStack = new DSAStack<OrePile>();
   }


/************************************************************
 * METHODS
 ************************************************************/

/**
  * Shed name accessor
  *
  * @return		Name of the shed
  */
   public String getName() 
   {
      return m_sName;
   }

/**
  * Shed name mutator
  *
  * @param newName	New name for the shed
  */
   public void setName(String newName) 
   {
      if ((newName == null) || (newName.equals("")))
         throw new IllegalArgumentException("name must not be blank");

      m_sName = newName;
   }

/**
  * Shed ore type accessor
  *
  * @return		Ore type of the shed
  */
   public int getOreType() 
   {
      return m_oreType;
   }

   
/**
  * Add a new pile to this Shed (in FIFO order)
  *
  * @param newPile	OrePile to add to the shed
  */
   public void addPile(OrePile newPile) 
   {
      // Ensure the ore types match - eg: mustn't add Iron to an Nickel shed!
      if (newPile.getOre().getOreType() != m_oreType)
         throw new IllegalArgumentException("OreType of the OrePile must be Ore.ORETYPE_NICKEL");

      m_orePileStack.push(newPile);	// Should we make a copy of newPile? Your choice...
   }


/**
  * Calculate and return the total weight of ore in this shed
  *
  * @return		Total ore weight over all OrePiles in the shed
  */
   public double calcTotalOreWeight() 
   {
      double fTotalOre;

      fTotalOre = 0.0;
      for (OrePile pile : m_orePileStack) {	// For this to work, Iterator must be implemented in io.DSAStack
         fTotalOre += pile.getWeight();
      } 

      return fTotalOre;
   }


/**
  * Calculate and return the total weight of metal in this shed
  *
  * @return		Total metal weight over all OrePiles in the shed
  */
   public double calcTotalMetalWeight() 
   {
      double fTotalMetal;

      fTotalMetal = 0.0;
      for (OrePile pile : m_orePileStack) {	// For this to work, Iterator must be implemented in io.DSAStack
         fTotalMetal += pile.calcMetalWeight();
      } 

      return fTotalMetal;
   }


/**
  * Attempt to take out enough OrePiles to satisfy a given ShipmentOrder.
  * If there is enough metal in the Shed, OrePiles will be taken out one at a time
  * and their total weight added up until the requested metal amount is satisfied.
  * If there is not enough metal an IllegalStateException is thrown.
  *
  * @param order	ShipmentOrder to be fulfilled
  * @return		The weight of ore to load onto the ship to satisfy the ordered metal
  */
   public double satisfyOrder(ShipmentOrder order)
   {
      double fOreToLoad, fOreToTake, fMetalRemaining;
      OrePile pile;

      if (order.getOrderedMetalWt() > calcTotalOreWeight())
         throw new IllegalStateException("Not enough ore in this Shed to fulfill the order");

      fMetalRemaining = order.getOrderedMetalWt();
      fOreToLoad = 0.000;

      while (fMetalRemaining > 0.) {
         pile = m_orePileStack.top();

         if (fMetalRemaining >= pile.calcMetalWeight()) {
            // Take this whole pile and dequeue it
            pile = m_orePileStack.top();
            m_orePileStack.pop();
            fOreToLoad += pile.getWeight();
            fMetalRemaining -= pile.calcMetalWeight();
         }
         else {
            // Take part of this pile but leave it on the queue
            fOreToTake = fMetalRemaining / (pile.getGrade() / 100.0);
            pile.setWeight(pile.getWeight() - fOreToTake);
            fOreToLoad += fOreToTake;
            fMetalRemaining = 0.0;	// We're done
          }
      }

      fMetalRemaining = 0;
      return fOreToLoad;
   }

   
/**
  * Provide an Iterator to iterate over the OrePiles currently in the Shed
  *
  * @return		An Iterator for this Shed (actually, io.DSAStack's Iterator)
  */
   public Iterator<OrePile> iterator() 
   {
      return m_orePileStack.iterator();
   }

}

