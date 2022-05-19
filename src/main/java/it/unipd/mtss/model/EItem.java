////////////////////////////////////////////////////////////////////
// Alessandro Cavaliere 1224440
// Filippo Brugnolaro 1217321
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

public class EItem {
    private final String name;
    private final EItemType type;
    private double price;

    public EItem(String name, EItemType type, double price) {
        if (name == null) {
            throw new IllegalArgumentException("Name must not be null");
        }

        if (type == null) {
            throw new IllegalArgumentException("Type must not be null");
        }

        if (price <= 0.00) {
            throw new IllegalArgumentException("Price must be at least 0.01");
        }

        this.name = name;
        this.type = type;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
