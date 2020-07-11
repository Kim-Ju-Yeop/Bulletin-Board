package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    숫자 0의 의미 : 로그인 한 기록이 존재하지 않다는 것을 의미한다.
    숫자 1의 의미 : 로그인 한 기록이 존재한다는 것을 의미한다.
*/

@WebServlet(name = "CheckLoginServlet", urlPatterns = "/checkLoginServlet")
public class CheckLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookieId = null;
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if ("cookieId".equals(cookie.getName())) {
                cookieId = cookie.getValue();
                break;
            }
        }

        if(cookieId == null) response.getWriter().print(0);
        else response.getWriter().print(1);
    }
}
