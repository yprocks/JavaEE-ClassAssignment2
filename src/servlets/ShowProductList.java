package servlets;

import model.ImageList;
import model.ProductList;

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
    public Vector itemList;
    public Vector imageList;

    public void init() throws SecurityException
    {
        itemList = new Vector();
        itemList.add(ProductList.getItem("05T89D45"));
        itemList.add(ProductList.getItem("45GT891S"));
        itemList.add(ProductList.getItem("E8GSF451"));
        itemList.add(ProductList.getItem("89SDF154"));
        itemList.add(ProductList.getItem("SDF498QW"));
        itemList.add(ProductList.getItem("ERT14S45"));

        imageList= new Vector();
        imageList.add(ImageList.getItem("05T89D45"));
        imageList.add(ImageList.getItem("45GT891S"));
        imageList.add(ImageList.getItem("E8GSF451"));
        imageList.add(ImageList.getItem("89SDF154"));
        imageList.add(ImageList.getItem("SDF498QW"));
        imageList.add(ImageList.getItem("ERT14S45"));
    }
    //Response
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    //Request
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher= null;

        //Send to index.jsp page
        dispatcher = request.getRequestDispatcher("/index.jsp");
        request.setAttribute("list", itemList);
        request.setAttribute("listImage", imageList);
        dispatcher.forward(request, response);
    }
}
