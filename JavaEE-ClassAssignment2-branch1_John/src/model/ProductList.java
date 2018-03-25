package model;

import java.util.Arrays;
import java.util.Vector;

/** A catalog listing the items available in inventory. */

public class ProductList extends Vector
{
    private static Item[] items=
    {
            new Item("05T89D45", "Samsung Galaxy S9 (64GB)", 7, 959.78, "Grey"),
            new Item("45GT891S", "Google Pixel 2 XL (128GB)", 5, 1239.00, "Black"),
            new Item("E8GSF451", "Apple iPhone X (64GB)", 20, 1319.98, "Space Grey"),
            new Item("89SDF154", "Apple iPhone 8 Plus (256GB)", 10, 1495.54, "Gold"),
            new Item("SDF498QW", "Samsung Galaxy S9+ (64GB)", 5, 1195.26, "Purple"),
            new Item("ERT14S45", "LG V30 Plus Tone (64GB)", 10, 598.99, "Grey"),
    };

    //Constructor
    public ProductList()
    {
        //Loop through items array and add to Item[]
        this.addAll(Arrays.asList(items));
    }

    //Get items
    public static Item getItem(String itemID)
    {
        Item item;
        if (itemID == null)
        {
            return (null);
        }
        for (Item item1 : items)
        {
            item = item1;
            if (itemID.equals(item.getSKU()))
            {
                return (item);
            }
        }
        return  null;
    }

}
