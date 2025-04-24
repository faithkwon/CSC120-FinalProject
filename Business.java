import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

public class Business  {

    /* Attributes */ 
    private String name;
    private int funds;
    private Hashtable<Product, Integer> inventory; // Integer = # of units of product in inventory
    private int currYear;

    /**
     * Business constructor
     * @param name business name
     */
    public Business(String name) {
        this.name = name;
        this.funds = 100;
        this.inventory = new Hashtable<>();
        this.currYear = 1;
    }

    /* Getter methods for attributes */
    public String getName() {
        return name;
    }

    public int funds() {
        return funds;
    }

    public int getCurrYear() {
        return currYear;
    }

    public void printInventory() {
        Enumeration<Product> e = inventory.keys();

        while(e.hasMoreElements()) {
            System.out.println(e.nextElement().getName() + "\n");
        }
    }

    /**
     * adds an amount of a product to the inventory
     * @param p product to be added to inventory
     * @param amt amount of the product
     */
    public void addInventory(Product p, int amt) {
        inventory.put(p, amt);
        removeFunds(p.getProductionCost() * amt);

        System.out.println("You have successfully added " + amt + " units of " + p + " to your inventory.\n$" + p.getProductionCost() * amt + " removed from your funds for production costs.");
    }

    /**
     * removes a product from your inventory
     * @param p product to remove
     */
    public void removeInventory(Product p) {
        inventory.remove(p);
        addFunds(p.getProductionCost() * inventory.get(p));

        System.out.println("You have successfully removed " + p + " from your inventory.\n$" + p.getProductionCost() * inventory.get(p) + " added to your funds from forgone production costs.");
    }

    /**
     * sells an amount of a specific product
     * @param p product being sold
     * @param amt amount of product sold
     */
    public void sellProduct(Product p, int amt) {
        addFunds(p.getPrice());
        inventory.remove(p);

        System.out.println(amt + " units of " + p.getName() + " sold! " + p.getPrice() * amt + " added to your funds.");
    }

    /**
     * adding to funds
     * @param d
     */
    private void addFunds(double d) {
        funds += d;
    }

    /**
     * removing from funds
     * @param d
     */
    private void removeFunds(double d) {
        funds -= d;
    }

    /* Generating random events */
    public void generateEvent() {
        Random random = new Random();
        int i = random.nextInt(0, 4);
        int n = random.nextInt(0, 2);

        Product p;

        ArrayList<Product> list = new ArrayList<>(inventory.keySet());
        p = list.get(random.nextInt(0, list.size()));

        if (i == 0) { // Inventory of a certain product set to 0
            inventory.replace(p, inventory.get(p), 0);
            System.out.println("Oh no! A giant bird swooped into your store and stole all of your " + p.getName() + ".");
        } else if (i == 1) { // Prices get affected
            int r = random.nextInt(30, 70);

            if (n == 0) {
                p.changePrice(true, r);
                System.out.println("Oh no! There's a shortage in a crucial component in producing " + p + "; prices have risen by " + r + "%.");
            } else {
                p.changePrice(false, random.nextInt(20, 70));
                System.out.println("Yay! You opened a closet and found a tool that increased your productivity, lowering costs of producing " + p + "; prices have fallen by " + r + "%.");
            }
        } else if (i == 2) { // Funds get affected
            
        } else { // Demand gets affected

        }
    }
}