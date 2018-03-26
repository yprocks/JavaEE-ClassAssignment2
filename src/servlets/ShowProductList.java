package servlets;

import dao.PhoneDAO;
import dao.PhoneDAOImpl;
import model.Phone;
import model.PhoneList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Vector;

//Handle url call
@WebServlet("/ShowProductList")
public class ShowProductList extends HttpServlet
{
    private PhoneDAO phoneDAO= PhoneDAOImpl.getInstance();

    public ShowProductList()
    {
        super();
        phoneDAO.addPhone(1, new Phone("05T89D45", "Samsung Galaxy S9 (64GB)", 7, 959.78, "Grey", "img/s9-front-purple.png"));
        phoneDAO.addPhone(2, new Phone("45GT891S", "Google Pixel 2 XL (128GB)", 5, 1239.00, "Black", "img/pixel_2_xl_front_bw.png"));
        phoneDAO.addPhone(3, new Phone("E8GSF451", "Apple iPhone X (64GB)", 20, 1319.98, "Space Grey", "img/iphone_x_front_silver.png"));
        phoneDAO.addPhone(4, new Phone("89SDF154", "Apple iPhone 8 Plus (256GB)", 10, 1495.54, "Gold", "img/iphone_8_plus_front_silver.png"));
        phoneDAO.addPhone(5, new Phone("SDF498QW", "Samsung Galaxy S9+ (64GB)", 5, 1195.26, "Purple", "img/s9plus-front-gray.png"));
        phoneDAO.addPhone(6, new Phone("ERT14S45", "LG V30 Plus Tone (64GB)", 10, 598.99, "Grey", "img/v30_front.png"));
    }


    //Response
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    //Request
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher= request.getRequestDispatcher("/index.jsp");
        request.setAttribute("list", phoneDAO.getAllPhone());
        dispatcher.forward(request, response);
    }
}
