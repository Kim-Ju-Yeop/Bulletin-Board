package servlet;

import database.SimpleDbBulletin;
import model.BulletinModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WriteServlet", urlPatterns = "/writeServlet")
public class WriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookieId = null;
        Cookie[] cookies = request.getCookies();

        for(Cookie cookie : cookies){
            if ("cookieId".equals(cookie.getName())) {
                cookieId = cookie.getValue();
                break;
            }
        }

        try{
            SimpleDbBulletin simpleDbBulletin = new SimpleDbBulletin();

            int id = simpleDbBulletin.getId() + 1;
            int hit = 0;
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String writer = cookieId;

            BulletinModel model = new BulletinModel(id, hit, title, content, writer);
            simpleDbBulletin.writeList(model);
        }catch (Exception e){ e.printStackTrace(); }
    }
}
