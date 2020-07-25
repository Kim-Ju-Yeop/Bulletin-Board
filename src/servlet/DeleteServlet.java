package servlet;

import database.SimpleDbBulletin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", urlPatterns = "/deleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));

            SimpleDbBulletin simpleDbBulletin = new SimpleDbBulletin();
            simpleDbBulletin.deleteList(id);
            simpleDbBulletin.deleteGoodUserList(id);
        }catch (Exception e){ e.printStackTrace(); }
    }
}
