<%@page import="community.CommunityDTO"%>
<%@page import="community.CommunityDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String num = request.getParameter("num");
String title = request.getParameter("title");
String writer = (String)session.getAttribute("userName");
String content = request.getParameter("content");

CommunityDAO dao = new CommunityDAO();
CommunityDTO dto = new CommunityDTO(Integer.parseInt(num), writer, title, content, null, 0);
dao.updateCommu(dto);

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