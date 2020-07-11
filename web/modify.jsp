<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
%>
<html>
    <head>
        <title>글 수정하기 화면</title>
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
            function edit() {
                let params = {
                    id : <%= id %>,
                    title : $("input[id = 'title']").val(),
                    content : $("textarea[id = 'content']").val()
                }

                $.post("http://localhost:8080/modify", params, function (response) {
                    alert("글 수정을 정상적으로 수쟁하였습니다.")
                    location.href = "list.html"
                })
            }
        </script>
    </head>
    <body>
        <h1>글 수정하기 화면</h1>
        <div>
            <label>제목</label>
            <input type="text" id="title">
        </div>
        <div>
            <label>내용</label>
            <textarea id="content"></textarea>
        </div>
        <div>
            <input type="button" value="수정" onclick="edit()">
        </div>
    </body>
</html>
