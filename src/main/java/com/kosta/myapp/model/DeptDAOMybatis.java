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

//DAO(Data Access Object) : DB�� �����ϴ� ����Ͻ� ������ �ۼ��Ѵ�.
@Repository
public class DeptDAOMybatis {
	@Autowired
	SqlSession sqlSession;
	
	static final String NAMESPACE = "net.dept.";
	//1. �����ȸ
	public List<DeptDTO> selectAll() {
		List<DeptDTO>dlist = sqlSession.selectList(NAMESPACE+"selectAll");
		return dlist;
	}
	//2. Ư���μ���ȸ(�μ��ڵ�� ��ȸ)
	public DeptDTO selectById(int deptid) {
		return sqlSession.selectOne(NAMESPACE+"selectById", deptid);
	}
	//3. �����ڵ�� ��ȸ
	public List<DeptDTO> selectByLocation(int locid) {
		return sqlSession.selectList(NAMESPACE+"selectByLocation", locid);
	}
	//4. �űԺμ��Է�
	public int deptInsert(DeptDTO dept) {
		return sqlSession.insert(NAMESPACE+"deptInsert",dept );
	}
	//5. Ư���μ�����
	public int deptUpdate(DeptDTO dept) {
		return sqlSession.update(NAMESPACE+"deptUpdate", dept);
	}
	//6. Ư���μ�����
	public int deptDelete(int deptid) {
		return sqlSession.delete(NAMESPACE+"deptDelete",deptid );
	}
}
