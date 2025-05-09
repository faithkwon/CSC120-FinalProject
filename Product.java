
public class Product {
    
    /* Attributes */
    private double price;
    private String priceName;
    private String name;
    private double productionCost;
    private ProductType type;
    private double demand;

    /**
     * Product constructor
     * @param pricePoint product price level
     * @param name product name
     * @param type type of product
     */
    public Product(int pricePoint, String name, ProductType type) {
        switch (pricePoint) {
            case 3 -> {
                this.price = 15.;
                this.productionCost = 12.;
                this.priceName = "expensive";
            }
            case 2 -> {
                this.price = 10.;
                this.productionCost = 7.;
                this.priceName = "mid-priced";
            }
            case 1 -> {
                this.price = 5.;
                this.productionCost = 2.;
                this.priceName = "cheap";
            }
        }

        this.name = name;
        this.type = type;

        this.demand = 1.; 
    }

    /* Getter methods for attributes */

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public double getProductionCost() {
        return productionCost;
    }

    public ProductType getType() {
        return type;
    }

    public double getDemand() {
        return demand;
    }

    public String toString() {
        return name + ", a " + priceName + " " + type + " product priced at $" + price + " that costs $" + productionCost + " to produce";
    }

    /**
     * Setter method for ProductType
     * @param p type
     */
    public void setType(ProductType p) {
        this.type = p;
    }

    /**
     * Setter method for price
     * @param increase if you want to increase or decrease the price
     * @param percent the % you want to inc/dec the price by
     */
    public void changePrice(boolean increase, double percent) {
        if (increase) {
            this.price += price * (percent / 100.);
        } else {
            this.price -= price * (percent / 100.);
        }
    }
    
    /**
     * Calculating demand
     * @param factor the amount at which demand is increasing/decreasing by
     */
    public void increaseDemand(double factor) {
        demand += factor;
        demand = Math.min(demand, 5.0); // cap demand to prevent runaway growth
    }
    
    public void decreaseDemand(double factor) {
        demand -= factor;
        demand = Math.max(demand, 0.1); // prevent zero or negative demand
    }
}
