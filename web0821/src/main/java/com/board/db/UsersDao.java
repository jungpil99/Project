package com.board.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.SqlMapConfig;

public class UsersDao {

	// SqlSessionFactory를 SqlMapConfig를 통하여 생성한다.
	SqlSessionFactory sqlsession_f = SqlMapConfig.getSqlMapInstance();
	SqlSession session;

	public UsersDao() {
		// SqlSessionFactory에서 session을 할당받는다.
		// 이 때 openSession에 true를 주어야 자동 커밋이 된다.
		// default는 false이다.
		session = sqlsession_f.openSession(true);
	}

	public List<UsersDto> selectList(String id) {
		return session.selectList("UsersMapper.selectList", id);
	}

	public int getIdRecords(UsersDto dto) {
		return session.selectOne("UsersMapper.getIdRecords", dto);
	}
	
	public void insertOne(UsersDto dto) {
		session.insert("UsersMapper.insertOne", dto);
	}

	public UsersDto selectOne(UsersDto dto) {
		return session.selectOne("UsersMapper.selectOne", dto);
	}

	public void updateOne(UsersDto dto) {
		session.update("UsersMapper.updateOne", dto);
	}

	public void deleteOne(UsersDto dto) {
		session.delete("UsersMapper.deleteOne", dto);
	}
}