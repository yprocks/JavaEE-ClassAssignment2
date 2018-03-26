package model;

import java.util.Arrays;
import java.util.Vector;

/** A catalog listing the phones available in inventory. */

public class PhoneList extends Vector
{
    private static Phone[] phones =
    {
            new Phone("05T89D45", "Samsung Galaxy S9 (64GB)", 7, 959.78, "Grey", "img/s9-front-purple.png"),
            new Phone("45GT891S", "Google Pixel 2 XL (128GB)", 5, 1239.00, "Black", "img/pixel_2_xl_front_bw.png"),
            new Phone("E8GSF451", "Apple iPhone X (64GB)", 20, 1319.98, "Space Grey", "img/iphone_x_front_silver.png"),
            new Phone("89SDF154", "Apple iPhone 8 Plus (256GB)", 10, 1495.54, "Gold", "img/iphone_8_plus_front_silver.png"),
            new Phone("SDF498QW", "Samsung Galaxy S9+ (64GB)", 5, 1195.26, "Purple", "img/s9plus-front-gray.png"),
            new Phone("ERT14S45", "LG V30 Plus Tone (64GB)", 10, 598.99, "Grey", "img/v30_front.png"),
    };

    //Constructor
    public PhoneList()
    {
        //Loop through phones array and add to Phone[]
        this.addAll(Arrays.asList(phones));
    }

    //Get phones
    public static Phone getItem(String itemID)
    {
        Phone phone;
        if (itemID == null)
        {
            return (null);
        }
        for (Phone phone1 : phones)
        {
            phone = phone1;
            if (itemID.equals(phone.getSKU()))
            {
                return (phone);
            }
        }
        return  null;
    }

}
