<%@page import="community.CommunityDTO"%>
<%@page import="java.util.List"%>
<%@page import="community.CommunityDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
CommunityDAO dao = new CommunityDAO();
List<CommunityDTO> list = dao.getCommuList();
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
            <h1><a href="index.jsp">DCODLAB</a></h1>

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
            <h1>자유 게시판</h1>
            <div class="wrap">
                <table style="border-collapse: collapse; width: 100%">
                <tr>
                <td align="center" style="width:10%; padding: 1px;
                border-bottom: 1px solid #000000;">번호</td>
                <td align="center" style="width:20%; padding: 1px;
                border-bottom: 1px solid #000000;">글쓴이</td>
                <td align="center" style="padding: 1px;
                border-bottom: 1px solid #000000;">제목</td>
                <td align="center" style="width:20%; padding: 1px;
                border-bottom: 1px solid #000000;">등록일</td>
                <td align="center" style="width:10%; padding: 1px;
                border-bottom: 1px solid #000000;">조회수</td>
                </tr>
<%
for(CommunityDTO dto : list){
%>                
                <tr>
                <td align="center" style="width:10%; padding: 1px;
                border-bottom: 1px solid #000000;"><a href="view.jsp?num=<%=dto.getNum()%>"><%=dto.getNum() %></td>
                <td align="center" style="width:20%; padding: 1px;
                border-bottom: 1px solid #000000;"><%=dto.getWriter() %></td>
                <td align="center" style="padding: 1px;
                border-bottom: 1px solid #000000;"><a href="view.jsp?num=<%=dto.getNum()%>"><%=dto.getTitle() %></a></td>
                <td align="center" style="width:20%; padding: 1px;
                border-bottom: 1px solid #000000;"><%=dto.getRegtime() %></td>
                <td align="center" style="width:10%; padding: 1px;
                border-bottom: 1px solid #000000;"><%=dto.getHits() %></td>
                </tr>
<%
}
%>
                </table>
                <br>
<%
if((String)session.getAttribute("userName") != null){
%>
                <input type="button" value="글쓰기" onclick="location.href='write.jsp'"
                style="width: 10%;
                padding: 10px;
                background-color: #333;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;">
<%
}else{
%>
                <input type="button" value="글쓰기" onclick="alert('로그인 후 이용해주세요.'); location.href='login_form.jsp';"
                style="width: 10%;
                padding: 10px;
                background-color: #333;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;">
<%
}
%>
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