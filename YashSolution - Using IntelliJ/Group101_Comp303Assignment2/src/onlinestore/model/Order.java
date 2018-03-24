package onlinestore.model;

import java.util.ArrayList;

public class Order {
    private ArrayList<Phone> orderedItems;

    public Order() {
        orderedItems = new ArrayList<Phone>();
    }

    public ArrayList<Phone> getOrderedItems() {
        return orderedItems;
    }

    public void addPhone(Phone phone) {
        orderedItems.add(phone);
    }
}