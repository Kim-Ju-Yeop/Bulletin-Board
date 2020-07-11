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

@WebServlet(name = "HitServlet", urlPatterns = "/hitServlet")
public class HitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            String cookieId = request.getParameter("cookieId");

            SimpleDbBulletin simpleDbBulletin = new SimpleDbBulletin();

            int hit = simpleDbBulletin.getHit(id);
            simpleDbBulletin.updateHit(hit, id);

            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());

            BulletinGoodModel bulletinGoodModel = new BulletinGoodModel(id, cookieId, timestamp);
            simpleDbBulletin.writeHitList(bulletinGoodModel);

        }catch (Exception e){ e.printStackTrace(); }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
