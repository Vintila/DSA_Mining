package io;

public class DSAShipmentManager
{
    /**
     * *************************************************************************
     * Instance Members
     * *************************************************************************
     */
    private DSAQueue<ShipmentOrder> orderQueue = new DSAQueue<ShipmentOrder>();
    private DSALinkedList<IShed> shedList = new DSALinkedList<IShed>();

    /**
     * *************************************************************************
     * Public Methods
     * *************************************************************************
     */
    public void run()
    {
        uiMainMenuChoice();
    }


    /****************************************************************************
     *                          Private Methods
     ***************************************************************************/

    /**
     * Menu navigation
     */
    private void uiMainMenuChoice()
    {
        int converted;

        do
        {
            uiDisplayMenuMenu();
            //Get the users choice
            converted = ConsoleInput.readInt();

            switch (converted)
            {
                case 1:
                    uiAddNewShed();
                    break;
                case 2:
                    uiOreDeliveryEntry();
                    break;
                case 3:
                    uiShowShedContents();
                    break;
                case 4:
                    uiOrderEntry();
                    break;
                case 5:
                    uiShowPendingOrders();
                    break;
                case 6:
                    uiProcessNextOrder();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Enter valid option");
            }

        } while (converted != 7);

    }

    /**
     * Adds an orepile to the shed selected by the user
     */
    private void uiOreDeliveryEntry()
    {

        if (shedList.isEmpty())
        {
            System.out.println("\nNo sheds to deliver to");
            return;
        }

        String shedName;
        double Grade;
        double Weight;
        IShed shed;


        System.out.println("\nNew Ore Delivery\n");

        //Gets a valid Shed to deliver to
        do
        {
            uiShowShedNames();
            System.out.println("Enter the name of the shed to deliver to (non-case sensitive):");
            shedName = ConsoleInput.readLine();

            shed = findShedByName(shedName);

        } while (shed == null);

        //Gets a valid grade for this delivery
        do
        {

            System.out.println("\nEnter Grade for this delivery (between 0 and 100):");
            Grade = ConsoleInput.readDouble();

        } while (!(Grade >= 0 && Grade <= 100));

        //Gets a valid Weight for this delivery
        do
        {
            System.out.println("\nEnter Weight for this delivery (greater than 0):");
            Weight = ConsoleInput.readDouble();
        } while (!(Weight > 0));

        //Adds the new pile to the chosen shed.
        shed.addPile(new OrePile(new Ore(shed.getOreType(), "t"), Weight, Grade));

    }


    /**
     * Prints out the list of all sheds
     */
    private void uiShowShedNames()
    {
        System.out.println("\nPrinting Shed List");

        for (IShed shed : shedList)
        {                                                                                                       //inline conditional statement for changing the integer representation of the ore type to a human readable string
            System.out.println(String.format("\nName: %15s | Type:%8s | Weight:%6.2f", shed.getName(), shed.getOreType() == Ore.ORETYPE_IRON ? "Iron" : "Nickel", shed.calcTotalOreWeight()));


        }
    }

    /**
     * Creates a new order with values provided by the user and adds it to the queue
     */
    private void uiOrderEntry()
    {
        String custName;
        String shippingDest;
        int oreType;
        double metalWeight;
        double price;

        //Gets a valid Customer Name
        do
        {
            System.out.println("\nEnter the Customers name:");
            custName = ConsoleInput.readLine();
        } while (custName.isEmpty());

        //Gets a valid Shipping Destination
        do
        {
            System.out.println("\nEnter the Shipping Address:");
            shippingDest = ConsoleInput.readLine();
        } while (shippingDest.isEmpty());


        //Gets a valid Ore Type
        do
        {
            System.out.println("\nEnter Ore Type(1 = Nickel, 2 = Iron):");
            oreType = ConsoleInput.readInt();
        } while (oreType != Ore.ORETYPE_IRON && oreType != Ore.ORETYPE_NICKEL);

        //Gets a valid Metal Weight
        do
        {
            System.out.println("\nEnter the weight of metal for this order(greater than 0):");
            metalWeight = ConsoleInput.readDouble();

        } while (!(metalWeight > 0));

        //Hardcoded price at $110
        price = metalWeight * 110;

        //Creates the new ShipmentOrder
        ShipmentOrder order = new ShipmentOrder(
                custName,
                shippingDest,
                new Ore(oreType, "t"),
                metalWeight,
                price
        );

        //Adds the Order to the Order Queue
        orderQueue.enqueue(order);

        //Prints out the Order ID and Price
        System.out.println(String.format("\nOrder Added " +
                "ID: %d  Price: %.2f", order.getOrderID(), order.getUnitPrice()));

    }

    /**
     * Prints out all pending orders
     */
    private void uiShowPendingOrders()
    {
        System.out.println("\nCurrent Orders:");

        for (ShipmentOrder order : orderQueue)
        {                                                                                                                                                                   //inline conditional statement for changing the integer representation of the ore type to a human readable string
            System.out.println(String.format("ID: %3d | Customer Name: %15s | Ore Type: %5s | Weight: %6.2f | Value: %6.2f\n", order.getOrderID(), order.getCustomerName(), order.getOre().getOreType() == Ore.ORETYPE_IRON ? "Iron" : "Nickel", order.getOrderedMetalWt(), order.getUnitPrice()));
        }
    }

