package servlet;

import database.SimpleDbBulletin;
import model.BulletinGoodModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet(name = "AddGoodServlet", urlPatterns = "/addGoodServlet")
public class AddGoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            String cookieId = request.getParameter("cookieId");

            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());

            SimpleDbBulletin simpleDbBulletin = new SimpleDbBulletin();
            BulletinGoodModel model = new BulletinGoodModel(id, cookieId, timestamp);

            simpleDbBulletin.writeGoodList(model);
        }catch (Exception e){ e.printStackTrace(); }
    }
}
