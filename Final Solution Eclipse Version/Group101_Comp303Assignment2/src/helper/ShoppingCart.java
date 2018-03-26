package helper;

import model.ItemOrder;

import java.io.Serializable;
import java.util.Vector;
import dao.ItemDAO;
import dao.ItemDAOImpl;

/**
 * A shopping cart data structure used to track orders. The ShowOrder servlet
 * associates one of these carts with each user session.
 */
public class ShoppingCart implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<ItemOrder> itemsOrdered;
    private ItemDAO itemDAO;
    
    /**Builds an empty shopping cart*/
    public ShoppingCart()
    {
        itemsOrdered = new Vector<ItemOrder>();
        itemDAO = ItemDAOImpl.getInstance();
    }

    /**Returns Vector of ItemOrder entries giving Item and number ordered.*/
    public Vector<ItemOrder> getItemsOrdered()
    {
        return itemsOrdered;
    }

    /**
     * Looks through cart to see if it already contains an order entry
     * corresponding to item ID. If it does, increments the number ordered. If
     * not, looks up Item in catalog and adds an order entry for it.
     */
    public synchronized void addItem(String itemID)
    {
        ItemOrder order;
        for (int i=0; i<itemsOrdered.size(); i++)
        {
            order= (ItemOrder) itemsOrdered.elementAt(i);
            if (order.getItemSKU().equals(itemID))
            {
                order.incrementNumItems();
                return;
            }
        }
        ItemOrder newOrder= new ItemOrder(itemDAO.getItem(itemID));
        itemsOrdered.addElement(newOrder);

    }

    /**
     * Looks through cart to find order entry corresponding to item ID listed.
     * If the designated number is positive, sets it. If designated number is 0
     * (or, negative due to a user input error), deletes item from cart.
     */

    public synchronized void setItemsOrdered(String itemSKU, int numberOrdered)
    {
        ItemOrder order;
        for (int i=0; i<itemsOrdered.size(); i++)
        {
            order = (ItemOrder) itemsOrdered.elementAt(i);
            if (order.getItemSKU().equals(itemSKU))
            {
                if (numberOrdered <=0)
                {
                    itemsOrdered.removeElementAt(i);
                }
                else
                {
                    order.setNumItems(numberOrdered);
                }
                return;
            }
        }
        ItemOrder newOrder = new ItemOrder(itemDAO.getItem(itemSKU));
        newOrder.setNumItems(numberOrdered);
        itemsOrdered.addElement(newOrder);
    }

    public synchronized int getNumOrdered()
    {
        return itemsOrdered.size();
    }

}
