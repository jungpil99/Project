package com.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.db.*;
import com.board.service.BoardService;
import com.board.service.UsersService;

@WebServlet("/")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BoardController() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                                 throws ServletException, IOException {
        String view = null;

        // URL에서 프로젝트 이름 뒷 부분의 문자열 얻어내기
        String uri = request.getRequestURI();
        String conPath = request.getContextPath();
        String com = uri.substring(conPath.length());
        
        HttpSession session = request.getSession();
		UsersDto userInfo = (UsersDto)session.getAttribute("customInfo");
        
        // 주어진 URL에 따라 지정된 동작 수행
        if(com.equals("/home") || com.equals("/")) {
        	if (userInfo != null) {
    			session.setAttribute("customInfo", userInfo);
    			request.setAttribute("userLoggedIn", true);
    	    }
        	view = "08/home.jsp";
        } else if (com.equals("/list")) {
            String tmp = request.getParameter("page");
            int pageNo = (tmp != null && tmp.length() > 0)
                    ? Integer.parseInt(tmp) : 1;
            
    		if (userInfo != null) {
    			session.setAttribute("customInfo", userInfo);
    			request.setAttribute("userLoggedIn", true);
    	    }
    		
            request.setAttribute("msgList",
                    new BoardService().getMsgList(pageNo));
            request.setAttribute("pgnList",
                    new BoardService().getPagination(pageNo));
            view = "list.jsp";
        } else if (com.equals("/view")){
            int num = Integer.parseInt(request.getParameter("num"));
            boolean flag = false;
            
            BoardDto msg = new BoardService().getMsg(num);
    		if (userInfo != null) {
    			session.setAttribute("customInfo", userInfo);
    			request.setAttribute("userLoggedIn", true);
    			
    			if(userInfo.getName().equals(msg.getWriter())) {
    				flag = true;
    			}
    	    }
    		
    		request.setAttribute("update", flag);
            request.setAttribute("msg", msg);
            view = "view.jsp";

        } else if (com.equals("/write")){
            String tmp = request.getParameter("num");
            int num = (tmp != null && tmp.length() > 0)
                    ? Integer.parseInt(tmp) : 0;

            BoardDto dto = new BoardDto();
            String action = "insert";
            
    		if (userInfo != null) {
    			session.setAttribute("customInfo", userInfo);
    			request.setAttribute("userLoggedIn", true);
    	    }
            
            if (num > 0) {
                dto = new BoardService().getMsgForWrite(num);
                action = "update?num=" + num;
            }

            request.setAttribute("msg", dto);
            request.setAttribute("action", action);
            view = "write.jsp";

        } else if (com.equals("/insert")){
            request.setCharacterEncoding("utf-8");
            String writer  = request.getParameter("writer" );
            String title   = request.getParameter("title"  );
            String content = request.getParameter("content");

            try {
                new BoardService().writeMsg(writer, title, content);
                view = "redirect:list";

            } catch(Exception e) {
                request.setAttribute("errorMessage", e.getMessage());
                view = "errorBack.jsp";
            }

        } else if (com.equals("/update")){
            request.setCharacterEncoding("utf-8");
            int num = Integer.parseInt(request.getParameter("num"));
            String writer  = request.getParameter("writer" );
            String title   = request.getParameter("title"  );
            String content = request.getParameter("content");

            try {
                new BoardService().updateMsg(writer, title, content, num);
                view = "redirect:list";

            } catch(Exception e) {
                request.setAttribute("errorMessage", e.getMessage());
                view = "errorBack.jsp";
            }

        } else if (com.equals("/delete")){
            int num = Integer.parseInt(request.getParameter("num"));

            new BoardService().deleteMsg(num);
            view = "redirect:list";
        } else if(com.equals("/loginform")) {
        	view = "sign-in/loginform.jsp";
        } else if(com.equals("/login")) {
        	request.setCharacterEncoding("utf-8");
        	String id = request.getParameter("id");
        	String pwd = request.getParameter("pwd");
        	
        	UsersDto dto = new UsersDto(id, pwd, "", "", "", "");
    		UsersDao dao = new UsersDao();
    		int login = dao.getIdRecords(dto);
    		
    		try {
    			if(login == 1) {
        			dto = dao.selectOne(dto);
            		session.setAttribute("customInfo", dto);
            		request.setAttribute("userLoggedIn", true);
            		view = "08/home.jsp";
        		}else {
        			throw new Exception("아이디 또는 비밀번호가 일치하지 않습니다");
        		}
			} catch (Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
                view = "errorBack.jsp";
			}
    		
    		
        } else if(com.equals("/joinform")) {
        	view = "joinform.jsp";
        } else if(com.equals("/join")) {
        	request.setCharacterEncoding("utf-8");
        	String id = request.getParameter("id");
        	String pwd = request.getParameter("pwd");
        	String name = request.getParameter("name");
        	String email = request.getParameter("email");
        	String tel = request.getParameter("tel");
        	
        	UsersDao dao = new UsersDao();
        	UsersDto dto = new UsersDto(id, pwd, name, email, tel, "");
        	
        	try {
				dao.insertOne(dto);
				System.out.println("회원가입");
				view = "redirect:home";
			} catch (Exception e) {
				request.setAttribute("errorMessage", e.getMessage());
                view = "errorBack.jsp";
			}
        	
        }else if(com.equals("/logout")) {
     		session.removeAttribute("customInfo");
        	view = "08/home.jsp";
        }else if(com.equals("/error")) {
        	try {
        		throw new Exception("권한이 없습니다");
        	}catch (Exception e){
        		request.setAttribute("errorMessage", e.getMessage());
                view = "errorBack.jsp";
        	}
        	
        }else if(com.equals("/infoform")) {
        	if (userInfo != null) {
    			session.setAttribute("customInfo", userInfo);
    			request.setAttribute("userLoggedIn", true);
    	    }
        	
        	view = "infoform.jsp";
        }else if(com.equals("/info")) {
        	
        	request.setCharacterEncoding("utf-8");
        	String id = request.getParameter("id");
        	String pwd = request.getParameter("pwd");
        	String email = request.getParameter("email");
        	String tel = request.getParameter("tel");
        	
        	UsersDao dao = new UsersDao();
        	UsersDto dto = new UsersDto(id, pwd, "", email, tel, "");
        	
        	if (userInfo != null) {
    			session.setAttribute("customInfo", userInfo);
    			request.setAttribute("userLoggedIn", true);
    	    }
        	
        	dao.updateOne(dto);
        	view = "08/home.jsp";
        }else if(com.equals("/userDe")) {
        	request.setCharacterEncoding("utf-8");
        	String id = request.getParameter("id");
        	
        	UsersDao dao = new UsersDao();
        	UsersDto dto = new UsersDto(id, "", "", "", "", "");
        	dao.deleteOne(dto);
        	
        	view = "08/home.jsp";
        }
        // view에 담긴 문자열에 따라 포워딩 또는 리다이렉팅
        if (view.startsWith("redirect:")) {
            response.sendRedirect(view.substring(9));
        } else {
            request.getRequestDispatcher(view).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                                  throws ServletException, IOException {
        doGet(request, response);
    }
}