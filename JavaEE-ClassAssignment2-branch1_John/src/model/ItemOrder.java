package model;

import model.Item;

/**
 * Associates a catalog Item with a specific order by keeping track of the
 * number ordered and the total price. Also provides some convenience methods to
 * get at the Item data without first extracting the Item separately.
 */

public class ItemOrder
{
    private Item item;
    private int numItems;
    private String itemImg;

    public ItemOrder(Item item) {
        setItem(item);
    }


    public void setImgPath(String path)
    {
        this.itemImg= path;
    }

    public String getImgPath()
    {
        return itemImg;
    }

    public Item getItem() {
        return item;
    }

    protected void setItem(Item item) {
        this.item = item;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int n)
    {
        this.numItems = n;
    }

    public String getItemSKU()
    {
        return(getItem().getSKU());
    }

    public void incrementNumItems()
    {
        setNumItems(getNumItems()+1);
    }

    public String getDes()
    {
        return(getItem().getDescription());
    }

    public double getUnitPrice()
    {
        return (getItem().getUnitPrice());
    }

    public double getProdTotal()
    {
        return (getNumItems()*getUnitPrice());
    }

}
