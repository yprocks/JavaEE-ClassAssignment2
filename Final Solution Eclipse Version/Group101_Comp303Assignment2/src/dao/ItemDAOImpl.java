package dao;

import model.Item;

import java.util.concurrent.ConcurrentHashMap;

import java.util.LinkedList;
import java.util.Map;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
	private static ItemDAOImpl instance = null;
	private Map<String, Item> itemMap = null;
	private List<Item> sortedItem = null;

	synchronized public static ItemDAOImpl getInstance() {
		if (instance == null) {
			instance = new ItemDAOImpl();
		}
		return instance;
	}

	private ItemDAOImpl() {
		itemMap = new ConcurrentHashMap<String, Item>();
		sortedItem = new LinkedList<Item>();
	}

	private void sortPhones(Item phone) {
		sortedItem.add(phone);
//		Collections.sort(sortedPhone);
	}

	public void addItem(String key, Item item) {
		itemMap.put(key, item);
		sortPhones(item);
	}

	public List<Item> getItems() {
		return sortedItem;
	}

  public Item getItem(String itemID)
  {
      Item item;
      if (itemID == null)
          return (null);
      
      for (Item item1 : sortedItem)
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