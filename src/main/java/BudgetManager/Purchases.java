package main.java.BudgetManager;


public class Purchases {

    private final Categories categories;
    private final float price;
    private final String name;

    public Purchases(Categories categories, float price, String name) {
        this.categories = categories;
        this.price = price;
        this.name = name;
    }

    public Categories getCategories() {
        return categories;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int compareTo(Purchases purchase) {
        float comparePrice = (purchase).getPrice();
        if (this.price == comparePrice) {
            return 0;
        } else {
            return this.price > comparePrice ? 1 : -1;
        }
        //return (int) (this.price - comparePrice);
    }

    @Override
    public String toString() {
        return "Purchases{" +
                "categories=" + categories +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
