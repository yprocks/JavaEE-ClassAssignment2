package onlinestore.servlets;

import onlinestore.dao.PhoneDAO;
import onlinestore.dao.PhoneDAOImpl;
import onlinestore.model.Order;
import onlinestore.model.Phone;

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
import java.util.List;

@WebServlet("/phone")
public class PhoneController extends HttpServlet {

    PhoneDAO phoneDAO = PhoneDAOImpl.getInstance();

    public PhoneController() {
        super();
        phoneDAO.addPhone(new Integer(22), new Phone(22, "Samsung Galaxy S7", 899.00, "Samsung"));
        phoneDAO.addPhone(new Integer(14), new Phone(14, "iPhone 8 32GB", 999.00, "Apple"));
        phoneDAO.addPhone(new Integer(6), new Phone(61, "LG G5", 699.20, "Life Good Co."));
        phoneDAO.addPhone(new Integer(17), new Phone(17, "OnePlus5", 799.70, "One Plus"));
        phoneDAO.addPhone(new Integer(207), new Phone(207, "Samsung Galaxy S8", 999.00, "Samsung"));

    }

    public void init() {
        getServletContext().setAttribute("lastNum", 18907);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String base = "/jsp/";
        String url = "";
        String errorUrl = base + "error.jsp";

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "display-phone":
                    url = base + "order.jsp";
                    displayPhone(request, response, url);
                    break;
                case "add-to-cart":
                    url = base + "cart.jsp";
                    addToCart(request, response, url);
                    break;
                case "place-order":
                    url = base + "message.jsp";
                    confirmOrder(request, response, url);
                    break;
            }
        }

        sendErrorPage(request, response, errorUrl, "Url route not found.");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendErrorPage(request, response, "/jsp/error.jsp", "Get request not supproted! Please post via form.");
    }

    private void displayPhone(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {

        String orderId = request.getParameter("orderId");

        if (orderId == null || orderId.trim().isEmpty()) {
            request.setAttribute("message", "You must supply name to identify your order");
            request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
            return;
        }

        int orderNum = 0;

        synchronized (this) {
            orderNum = (int) getServletContext().getAttribute("lastNum");
            orderNum++;
            getServletContext().setAttribute("lastNum", orderNum);
        }

        HttpSession session = request.getSession();

        session.setAttribute("orderId", orderId + orderNum);

//        PhoneDAO mm = PhoneDAOImpl.getInstance();
        request.setAttribute("list", phoneDAO.getPhone());
        request.getRequestDispatcher("jsp/order.jsp").forward(request, response);
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {

        Order order = new Order();

        List<Phone> phones = phoneDAO.getPhone();

        for (Phone phone : phones) {

            int itemNo = phone.getItemNo();

            String sitemNo = "" + itemNo;

            request.getParameter(sitemNo);

            int quantity = Integer.parseInt(request.getParameter(sitemNo));

            if (quantity > 0) {
                phone.setQuantity(quantity);
                order.addPhone(phone);
            }
        }

        if (order.getOrderedItems().size() < 1) {
            request.setAttribute("message", "Your order is empty");
            request.setAttribute("list", phoneDAO.getPhone());
            request.getRequestDispatcher("jsp/order.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("order", order.getOrderedItems());
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private void confirmOrder(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String message = "thank you";

        String orderId = (String) session.getAttribute("orderId");

        if (orderId == null) {
            message = "ERROR: No order identifier. Did you confirm or cancel already?";
        } else if (session.getAttribute("order") == null) {
            message = "ERROR: You have no order to confirm";
        } else if (request.getParameter("confirm").equals("CONFIRM")) {
            message = "Order " + orderId + " confirmed. Your order will be delivered in 2 business days, by " + getOrderTime();
        } else if (request.getParameter("confirm").equals("CANCEL")) {
            message = "Order " + orderId + " Cancelled.";
        }

        session.setAttribute("orderId", null);

        session.invalidate();

        request.setAttribute("message", message);

        request.getRequestDispatcher(url).forward(request, response);
    }

    private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String url, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
        requestDispatcher.forward(request, response);
    }

    private String getOrderTime() {
        Calendar cal = Calendar.getInstance();
        long time = cal.getTimeInMillis() + 1200000; // 20 minutes = 1200000 ms;
        DateFormat df = DateFormat.getTimeInstance(DateFormat.LONG);
        return df.format(new Date(time));
    }
}
