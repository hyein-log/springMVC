package com.kosta.myapp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.model.dto.DeptDTO;
import com.kosta.util.DBUtil;

//DAO(Data Access Object) : DB에 접근하는 비즈니스 로직을 작성한다.
@Repository
public class DeptDAOMybatis {
	@Autowired
	SqlSession sqlSession;
	
	static final String NAMESPACE = "net.dept.";
	//1. 모두조회
	public List<DeptDTO> selectAll() {
		List<DeptDTO>dlist = sqlSession.selectList(NAMESPACE+"selectAll");
		return dlist;
	}
	//2. 특정부서조회(부서코드로 조회)
	public DeptDTO selectById(int deptid) {
		return sqlSession.selectOne(NAMESPACE+"selectById", deptid);
	}
	//3. 지역코드로 조회
	public List<DeptDTO> selectByLocation(int locid) {
		return sqlSession.selectList(NAMESPACE+"selectByLocation", locid);
	}
	//4. 신규부서입력
	public int deptInsert(DeptDTO dept) {
		return sqlSession.insert(NAMESPACE+"deptInsert",dept );
	}
	//5. 특정부서수정
	public int deptUpdate(DeptDTO dept) {
		return sqlSession.update(NAMESPACE+"deptUpdate", dept);
	}
	//6. 특정부서삭제
	public int deptDelete(int deptid) {
		return sqlSession.delete(NAMESPACE+"deptDelete",deptid );
	}
}
