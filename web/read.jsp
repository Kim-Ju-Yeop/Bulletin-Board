<%@ page import="java.net.URLDecoder" %>
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

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판</title>
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
            readWriting();
            readHitUser();
        });

        function readWriting() {
            let params = {
                id : <%= id %>,
            }

            $.post("http://localhost:8080/readServlet", params, function(response) {
                showWriting(response);
            });
        }

        function showWriting(writing) {
            $("#title").html(writing.title);
            $("#content").html(writing.content);
            $("#writer").html(writing.writer);
            $("#hitCount").html(writing.hit);

            if('<%=cookieId%>' !== 'null'){
                if(writing.writer.toString() != '<%=cookieId%>'){
                    document.getElementById("edit").style.display = 'none'
                    document.getElementById("delete").style.display = 'none'
                }
            }else{
                document.getElementById("edit").style.display = 'none'
                document.getElementById("delete").style.display = 'none'
                document.getElementById("hit").style.display = 'none'
            }
        }

        function deleteList(){
            let params = {
                id : <%= id %>,
            }

            $.get("http://localhost:8080/deleteServlet", params, function (response) {
                alert("정상적으로 삭제되었습니다.")
                location.href = "list.html";
            })
        }

        function readHitUser(){
            let params = {
                id : <%= id %>,
            }

            $.get("http://localhost:8080/readServlet", params, function(response) {
                showHitUser(response)
            });
        }

        function showHitUser(list){
            $("#hitUser").empty()
            if(list.length == 0){
                $("#hitUser").html("현재 좋아요를 누른 사람이 아무도 없습니다.");
            }else{
                for(let i in list){
                    $("#hitUser").append(list[i].writerId + " ")

                    if(list[i].writerId == ('<%=cookieId%>')){
                        document.getElementById("hit").style.display = 'none'
                    }
                }
            }
        }

        function addHit(){
            let params = {
                id : <%= id %>,
                cookieId : '<%=cookieId%>'
            }

            $.post("http://localhost:8080/hitServlet", params, function (response) {
                location.reload()
            })

        }

    </script>
</head>
<body>
    <h1>게시판 - 글읽기</h1>

    <div>
        <label>제목</label>
        <span id="title">안녕하세요</span>
    </div>

    <div>
        <label>내용</label>
        <textarea id="content">반갑습니다.</textarea>
    </div>

    <div>
        <label>글쓴이</label>
        <span id="writer">홍길동</span>
    </div>

    <div>
        <label>좋아요 갯수</label>
        <span id="hitCount">0</span>
    </div>

    <div>
        <label>좋아요 누른 사용자</label>
        <span id="hitUser">홍길동</span>
    </div>

    <div>
        <a href="http://localhost:8080/modify.jsp?id=<%=id%>"><input type="button" value="수정하기" id="edit"></a>
        <input type="button" value="삭제하기" id="delete" onclick="deleteList()">
        <input type="button" value="좋아요" id="hit" onclick="addHit()">
    </div>
</body>
</html>
