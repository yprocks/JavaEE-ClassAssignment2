package dao;

import model.Item;

import java.util.List;

public interface ItemDAO {
	void addItem(String key, Item item);

	List<Item> getItems();

	Item getItem(String SKU);
}
