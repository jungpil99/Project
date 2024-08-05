<%@page import="community.CommunityDTO"%>
<%@page import="community.CommunityDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String num = request.getParameter("num");

CommunityDAO dao = new CommunityDAO();
CommunityDTO dto = dao.getOne(Integer.parseInt(num));

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <script src="https://kit.fontawesome.com/c47106c6a7.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/ie.js"></script>
<title>Insert title here</title>
</head>
<body>
<header>
        <div class="inner">
            <h1><a href="#">DCODLAB</a></h1>

            <ul id="gnb">
                <li><a href="#">DEPARTMENT</a></li>
                <li><a href="#">GALLERY</a></li>
                <li><a href="#">YOUTUBE</a></li>
                <li><a href="community_form.jsp">커뮤니티</a></li>
                <li><a href="#">고객센터</a></li>
            </ul>

            <ul class="util">
                <%
                if((String)session.getAttribute("userName") != null){
                %>
                <li><a href="#"><%=(String)session.getAttribute("userName") %>님</a></li>
                <li><a href="logout.jsp">logout</a></li>
                <%
                }else{
                %>
                <li><a href="login_form.jsp">Login</a></li>
                <li><a href="join_form.jsp">Join</a></li>
                <%
                }
                %>
            </ul>
        </div>
    </header>


    <section>
        <div class="inner">
            <h1>글쓰기</h1>
            <div class="wrap">
                <form method="post" action="update.jsp">
                <input type ="hidden" name="num" value="<%=dto.getNum()%>">

        <table style="border-collapse: separate; border-spacing: 10px;">
        <tr>
            <th>제목</th>
            <td><input type="text" name="title"  maxlength="80"
                       value="<%=dto.getTitle() %>" style="width: 1000px;
                       padding: 10px;
                       margin: 10px 0;
                       border: 1px solid #ccc;
                       border-radius: 5px;"> 
            </td>
        </tr>
        <%
        if((String)session.getAttribute("userName") != null){
        %>
        <tr>
            <th>작성자</th>
            <td style="width: 1000px;
                       padding: 10px;
                       margin: 10px 0;
                       border: 1px solid #ccc;
                       border-radius: 5px;">
                       <%=(String)session.getAttribute("userName") %>
            </td>
        </tr>
        <%
        }
        %>
        <tr>
            <th>내용</th>
            <td><textarea name="content" rows="10" style="width: 1000px;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;"><%=dto.getContent() %></textarea>
            </td>
        </tr>
    </table>

    <br>
    <input type="submit" value="저장" style="width: 10%;
            padding: 10px;
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;">
    <input type="button" value="취소" onclick="history.back()" style="width: 10%;
            padding: 10px;
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;">
</form>
            </div>
        </div>
    </section>

    <footer>
        <div class="inner">
            <div class="upper">
                <h1>DCODELAB</h1>
                <ul>
                    <li><a href="#">Policy</a></li>
                    <li><a href="#">Terms</a></li>
                    <li><a href="#">Family Site</a></li>
                    <li><a href="#">Sitemap</a></li>
                </ul>
            </div>

            <div class="lower">
                <address>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Quas, facere.<br>
                    TEL : 031-111-1234 C.P : 010-1234-5678
                </address>
                <p>
                    2020 DOCDELAB &copy; copyright all right reserved.
                </p>
            </div>
        </div>
    </footer>
</body>
</html>