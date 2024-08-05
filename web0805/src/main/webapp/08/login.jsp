<%@page import="customer.CustomerDTO"%>
<%@page import="customer.CustomerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
CustomerDAO dao = new CustomerDAO();

request.setCharacterEncoding("UTF-8");
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
String name = request.getParameter("name");

CustomerDTO dto = dao.getlogin(id, pwd);

if(dto != null){
	session.setAttribute("userId",   dto.getId());
    session.setAttribute("userName", dto.getName());
    response.sendRedirect("index.jsp");
}else{
	out.println("<script type='text/javascript'>");
    out.println("alert('로그인 실패')");
    out.println("history.back();");
    out.println("</script>");
}


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

</body>
</html>