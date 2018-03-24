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

    private PhoneDAO phoneDAO = PhoneDAOImpl.getInstance();

    public PhoneController() {
        super();
        phoneDAO.addPhone(139, new Phone(139, "Samsung Galaxy S8", 999.00, "Samsung", "s8.png"));
        phoneDAO.addPhone(122, new Phone(122, "iPhone 10 64GB", 1299.00, "Apple", "iPhoneX.png"));
        phoneDAO.addPhone(121, new Phone(121, "iPhone 8 32GB", 899.00, "Apple", "iphone8.png"));
        phoneDAO.addPhone(127, new Phone(127, "OnePlus5T", 999.70, "One Plus", "5t.png"));
        phoneDAO.addPhone(148, new Phone(148, "Samsung Note 8", 1199.00, "Samsung", "Note8.png"));

    }

    public void init() {
        getServletContext().setAttribute("lastNum", 18907);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String base = "/jsp/";
        String url;

        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "display-phone":
                    url = base + "order.jsp";
                    displayPhone(request, response, url);
                    return;
                case "add-to-cart":
                    url = base + "cart.jsp";
                    addToCart(request, response, url);
                    return;
                case "place-order":
                    url = base + "message.jsp";
                    confirmOrder(request, response, url);
                    return;
            }
        }

        sendErrorPage(request, response, "/jsp/error.jsp", "Routing not valid");
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

        int orderNum;

        synchronized (this) {
            orderNum = (int) getServletContext().getAttribute("lastNum");
            orderNum++;
            getServletContext().setAttribute("lastNum", orderNum);
        }

        HttpSession session = request.getSession();

        session.setAttribute("orderId", orderId.replaceAll("\\s+", "").toUpperCase() + orderNum);

        request.setAttribute("list", phoneDAO.getPhone());
        request.getRequestDispatcher(url).forward(request, response);
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
            message = "ERROR: Seems like your session has expired. Did you confirm or cancel already?";
            request.setAttribute("errormessage", message);
        } else if (session.getAttribute("order") == null) {
            message = "ERROR: You have no order to confirm";
            request.setAttribute("errormessage", message);
        } else if (request.getParameter("confirm").equals("CONFIRM")) {
            message = "Order " + orderId + " confirmed. Your order will be delivered in 5 business days, by " + getOrderTime();
            request.setAttribute("message", message);
        } else if (request.getParameter("confirm").equals("CANCEL")) {
            message = "Order " + orderId + " Cancelled.";
            request.setAttribute("message", message);
        }

        session.setAttribute("orderId", null);

        session.invalidate();

        request.getRequestDispatcher(url).forward(request, response);
    }

    private void sendErrorPage(HttpServletRequest request, HttpServletResponse response, String url, String message) throws ServletException, IOException {
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
