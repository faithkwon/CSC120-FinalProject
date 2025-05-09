import java.util.Random;
import java.util.Scanner;

public class Main {

    /* Attributes */ 
    private static ProductType type = ProductType.FOOD;
    private static boolean b = false;
    private static String inputString;
    private static Scanner input = new Scanner(System.in);
    private static int inputNum;
    private static double inputDouble;
    private static int pricePoint = 1;
    private static Business self = new Business("");
    private static int currYear = 1;
    private static int currQuarter = 1;
    private static Random random = new Random();

    /* code for letting the user set the product type */
    public static void setProductType() {
        inputString = input.nextLine().toLowerCase();

        while (b == false) {
            switch (inputString) {
                case "food" -> {
                    type = ProductType.FOOD;
                    b = true;
                }
                case "medicine" -> {
                    type = ProductType.MEDICINE;
                    b = true;
                }
                case "clothing" -> {
                    type = ProductType.CLOTHING;
                    b = true;
                }
                case "tech" -> {
                    type = ProductType.TECH;
                    b = true;
                }
                case "home" -> {
                    type = ProductType.HOME;
                    b = true;
                }
                default -> {
                    System.out.println("I don't understand, please try again.");
                    inputString = input.nextLine().toLowerCase();
                }
            }
        }

        b = false;
    } 

    /**
     * Returning the current 'in' ProductType
     * @return the 'in' ProductType
     */
    public static ProductType inType() {
        ProductType in = ProductType.FOOD;
        int i = random.nextInt(1, 5);

        switch (i) {
            case 1 -> in = ProductType.MEDICINE;
            case 2 -> in = ProductType.CLOTHING;
            case 3 -> in = ProductType.TECH;
            case 4 -> in = ProductType.HOME;
        }

        return in;
    }

    /* Text for letting the user add to the inventory */
    public static void addToInventoryText() {
        System.out.println("\nType?");
        setProductType();

        System.out.println("\nPrice point? Type '1' for cheap ($5-10), '2' for mid-price ($10-15), and '3' for expensive ($15-20).\nYou currently have $" + self.getFunds() + ".");
        inputDouble = input.nextDouble();

        while (b == false) {
            if (inputDouble == 1 || inputDouble == 2 || inputDouble == 3) {
                pricePoint = (int)inputDouble;
                b = true;
            } else {
                System.out.println("I don't understand, please try again.");
                inputNum = input.nextInt();
            }
        }
        b = false;

        System.out.println("\nProduct name?");

        input.nextLine();
        inputString = input.nextLine();

        Product p = new Product(pricePoint, inputString, type);

        System.out.println("\nHow many? Each unit costs $" + p.getProductionCost() + " to produce.");
        
        inputNum = input.nextInt();

        while (b == false) {
            if (inputNum * p.getProductionCost() <= self.getFunds()) {
                self.addInventory(p, inputNum);
                b = true;
            } else {
                System.out.println("Invalid amount, please try again.");
                inputNum = input.nextInt();
            }
        }
        b = false;
    }

    /**
     * Code for automatically selling a random amount of the products at the end of each user's 'turn'
     * @param in the current 'in' ProductType
     */
    public static void autoSell(ProductType in) {
        double baseMultiplier;
        double totalMultiplier;
        int maxSale;
        int amt;

        for (Product p : self.getInventory()) {
            if (self.getAmount(p) > 0) {

                // Base multiplier: in-season = 0.8, otherwise 0.4
                if (p.getType() == in) {
                    baseMultiplier = 0.8;
                } else {
                    baseMultiplier = 0.4;
                }
    
                // Combine with product-specific demand
                totalMultiplier = baseMultiplier * p.getDemand();
                maxSale = Math.max(1, (int)(self.getAmount(p) * totalMultiplier));
                
                if (maxSale == 1) {
                    amt = 1;
                } else {
                    amt = random.nextInt(maxSale) + 1;
                }
    
                self.sellProduct(p, amt);
            }
        }
    }

