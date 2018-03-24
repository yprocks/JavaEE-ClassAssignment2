package onlinestore.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.NumberFormat;

public class Phone {
    private int sku;
    private String phoneDesc;
    private double phonePrice;
    private int quantity;
    private String company;
    private String image;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Phone(int skuNo, String phoneDesc, double phonePrice, String company, String image) {
        this.setItemNo(skuNo);
        this.setItemDesc(phoneDesc);
        this.setItemPrice(phonePrice);
        this.setQuantity(0);
        this.setCompany(company);
        this.setImage(image);
    }

    public int getItemNo() {
        return sku;
    }

    public void setItemNo(int skuNo) {
        this.sku = skuNo;
    }

    public String getItemDesc() {
        return phoneDesc;
    }

    public void setItemDesc(String phoneDesc) {
        this.phoneDesc = phoneDesc;
    }

    public double getItemPrice() {
        return phonePrice;
    }

    public void setItemPrice(double phonePrice) {
        this.phonePrice = phonePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bs);
        ps.printf("%4s     %-30s", getItemNo(), getItemDesc());
        String s = bs.toString();
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return s + nf.format(getItemPrice());
    }

    public int compareTo(Phone phone) {
        return getItemNo() - phone.getItemNo();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
