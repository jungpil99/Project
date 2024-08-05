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
String email = request.getParameter("email");
String tel = request.getParameter("tel");

CustomerDTO dto = new CustomerDTO(id, pwd, name, email, tel);

boolean join = dao.joinCustomer(dto);

if(join = true){
	out.println("<script type='text/javascript'>");
    out.println("alert('회원가입 성공')");
    out.println("location.href='index.jsp'");
    out.println("</script>");
}else{
	out.println("<script type='text/javascript'>");
    out.println("alert('회원가입 실패')");
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