package servlet;

import database.SimpleDbBulletin;
import model.BulletinModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModifyServlet", urlPatterns = "/modify")
public class ModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            int hit = 0;
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String writer = "글쓴이";

            BulletinModel model = new BulletinModel(id, hit, title, content, writer);

            SimpleDbBulletin bulletin = new SimpleDbBulletin();
            bulletin.updateList(model);
        }catch (Exception e){ e.printStackTrace(); }
    }
}
