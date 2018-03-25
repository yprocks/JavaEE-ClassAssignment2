package model;

public class ItemImage
{
    private String itemSku;
    private String imagePath;

    public ItemImage(String itemSku, String imagePath)
    {
        this.itemSku = itemSku;
        this.imagePath = imagePath;
    }

    public String getItemSku() {
        return itemSku;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
