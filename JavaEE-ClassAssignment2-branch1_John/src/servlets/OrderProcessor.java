package servlets;

import controller.ShoppingCart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/OrderProcessor")
public class OrderProcessor extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);
        ShoppingCart cart;

        synchronized (session)
        {
            cart=(ShoppingCart) session.getAttribute("ShoppingCart");

            // New visitors get a fresh shopping cart.
            // Previous visitors keep using their existing cart.
            if(cart==null)
            {
                cart= new ShoppingCart();
                session.setAttribute("ShoppingCart", cart);
                System.out.println("cart null, new cart added");
            }
            String itemSKU= request.getParameter("itemSKU");
            if(itemSKU!= null)
            {
                String numItemsString = request.getParameter("numItems");
                String imgPath = request.getParameter("itemImg");
                if(numItemsString ==null)
                {
                    // If request specified an ID but no number,
                    // then customers came here via an "Add Item to Cart"
                    // button on a catalog page.
                    cart.addItem(itemSKU);
                }
                else
                {
                    // If request specified an ID and number, then
                    // customers came here via an "Update Order" button
                    // after changing the number of items in order.
                    // Note that specifying a number of 0 results
                    // in item being deleted from cart.
                    int numItems;
                    try
                    {
                        numItems = Integer.parseInt(numItemsString);
                    }
                    catch (NumberFormatException nfe)
                    {
                        numItems = 1;
                    }
                    cart.setItemsOrdered(itemSKU, numItems, imgPath);
                    System.out.println("order processor servlet, setting cart");
                }
            } // end synchronized


            RequestDispatcher dispatcher= null;
            //Send to ShowOrder page
            dispatcher = request.getRequestDispatcher("ShowOrder.jsp");
            dispatcher.forward(request, response);
        }





    }
}
