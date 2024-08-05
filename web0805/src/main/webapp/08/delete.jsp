<%@page import="community.CommunityDTO"%>
<%@page import="community.CommunityDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String num = request.getParameter("num");

CommunityDAO dao = new CommunityDAO();
dao.deleteCommu(Integer.parseInt(num));

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