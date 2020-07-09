package servlet;

import database.SimpleDbBulletin;
import model.BulletinModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WriteServlet", urlPatterns = "/writeServlet")
public class WriteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            SimpleDbBulletin simpleDbBulletin = new SimpleDbBulletin();

            int id = simpleDbBulletin.getId() + 1;
            int hit = 0;
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String writer = request.getParameter("writer");

            BulletinModel model = new BulletinModel(id, hit, title, content, writer);
            simpleDbBulletin.writeList(model);

            response.getWriter().print("정상적으로 게시글을 작성하였습니다.");

        }catch (Exception e){ e.printStackTrace(); }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
