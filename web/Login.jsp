<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String cookieId = null;
    Cookie[] cookies = request.getCookies();

    String id = request.getParameter("id");
    String pw = request.getParameter("pw");

    for(Cookie cookie : cookies){
        if ("cookieId".equals(cookie.getName())) {
            cookieId = cookie.getValue();
            break;
        }
    }

    if(cookieId != null){
        response.sendRedirect("list.html");
    }

    if("1234".equals(pw)){
        Cookie cookie = new Cookie("cookieId", id);
        response.addCookie(cookie);

        System.out.println(cookie.getValue());
        System.out.println();
        response.sendRedirect("list.html");
    }
%>
<html>
    <head>
        <title>로그인 화면</title>
        <style>
            input{
                border: none;
                border-bottom: 1px solid #a1a1a1;
            }
        </style>
    </head>
    <body>
        <div>
            <form method="post" action="Login.jsp">
                <p>ID</p>
                <input type="text" name="id">
                <p>비밀번호</p>
                <input type="password" name="pw"><br><br>
                <button>로그인</button>
            </form>
            <a href="list.html"><button>로그인없이 이용하기</button></a>
        </div>
    </body>
</html>
