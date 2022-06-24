package com.kosta.myapp.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.model.dto.BoardEmpVO;
import com.kosta.model.dto.BoardVO;
import com.kosta.util.DBUtil;

@Repository
public class BoardDAOMybatis {
	@Autowired
	SqlSession sqlSession;
	//DataSource ds;
	static final String NAMESPACE = "net.board.";
	
	public List<BoardEmpVO> selectallJoin() {
		return sqlSession.selectList(NAMESPACE+"boardEmpInfo");
	}
	public List<BoardVO> selectAll() {
		return sqlSession.selectList(NAMESPACE+"selectAll");
	}

	public BoardVO selectByBno(int bno) {
		return sqlSession.selectOne(NAMESPACE+"selectByBno", bno);
	}

	public List<BoardVO> selectByWriter(int writer) {
		return sqlSession.selectList(NAMESPACE+"selectBywriter", writer);
	}

	public List<BoardVO> selectByTitle(String title) {
		return sqlSession.selectList(NAMESPACE+"selectBytitle", title);
	}

	public List<BoardVO> selectByRegdate(Date sdate, Date edate) {
		Map<String, Date> datemap = new HashMap<String, Date>();
		datemap.put("sdate", sdate);
		datemap.put("edate", edate);
		return sqlSession.selectList(NAMESPACE+"selectByregdate", datemap);
	}

	public int insert(BoardVO bvo) {
		return sqlSession.insert(NAMESPACE+"insert", bvo);
	}

	public int update(BoardVO bvo, int bno) {
		return sqlSession.update(NAMESPACE+"update", bvo);
	}

	public int delete(int bno) {
		return sqlSession.delete(NAMESPACE+"delete", bno);
	}

}
