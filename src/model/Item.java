package model;

/**
 * Describes a catalog item for on-line store.
 **/

public class Item
{
    private String SKU;
    private String description;
    private int numberInStock;
    private double unitPrice;
    private String color;

    public Item(String SKU, String description, int numberInStock, double unitPrice, String color) {
        this.SKU = SKU;
        this.description = description;
        this.numberInStock = numberInStock;
        this.unitPrice = unitPrice;
        this.color = color;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
