package model;

/**
 * Associates a catalog Phone with a specific order by keeping track of the
 * number ordered and the total price. Also provides some convenience methods to
 * get at the Phone data without first extracting the Phone separately.
 */

public class PhoneOrder
{
    private Phone phone;
    private int numItems;
    private String itemImg;

    public PhoneOrder(Phone phone)
    {
        setPhone(phone);
    }

    public void setImgPath(String path)
    {
        this.itemImg= path;
    }

    public String getImgPath()
    {
        return itemImg;
    }

    public Phone getPhone() {
        return phone;
    }

    protected void setPhone(Phone phone) {
        this.phone = phone;
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
        return(getPhone().getSKU());
    }

    public void incrementNumItems()
    {
        setNumItems(getNumItems()+1);
    }

    public String getDes()
    {
        return(getPhone().getDescription());
    }

    public double getUnitPrice()
    {
        return (getPhone().getUnitPrice());
    }

    public double getProdTotal()
    {
        return (getNumItems()*getUnitPrice());
    }

}
