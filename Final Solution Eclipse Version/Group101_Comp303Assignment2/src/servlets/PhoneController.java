package servlets;

import dao.ItemDAO;
import dao.ItemDAOImpl;
import helper.ShoppingCart;
import model.Item;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/phone")
public class PhoneController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ItemDAO itemDAO = ItemDAOImpl.getInstance();

	public PhoneController() {
		super();
		itemDAO.addItem("05T89D45",
				new Item("05T89D45", "Samsung Galaxy S9 (64GB)", 7, 959.78, "Grey", "s9-front-purple.png"));
		itemDAO.addItem("45GT891S",
				new Item("45GT891S", "Google Pixel 2 XL (128GB)", 5, 1239.00, "Black", "pixel_2_xl_front_bw.png"));
		itemDAO.addItem("E8GSF451",
				new Item("E8GSF451", "Apple iPhone X (64GB)", 20, 1319.98, "Space Grey", "iphone_x_front_silver.png"));
		itemDAO.addItem("89SDF154", new Item("89SDF154", "Apple iPhone 8 Plus (256GB)", 10, 1495.54, "Gold",
				"iphone_8_plus_front_silver.png"));
		itemDAO.addItem("SDF498QW",
				new Item("SDF498QW", "Samsung Galaxy S9+ (64GB)", 5, 1195.26, "Purple", "s9plus-front-gray.png"));
		itemDAO.addItem("ERT14S45",
				new Item("ERT14S45", "LG V30 Plus Tone (64GB)", 10, 598.99, "Grey", "v30_front.png"));

	}

	public void init() {
		getServletContext().setAttribute("lastNum", 18907);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url;

		String action = request.getParameter("action");
		if (action != null) {
			switch (action) {
			case "add-to-cart":
				url = "cart.jsp";
				addToCart(request, response, url);
				return;
			case "checkout":
				url = "message.jsp";
				confirmOrder(request, response, url);
				return;
			}
		}

		sendErrorPage(request, response, "/error.jsp", "Routing not valid");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		displayPhone(request, response, "index.jsp");

	}

	private void displayPhone(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {

		// String orderId = request.getParameter("orderId");
		//
		// if (orderId == null || orderId.trim().isEmpty()) {
		// request.setAttribute("message", "You must supply name to identify your
		// order");
		// request.getRequestDispatcher("index.jsp").forward(request, response);
		// return;
		// }

		int orderNum;

		synchronized (this) {
			orderNum = (int) getServletContext().getAttribute("lastNum");
			orderNum++;
			getServletContext().setAttribute("lastNum", orderNum);
		}

		HttpSession session = request.getSession();

		session.setAttribute("orderId", "2018" + orderNum);

		request.setAttribute("list", itemDAO.getItems());
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		ShoppingCart cart;

		synchronized (session) {
			cart = (ShoppingCart) session.getAttribute("ShoppingCart");

			// New visitors get a fresh shopping cart.
			// Previous visitors keep using their existing cart.
			if (cart == null) {
				cart = new ShoppingCart();
				session.setAttribute("ShoppingCart", cart);
				System.out.println("cart null, new cart added");
			}
			String itemSKU = request.getParameter("itemSKU");
			if (itemSKU != null) {
				String numItemsString = request.getParameter("numItems");
				if (numItemsString == null) {
					// If request specified an ID but no number,
					// then customers came here via an "Add Item to Cart"
					// button on a catalog page.
					cart.addItem(itemSKU);
				} else {
					// If request specified an ID and number, then
					// customers came here via an "Update Order" button
					// after changing the number of items in order.
					// Note that specifying a number of 0 results
					// in item being deleted from cart.
					int numItems;
					try {
						numItems = Integer.parseInt(numItemsString);
					} catch (NumberFormatException nfe) {
						numItems = 1;
					}
					cart.setItemsOrdered(itemSKU, numItems);
					session.setAttribute("cart", cart.getItemsOrdered());
					System.out.println("order processor servlet, setting cart");
				}
			} // end synchronized

			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	private void confirmOrder(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String message = "thank you";

		String orderId = (String) session.getAttribute("orderId");

		if (orderId == null) {
			message = "ERROR: Seems like your session has expired. Did you confirm or cancel already?";
			request.setAttribute("errormessage", message);
		} else {
			message = "Order " + orderId + " confirmed. Your order will be delivered in 5 business days, by "
					+ getOrderTime();
			request.setAttribute("message", message);
		}
		// else if (request.getParameter("confirm").equals("CANCEL")) {
		// message = "Order " + orderId + " Cancelled.";
		// request.setAttribute("message", message);
		// }

		session.setAttribute("orderId", null);
		session.setAttribute("cart", null);
		session.setAttribute("ShoppingCart", null);

		session.invalidate();

		request.getRequestDispatcher(url).forward(request, response);
	}

	private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String url, String message)
			throws ServletException, IOException {
		request.setAttribute("message", message);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	private String getOrderTime() {
		Calendar cal = Calendar.getInstance();
		long time = cal.getTimeInMillis() + 2400000; // will add 40 minutes to current time
		DateFormat df = DateFormat.getTimeInstance(DateFormat.LONG);
		return df.format(new Date(time));
	}
}
