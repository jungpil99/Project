<%@page import="community.CommunityDAO"%>
<%@page import="community.CommunityDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String title = request.getParameter("title");
String writer = (String)session.getAttribute("userName");
String content = request.getParameter("content");

CommunityDAO dao = new CommunityDAO();
CommunityDTO dto = new CommunityDTO(0, writer, title, content, null, 0);
dao.insertCommu(dto);

response.sendRedirect("community_form.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>