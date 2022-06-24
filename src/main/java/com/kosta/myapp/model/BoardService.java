package com.kosta.myapp.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.model.dto.BoardEmpVO;
import com.kosta.model.dto.BoardVO;

@Service
public class BoardService {
	@Autowired
	BoardDAOMybatis boardDAO;
	//BoardDAO boardDAO;
	
	public List<BoardEmpVO> selectallJoin() {
		return boardDAO.selectallJoin();
	}
	public List<BoardVO> selectAll() {
		return boardDAO.selectAll();
	}
	
	public BoardVO selectByBno(int bno) {
		return boardDAO.selectByBno(bno);
	}
	public List<BoardVO> selectByWriter(int writer) {
		return boardDAO.selectByWriter(writer);
	}
	public List<BoardVO> selectByTitle(String title) {
		return boardDAO.selectByTitle(title);
	}
	public List<BoardVO> selectByRegdate(Date sdate, Date edate) {
		return boardDAO.selectByRegdate(sdate, edate);
	}
	public int insert(BoardVO bvo) {
		return boardDAO.insert(bvo);
	}
	public int update(BoardVO bvo,int bno) {
		return boardDAO.update(bvo,bno);
	}
	public int delete(int bno) {
		return boardDAO.delete(bno);
	}
	
}
