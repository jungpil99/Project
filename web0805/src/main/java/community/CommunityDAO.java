package community;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBUtill;

public class CommunityDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private String COMMU_LIST = "select * from community";
	private String COMMU_ADD = "insert into community (writer, title, content, regtime, hits) "
			+ "values(?, ?, ?, now(), 0)";
	private String COMMU_GETONE = "select * from community where num =?";
	private String COMMU_UPDATE = "update community set writer=?, title=?, content=? where num = ?";
	private String COMMU_DELETE = "delete from community where num = ?";
	private String COMMU_HITS = "update community set hits = hits + 1 where num = ? ";
	
	public List<CommunityDTO> getCommuList(){
		List<CommunityDTO> list = new ArrayList<CommunityDTO>();
		
		conn = JDBUtill.getConnection();
		try {
			stmt = conn.prepareStatement(COMMU_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				CommunityDTO dto = new CommunityDTO(rs.getInt("num"), rs.getString("writer")
						, rs.getString("title"), rs.getString("content")
						, rs.getString("regtime"), rs.getInt("hits")); 
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBUtill.close(rs, stmt, conn);
		}
		
		return list;
	}
	
	public void insertCommu(CommunityDTO dto) {
		conn = JDBUtill.getConnection();
		try {
			stmt = conn.prepareStatement(COMMU_ADD);
			stmt.setString(1, dto.getWriter());
			stmt.setString(2, dto.getTitle());
			stmt.setString(3, dto.getContent());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBUtill.close(stmt, conn);
		}
	}
	
	public CommunityDTO getOne(int num) {
		CommunityDTO dto = null;
		conn = JDBUtill.getConnection();
		try {
			stmt = conn.prepareStatement(COMMU_GETONE);
			stmt.setInt(1, num);
			rs = stmt.executeQuery();
			if(rs.next()) {
				dto = new CommunityDTO(rs.getInt("num"), rs.getString("writer")
						, rs.getString("title"), rs.getString("content")
						, rs.getString("regtime"), rs.getInt("hits"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBUtill.close(rs, stmt, conn);
		}
		
		return dto;
	}
	
	public void updateCommu(CommunityDTO dto) {
		conn = JDBUtill.getConnection();
		try {
			stmt = conn.prepareStatement(COMMU_UPDATE);
			stmt.setString(1, dto.getWriter());
			stmt.setString(2, dto.getTitle());
			stmt.setString(3, dto.getContent());
			stmt.setInt(4, dto.getNum());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBUtill.close(stmt, conn);
		}
	}
	
	public void deleteCommu(int num) {
		conn = JDBUtill.getConnection();
		try {
			stmt = conn.prepareStatement(COMMU_DELETE);
			stmt.setInt(1, num);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBUtill.close(stmt, conn);
		}
	}
	
	public void hitsCommu(int num) {
		conn = JDBUtill.getConnection();
		try {
			stmt = conn.prepareStatement(COMMU_HITS);
			stmt.setInt(1, num);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBUtill.close(stmt, conn);
		}
	}
}
