import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

public class Business  {

    /* Attributes */ 
    private String name;
    private double funds;
    private Hashtable<Product, Integer> inventory; // Integer = # of units of product in inventory
    private double revenue;

    /**
     * Business constructor
     * @param name business name
     */
    public Business(String name) {
        this.name = name;
        this.funds = 50.;
        this.inventory = new Hashtable<>();
    }

    /* Getter/setter methods for attributes */
    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public double getFunds() {
        return funds;
    }

    public double getRevenue () {
        return revenue;
    }
    
    public int getAmount(Product p) {
        return inventory.get(p);
    }

    public String printInventory() {
        Enumeration<Product> e = inventory.keys();
        String s = "";

        while(e.hasMoreElements()) {
            Product p = e.nextElement();

            s += p.getName() + ":\n    - QUANTITY: " + inventory.get(p) + "\n    - PRICE: $" + p.getPrice() + "\n";
        }

        return s;
    }

    public ArrayList<Product> getInventory() {
        ArrayList<Product> a = new ArrayList<>();
        Enumeration<Product> e = inventory.keys();

        while(e.hasMoreElements()) {
            Product p = e.nextElement();
            a.add(p);
        }

        return a;
    }

    public Product getProduct(String name) {
        for (Product key : inventory.keySet()) {
            if (key.getName().equals(name)) {
                return key;
            }
        }
        
        throw new RuntimeException("Item not found in inventory.");
    }

    public String toString() {
        return "NAME: " + name + "\nCURRENT FUNDS: $" + funds + "\nTOTAL REVENUE: $" + revenue;
    }

    /**
     * adds an amount of a product to the inventory
     * @param p product to be added to inventory
     * @param amt amount of the product
     */
    public void addInventory(Product p, int amt) {
        if (funds >= p.getProductionCost() * amt) {
            inventory.put(p, amt);
            removeFunds(p.getProductionCost() * amt);

            System.out.println("\nYou have successfully added " + amt + " units of " + p + ".\n$" + p.getProductionCost() * amt + " removed from your funds for production costs. Remaining funds: $" + funds);
        } else {
            System.out.println("\nInsufficient funds. Please add a product that you can afford.");
        }
    }

    /**
     * removes a product from your inventory
     * @param p product to remove
     */
    public void removeInventory(Product p) {
        double productionCost = p.getProductionCost() * inventory.get(p);
        
        inventory.remove(p);
        addFunds(productionCost);

        System.out.println("\nYou have successfully removed " + p + ".\n$" + productionCost + " added to your funds from forgone production costs. Remaining funds: $" + funds);
    }

    /**
     * sells an amount of a specific product
     * @param p product being sold
     * @param amt amount of product sold
     */
    public void sellProduct(Product p, int amt) {
        addFunds(p.getPrice() * amt);
        revenue += p.getPrice() * amt;
        inventory.put(p, inventory.get(p) - amt);

        System.out.println(amt + " units of " + p.getName() + " sold! " + (p.getPrice() * amt) + " added to your funds.");
        
        if (inventory.get(p) == 0) {
            inventory.remove(p);
        }
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
        if (inventory.isEmpty()) return;

        System.out.println("*** RANDOM EVENT!!!!!! ***");
        Random random = new Random();
        int i = random.nextInt(0, 4);
        int n = random.nextInt(0, 2);

        ArrayList<Product> list = new ArrayList<>(inventory.keySet());
        Product p = list.get(random.nextInt(list.size()));

        switch (i) {
            case 0 -> {
                // Inventory of a certain product set to 0
                inventory.replace(p, inventory.get(p), 0);
                System.out.println("Oh no! A giant bird swooped into your store and stole all of your " + p.getName() + ".");
            }
            case 1 -> {
                // Prices get affected
                int r = random.nextInt(30, 70); // 30%–70% change
                if (n == 0) {
                    p.changePrice(true, r);
                    System.out.println("Oh no! There's a shortage in a crucial component in producing " + p.getName() + "; prices have risen by " + r + "%.\n" + p.getName() + "'s price is now $" + p.getPrice() + ".");
                } else {
                    p.changePrice(false, r);
                    System.out.println("Yay! You opened a closet and found a tool that increased your productivity, lowering costs of producing " + p.getName() + "; prices have fallen by " + r + "%.\n" + p.getName() + "'s price is now $" + p.getPrice() + ".");
                }
            }
            case 2 -> { 
                // Funds get affected
                int r = random.nextInt(10, 30); // 10%–30% change

                if (n == 0) {
                    removeFunds(funds * (r / 100.0));
                    System.out.println("Oh no! You had to pay an unexpected fine of $" + (funds * (r / 100.0)) + ".\nFunds are now at $" + funds + ".");
                } else {
                    addFunds(funds * (r / 100.0));
                    System.out.println("Yay! A customer donated $" + (funds * (r / 100.0)) + " out of appreciation.\nFunds are now at $" + funds + ".");
                }
            }
            case 3 -> {
                // Demand gets affected
                double r = random.nextDouble(0.1, 0.5);

                if (n == 0) {
                    p.decreaseDemand(r);
                    System.out.println("Bad press hurt your reputation — demand for " + p.getName() + " dropped slightly.");
                } else {
                    p.increaseDemand(r);
                    System.out.println("A viral video on TikTok made your " + p.getName() + " a hot item — demand increased!");
                }
            }
        }
    }

    /* Gives the user a list of commands */
    public void printCommands() {
        System.out.println("   - 'CHECK' - summarizes your business");
        System.out.println("   - 'INVENTORY' - prints your inventory");
        System.out.println("   - 'ADD PRODUCT' - adds a product to your inventory");
        System.out.println("   - 'REMOVE PRODUCT' - removes a product from your inventory");
        System.out.println("   - 'SALE' - conduct a sale on a product");
        System.out.println("   - 'HELP' - see this menu again");
    }
}