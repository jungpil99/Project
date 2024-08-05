package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBUtill;

public class CustomerDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private String CUS_LOGIN = "select * from customer where id = ? and pwd = ?";
	private String CUS_JOIN = "insert into customer (id, pwd, name, email, tel) values (?, ?, ?, ?, ?)";
	private String CUS_GETONE = "select * from board where id =?";
	
	public CustomerDTO getlogin(String id, String pwd) {
		CustomerDTO dto = null;
		conn = JDBUtill.getConnection();
		try {
			stmt = conn.prepareStatement(CUS_LOGIN);
			stmt.setString(1, id);
			stmt.setString(2, pwd);
			rs = stmt.executeQuery();
			if(rs.next()) {
				dto = new CustomerDTO(rs.getString("id"), rs.getString("pwd")
						,rs.getString("name"), rs.getString("email")
						,rs.getString("tel"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBUtill.close(rs, stmt, conn);
		}
		return dto;
	}
	
	public boolean joinCustomer(CustomerDTO dto) {
		boolean join = false;
		conn = JDBUtill.getConnection();
		int count = 0;
		try {
			stmt = conn.prepareStatement(CUS_JOIN);
			stmt.setString(1, dto.getId());
			stmt.setString(2, dto.getPwd());
			stmt.setString(3, dto.getName());
			stmt.setString(4, dto.getEmail());
			stmt.setString(5, dto.getTel());
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBUtill.close(stmt, conn);
		} 
		
		if(count != 0) {
			join = true;
		}else {
			join = false;
		}
		return join;
	}
	
}
