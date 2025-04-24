import java.util.Random;

public class Product {
    
    /* Attributes */
    private int price;
    private String name;
    private int productionCost;
    private ProductType type;
    private int demand;

    /**
     * Product constructor
     * @param pricePoint product price level
     * @param name product name
     * @param type type of product
     */
    public Product(int pricePoint, String name, ProductType type) {
        Random random = new Random();
        
        if (pricePoint == 3) {
            this.price = random.nextInt(15, 21);
            this.productionCost = 12;
        } else if (pricePoint == 2) {
            this.price = random.nextInt(10, 15);
            this.productionCost = 7;
        } else if (pricePoint == 1) {
            this.price = random.nextInt(5, 10);
            this.productionCost = 2;
        }

        this.name = name;
        this.type = type;

        this.demand = 1; // calculate demand
    }

    /* Getter methods for attributes */

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public ProductType getType() {
        return type;
    }

    public int getDemand() {
        return demand;
    }

    /**
     * Setter method for price
     * @param increase if you want to increase or decrease the price
     * @param percent the % you want to inc/dec the price by
     */
    public void changePrice(boolean increase, int percent) {
        if (increase) {
            this.price = this.price + (this.price * (percent / 100));
        } else {
            this.price = this.price - (this.price * (percent / 100));
        }
    }

    /**
     * Setter method for production costs -- decreases/increases the price by the same amount
     * @param increase if you want to increase or decrease the production costs
     * @param percent the % you want to inc/dec by
     */
    public void changeProductionCost(boolean increase, int percent) {
        if (increase) {
            this.productionCost = this.productionCost + (this.productionCost * (percent / 100));
            this.price = this.price + (this.price * (percent / 100));
        } else {
            this.productionCost = this.productionCost - (this.productionCost * (percent / 100));
            this.price = this.price - (this.price * (percent / 100));
        }
    }
    
    /**
     * Calculating demand (work in progress..... still workshopping the algorithm but the base idea is that demand goes up when prices get lower)
     * @param type the product type that is currently "in demand" 
     */
    public void calculateDemand(ProductType type) {
        if (this.type == type) {
            this.demand = (int)(((productionCost * 2) / price) * 1.5);
        } else {
            this.demand = (productionCost * 2) / price;
        }
    }
}