    /**
     * Adds a new shed to the Shed List with values provided by the user
     */
    private void uiAddNewShed()
    {

        int ShedType;

        IShed shed = null;
        String ShedName = null;

        System.out.println("\nAdding new shed");

        //Finds a unique shed name
        do
        {
            System.out.println("\nEnter a Name for this Shed:");
            ShedName = ConsoleInput.readLine();

            if (findShedByName(ShedName) != null)
                System.out.println("Name is not unique");
        } while (findShedByName(ShedName) != null || ShedName.isEmpty());

        //Gets a valid shed type
        do
        {
            System.out.println("\nEnter Shed Type(1 = Nickel, 2 = Iron)");
            ShedType = ConsoleInput.readInt();
        } while (ShedType != Ore.ORETYPE_IRON && ShedType != Ore.ORETYPE_NICKEL);


        switch (ShedType)
        {
            case Ore.ORETYPE_NICKEL:
                shed = new ShedNickel(ShedName);
                break;
            case Ore.ORETYPE_IRON:
                shed = new ShedIron(ShedName);
                break;
        }

        //Inserts new shed into the list
        shedList.insertFirst(shed);

    }

    /**
     * Finds a shed by name
     *
     * @param inName The name of the Shed to search for
     * @return Returns the Shed if one is found otherwise returns null
     */
    private IShed findShedByName(String inName)
    {
        IShed foundShed = null;

        for (IShed shed : shedList)
        {
            //Check that the shed names match
            if (shed.getName().toUpperCase().equals(inName.toUpperCase()))
            {
                foundShed = shed;
                break;
            }
        }

        return foundShed;
    }

    /**
     * Shows the contents of a shed selected by the user
     */
    private void uiShowShedContents()
    {
        if (shedList.isEmpty())
        {
            System.out.println("\nNo sheds to display");
            return;
        }

        String ShedName;
        IShed Shed;
        //Finds a valid shed to display
        do
        {
            uiShowShedNames();
            System.out.println("Enter the name of the shed to view:");
            ShedName = ConsoleInput.readLine();

            Shed = findShedByName(ShedName);

        } while (Shed == null);

        //Loops through the ore piles in the shed printing them out
        for (OrePile pile : Shed)
        {
            System.out.println(String.format("Ore Weight: %.2f    Ore Grade: %.2f    Metal Weight: %.2f    \n", pile.getWeight(), pile.getGrade(), pile.calcMetalWeight()));
        }
    }

    /**
     * Processes the next order in the queue if one is available
     */
    private void uiProcessNextOrder()
    {
        if (orderQueue.isEmpty())
        {
            System.out.println("No Orders in System, returning to main menu");
            return;
        }

        IShed shed;

        //Retrieves the first order in the queue
        ShipmentOrder order = orderQueue.peek();

        System.out.println(String.format("\nID: %d    Customer Name: %s    Ore Type: %s    Weight: %.2f    Value: %.2f\n", order.getOrderID(), order.getCustomerName(), order.getOre().getOreType() == Ore.ORETYPE_IRON ? "Iron" : "Nickel", order.getOrderedMetalWt(), order.getUnitPrice()));

        System.out.println("Are you sure you want to continue?(y/n)");
        char cont = ConsoleInput.readChar();

        //Checks that the user wants to continue
        if (Character.toUpperCase(cont) == 'Y')
        {
            shed = findValidShedForOrder(order);
            order = orderQueue.dequeue();
            if (shed != null)
            {
                double oreShipped = shed.satisfyOrder(order);
                //Sets the shipped ore weight in the order
                order.setShippedOreWt(oreShipped);

                System.out.println(String.format("\nTotal Ore Weight: %.2f     Name of shed: %s     ", order.getShippedOreWt(), shed.getName()));
            } else
            {
                System.out.println("\nNo Valid Shed, sending order to back of queue.");

                //Puts the order to the end of the queue
                orderQueue.enqueue(order);
            }
        } else
        {
            System.out.println("\nreturning to main menu");
        }
    }


    /**
     * Finds the first valid shed that will satisfy the order
     *
     * @param order order that the shed must be able to satisfy
     * @return returns the shed that will satisfy the order or null if no shed exists
     */
    private IShed findValidShedForOrder(ShipmentOrder order)
    {
        IShed foundShed = null;
        //Loop though all sheds in our list
        for (IShed shed : shedList)
        {
            //Checks that the shed is contains the right ore and enough of it to satisfy the order
            if (shed.calcTotalMetalWeight() >= order.getOrderedMetalWt() && shed.getOreType() == order.getOre().getOreType())
            {
                foundShed = shed;
            }
        }

        return foundShed;
    }

    /**
     * Displays the menu options
     */
    private void uiDisplayMenuMenu()
    {
        System.out.println("\n(1) Add New Shed \n" +
                "(2) Ore Delivery Data Entry \n" +
                "(3) View Shed Contents \n" +
                "(4) Customer Order Data Entry \n" +
                "(5) View Pending Customer Orders \n" +
                "(6) Process Next Customer Order \n" +
                "(7) Quit\n" +
                "Enter Your Choice> ");
    }

}
