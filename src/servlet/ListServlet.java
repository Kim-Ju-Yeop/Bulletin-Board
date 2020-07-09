package servlet;

import com.google.gson.Gson;
import database.SimpleDbBulletin;
import model.BulletinModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListServlet", urlPatterns = "/listServlet")
public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            SimpleDbBulletin simpleDbBulletin = new SimpleDbBulletin();
            List<BulletinModel> list = simpleDbBulletin.getAllList();

            Gson gson = new Gson();
            String jsonString = gson.toJson(list);

            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(jsonString);
        }catch (Exception e){e.printStackTrace();}
    }
}