    /* Main method! */
    public static void main(String[] args) {
        String name = "";

        // intializing
        System.out.println("\n====== GAME START ======\n");

        while (b == false) {
            System.out.println("What is your name?");
            name = input.nextLine();
    
            System.out.println("\nAnd what would you like to name your business? ");
            self.setName(input.nextLine());
            
            System.out.println("\nAlright, so your name is " + name + " and your business is called " + self.getName() + ". Is that right?\nType 'Y' for yes, 'N' for no.");
            inputString = input.nextLine().toLowerCase();
            if (inputString.equals("y")) {
                b = true;
            }
        }

        b = false;

        // story
        System.out.println("\n========================\n"); 
        System.out.println("You're dozing off at your 9-5 once again. You feel the ever present urge to quit your job.");
        System.out.println("It's 4:50 PM and your asshole boss drops a pile of paperwork onto your desk. That's your final straw.");
        System.out.println("You quit your job on the spot and run out of the building.");
        System.out.println("You're finally free to pursue your true passion of owning a business!");
        System.out.println("Watch out, world -- " + name + " is coming to take over!");
        System.out.println("(Press enter to continue.)");
        input.nextLine();

        // tutorial of sorts
        System.out.println("\n========================\n"); 

        System.out.println("\n====== YEAR 1, Q1 ======\n");
        System.out.println("Current funds: $" + self.getFunds());
        System.out.println("Total revenue: $" + self.getRevenue());
        System.out.println("Current inventory: " + self.printInventory());

        System.out.println("\nLooks like your inventory is empty! What type of product do you want to sell?\n");
        
        System.out.println("OPTIONS:");
        for (ProductType p : ProductType.values()) {
            System.out.println("   - " + p);
        }

        setProductType();

        b = false;

        System.out.println(type + " is a great choice!");
        System.out.println("\nNext, how pricey do you want the product to be? \nType '1' for cheap ($5), '2' for mid-price ($10), and '3' for expensive ($15).");
        
        inputDouble = input.nextDouble();

        while (b == false) {
            if (inputDouble == 1 || inputDouble == 2 || inputDouble == 3) {
                pricePoint = (int)inputDouble;
                b = true;
            } else {
                System.out.println("I don't understand, please try again.");
                inputNum = input.nextInt();
            }
        }

        b = false;

        System.out.println("\nNext, what is the product's name?");

        input.nextLine();
        inputString = input.nextLine();

        Product prod = new Product(pricePoint, inputString, type);

        System.out.println("\nFinally, how many would you like to add to your inventory? Each unit costs $" + prod.getProductionCost() + " to produce.");
        
        inputNum = input.nextInt();
        input.nextLine();

        while (b == false) {
            if (inputNum * prod.getProductionCost() <= self.getFunds()) {
                self.addInventory(prod, inputNum);
                b = true;
            } else {
                System.out.println("Invalid amount, please try again.");
                inputNum = input.nextInt();
            }
        }

        b = false;

        System.out.println("(Press enter to continue.)");
        input.nextLine();
        System.out.println("\nGreat! Now that you have an item in your inventory, familiarize yourself with what you can do.");
        System.out.println("\nOPTIONS:");
        self.printCommands();
        System.out.println("\nYou'll have three 'tangible' actions per quarter (adding/removing from inventory and conducting a sale).");
        System.out.println("This quarter, you get two more actions.");

        int i = 0;
        ProductType in = inType();
        System.out.println("\nLooks like " + in + " is in high demand this quarter.");
        
        // first practice quarter
        while (i < 2) {
            System.out.println("\nWhat would you like to do next?");
            inputString = input.nextLine().toLowerCase();

            switch (inputString) {
                case "check" -> System.out.println("\n" + self);
                case "inventory" -> System.out.println("\n" + self.printInventory());
                case "add product" -> {
                    addToInventoryText();

                    autoSell(in);
                    i++;
                } 
                case "remove product" -> {
                    System.out.println("\nWhich product would you like to remove?");
                    
                    while (b == false) {
                        inputString = input.nextLine();

                        try {
                            self.removeInventory(self.getProduct(inputString));
                            b = true;
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    b = false;

                    autoSell(in);
                    i++;
                }
                case "sale" -> {
                    System.out.println("\nFlash sale! On which product?");
                    inputString = input.nextLine().toLowerCase();

                    System.out.println("\nWhat % do you want to decrease by?");
                    inputDouble = input.nextDouble();
                    input.nextLine();

                    self.getProduct(inputString).changePrice(false, inputDouble);
                    self.getProduct(inputString).increaseDemand(inputDouble * 0.05); // 5% increase in demand for each 1% drop in price

                    System.out.println("\n" + self.getProduct(inputString).getName() + "'s price has decreased by " + inputDouble + "%. New price now $" + self.getProduct(inputString).getPrice() + ".");
                    
                    autoSell(in);
                    i++;
                }
                case "help" -> self.printCommands();
                default -> System.out.println("Please type a command I understand.");
            }
        }
        currQuarter++;
        System.out.println("\nWow, $" + self.getRevenue() + " earned in your first quarter? You're a natural!");
        System.out.println("That's enough tutorial for now. Good luck, " + name + "!");
        System.out.println("(Press enter to continue.)");
        input.nextLine();
        input.nextLine();

        // STARTING THE ACTUAL GAAAAAAAAAAAAME

        while (currYear < 6) { // only loops for 5 years
            double startingRevenue = self.getRevenue();
            while (currQuarter < 5) {
                System.out.println("\n========================"); 
                System.out.println("\n====== YEAR "+ currYear + ", Q" + currQuarter + " ======\n");
                System.out.println("Current funds: $" + self.getFunds());
                System.out.println("Total revenue: $" + self.getRevenue());
                System.out.println("Current inventory: \n" + self.printInventory());

                in = inType();
                System.out.println("\nLooks like " + in + " is in high demand this quarter.");

                i = 0;
                while (i < 3) {
                    System.out.println("\nWhat would you like to do next?");
                    inputString = input.nextLine().toLowerCase();
    
                    switch (inputString) {
                        case "check" -> System.out.println("\n" + self);
                        case "inventory" -> System.out.println("\n" + self.printInventory());
                        case "add product" -> {
                            addToInventoryText();
    
                            autoSell(in);
                            i++;
                        } 
                        case "remove product" -> {
                            System.out.println("\nWhich product would you like to remove?");
                            
                            while (b == false) {
                                inputString = input.nextLine();
    
                                try {
                                    self.removeInventory(self.getProduct(inputString));
                                    b = true;
                                } catch (Exception e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }
                            b = false;
    
                            autoSell(in);
                            i++;
                        }
                        case "sale" -> {
                            System.out.println("\nFlash sale! On which product?");
                            inputString = input.nextLine().toLowerCase();
    
                            System.out.println("\nWhat % do you want to decrease by?");
                            inputDouble = input.nextDouble();
                            input.nextLine();
    
                            self.getProduct(inputString).changePrice(false, inputDouble);
                            self.getProduct(inputString).increaseDemand(inputDouble * 0.05); // 5% increase in demand for each 1% drop in price
    
                            System.out.println("\n" + self.getProduct(inputString).getName() + "'s price has decreased by " + inputDouble + "%. New price now $" + self.getProduct(inputString).getPrice() + ".");
                            
                            autoSell(in);
                            i++;
                        }
                        case "help" -> self.printCommands();
                        default -> System.out.println("\nPlease type a command I understand.");
                    }
                }

                self.generateEvent();
                
                currQuarter++;
            }
            
            System.out.println("\nYou earned $" + (self.getRevenue() - startingRevenue) + " this past year!");
            currQuarter = 1;
            currYear++;

            System.out.println("(Press enter to continue.)");
            input.nextLine();
        }

        input.close();

        System.out.println("Congratulations, " + name + "!");
        System.out.println("At the end of your lucrative years as the CEO of " + self.getName() + ", you have earned $" + self.getRevenue() + " in total.");
        System.out.println("You go on to retire and live out the rest of your days happy and fulfilled. Yay!");
    }
}
