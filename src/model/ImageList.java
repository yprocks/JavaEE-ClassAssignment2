package model;

import java.util.Arrays;
import java.util.Vector;

public class ImageList extends Vector
{
    private static ItemImage[] imageItems=
    {
        new ItemImage("05T89D45", "img/s9-front-purple.png"),
        new ItemImage("45GT891S", "img/pixel_2_xl_front_bw.png"),
        new ItemImage("E8GSF451", "img/iphone_x_front_silver.png"),
        new ItemImage("89SDF154", "img/iphone_8_plus_front_silver.png"),
        new ItemImage("SDF498QW", "img/s9plus-front-gray.png"),
        new ItemImage("ERT14S45", "img/v30_front.png"),
    };

    //Constructor
    public ImageList()
    {
        //Loop through items array and add to Item[]
        this.addAll(Arrays.asList(imageItems));
    }

    //Get items
    public static ItemImage getItem(String imageId)
    {
        ItemImage imageItem;
        if (imageId == null)
        {
            return (null);
        }
        for (ItemImage item1 : imageItems)
        {
            imageItem = item1;
            if (imageId.equals(imageItem.getItemSku()))
            {
                return (imageItem);
            }
        }
        return  null;
    }
}
