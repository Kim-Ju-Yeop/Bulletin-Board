<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String id = request.getParameter("id");

    String cookieId = null;
    Cookie[] cookies = request.getCookies();

    for (Cookie cookie : cookies) {
        if ("cookieId".equals(cookie.getName())) {
            cookieId = cookie.getValue();
            break;
        }
    }
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>글 상세보기 화면</title>
        <style>
            label {
                display: inline-block;
                width: 100px;
            }
            div {
                padding: 5px;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            $(document).ready(function() {
                getList();
                getGoodUserList();
            });

            function getList() {
                let params = {
                    id : <%= id %>,
                }

                $.post("http://localhost:8080/readServlet", params, function(response) {
                    showList(response);
                });
            }

            function showList(list) {
                $("#title").html(list.title);
                $("#content").html(list.content);
                $("#writer").html(list.writer);

                if('<%=cookieId%>' !== 'null'){
                    if(list.writer.toString() != '<%=cookieId%>'){
                        document.getElementById("edit").style.display = 'none'
                        document.getElementById("delete").style.display = 'none'
                    }
                }else{
                    document.getElementById("edit").style.display = 'none'
                    document.getElementById("delete").style.display = 'none'
                    document.getElementById("addGood").style.display = 'none'
                }
            }

            function getGoodUserList(){
                let params = {
                    id : <%= id %>,
                }

                $.get("http://localhost:8080/getGoodUserServlet", params, function(response) {
                    showGoodUserList(response)
                });
            }

            function showGoodUserList(list){
                $("#goodUser").empty()
                $("#goodCount").html(list.length);

                if(list.length == 0) $("#goodUser").html("현재 좋아요를 누른 사람이 아무도 없습니다.");
                else{
                    $("#goodUser").append(" | ")

                    for(let i in list){
                        $("#goodUser").append(list[i].writerId + " 님 | ")
                        if(list[i].writerId == ('<%=cookieId%>')) document.getElementById("addGood").style.display = 'none'
                    }
                }
            }

            function deleteList(){
                let params = {
                    id : <%= id %>,
                }

                $.get("http://localhost:8080/deleteServlet", params, function (response) {
                    alert("글 삭제를 정상적으로 수행하였습니다.")
                    location.href = "list.html";
                })
            }

            function addGood(){
                let params = {
                    id : <%= id %>,
                    cookieId : '<%=cookieId%>'
                }

                $.post("http://localhost:8080/addGoodServlet", params, function (response) {
                    location.reload()
                })
            }
        </script>
    </head>
    <body>
        <h1>글 상세보기 화면</h1>
        <div>
            <label>제목</label>
            <span id="title"></span>
        </div>
        <div>
            <label>내용</label>
            <textarea id="content"></textarea>
        </div>
        <div>
            <label>글쓴이</label>
            <span id="writer"></span>
        </div>
        <div>
            <label>좋아요 갯수</label>
            <span id="goodCount"></span>
        </div>
        <div>
            <label>좋아요 유저</label>
            <span id="goodUser"></span>
        </div>
        <div>
            <input type="button" value="수정하기" id="edit" onclick="location.href = 'http://localhost:8080/modify.jsp?id=<%=id%>'">
            <input type="button" value="삭제하기" id="delete" onclick="deleteList()">
            <input type="button" value="좋아요" id="addGood" onclick="addGood()">
        </div>
    </body>
</html>
