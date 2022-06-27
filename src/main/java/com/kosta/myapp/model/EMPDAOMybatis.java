package com.kosta.myapp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.model.dto.EMPVO;
import com.kosta.model.dto.JobVO;
import com.kosta.util.DBUtil;

@Repository
//CRUD작업 INSERT, SELECT, UPDATE, DELETE -> DAO ; DATA ACCESS OBJECT
public class EMPDAOMybatis {
	
	@Autowired
	SqlSession session;
	
	static final String NAMESPACE="net.emp.";
	Logger logger = LoggerFactory.getLogger(EMPDAOMybatis.class);
	//1.모든직원조회
	public List<EMPVO> selectAll() {
		List<EMPVO> elist = session.selectList(NAMESPACE+"selectAll");
		logger.info("selectall : "+elist.size()+"건이 조회됐습니다.");
		return  elist;
	}
	public List<JobVO> selectJobAll() {
		List<JobVO> jlist = session.selectList(NAMESPACE+"selectJobAll");
		logger.info("selectJobAll : "+jlist.size()+"건이 조회됐습니다.");
		return jlist;
	}
	public List<EMPVO> selectManagerAll() {
		List<EMPVO> elist = session.selectList(NAMESPACE+"selectManager");
		logger.info("selectByManager : "+elist.size()+"건이 조회됐습니다.");
		return  elist;
	}
	
	//2.조건조회(특정부서)
	public List<EMPVO> selectByDept(int deptid) {
		List<EMPVO> elist = session.selectList(NAMESPACE+"selectByDept",deptid );
		logger.info("selectByDept : "+elist.size()+"건이 조회됐습니다.");
		return  elist;
	}
	//3.조건조회(특정매니저)
	public List<EMPVO> selectByManager(int mid) {
		List<EMPVO> elist = session.selectList(NAMESPACE+"selectByManager",mid );
		logger.info("selectByManager : "+elist.size()+"건이 조회됐습니다.");
		return  elist;
	}
	//4.조건조회(특정JOB)
	public List<EMPVO> selectByJob(String job_id) {
		List<EMPVO> elist = session.selectList(NAMESPACE+"selectByJob",job_id );
		logger.info("selectByJob : "+elist.size()+"건이 조회됐습니다.");
		return  elist;
	}
	//5.조건조회(특정 deptid, jobid, salary, hire_date)
	public List<EMPVO> selectByCondition(int deptid, String job_id, double sal, String hire_date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hire_date", hire_date);
		map.put("salary", sal);
		map.put("department_id",deptid);
		map.put("job_id",job_id);
		List<EMPVO> elist = session.selectList(NAMESPACE+"selectByCondition",map );
		logger.info("selectByCondition : "+elist.size()+"건이 조회됐습니다.");
		return  elist;
	}
	//6.특정직원 한건만 조회
	public EMPVO selectById(int empid) {
		EMPVO elist = session.selectOne(NAMESPACE+"selectById",empid );
		logger.info("selectById : "+elist.toString());
		return  elist;
	}
	public EMPVO selectByEmail(String empEmail) {
		EMPVO elist = session.selectOne(NAMESPACE+"selectByEmail",empEmail );
		logger.info("selectByEmail : "+elist.toString());
		return  elist;
	}
	//7.insert
	public int empInsert(EMPVO emp) {
		int result = session.insert(NAMESPACE+"insert",emp );
		logger.info("empInsert : "+result+"건 입력");
		return  result;
	}
	//8.update(특정직원1건 수정)
	public int empUpdate(EMPVO emp) {
		int result = session.update(NAMESPACE+"update",emp );
		logger.info("empUpdate : "+result+"건 수정");
		return  result;
	}
	
	//10.delete(특정직원 1건 삭제)
	public int empDelete(int empid) {
		int result = session.update(NAMESPACE+"delete",empid );
		logger.info("empDelete : "+result+"건 삭제");
		return  result;
	}
	//11.delete(조건 department_id에 해당하는 직원을 삭제)
	public int empDeleteByDept(int deptid) {
		int result = session.update(NAMESPACE+"deleteByDept",deptid );
		logger.info("empDeleteByDept : "+result+"건 삭제");
		return  result;
	}
	public int selectByEmailDuplication(String email) {
		int result = session.update(NAMESPACE+"selectByEmailDuplication",email );
		logger.info("selectByEmailDuplication : "+result+"건 조회");
		return  result;
	}
}
