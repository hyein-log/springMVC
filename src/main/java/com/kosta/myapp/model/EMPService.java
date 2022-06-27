package com.kosta.myapp.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.model.dto.EMPVO;
import com.kosta.model.dto.JobVO;
// 사용자 -> controller --> service --> DAO -> DB
//		<-			  <--		  <--	  <-
@Service
public class EMPService {
	
	@Autowired
	EMPDAOMybatis empDAO ;
	//1.모든직원조회
		public List<EMPVO> selectAll() {
			return empDAO.selectAll();
			
		}
		public List<JobVO> selectJobAll() {
			return empDAO.selectJobAll();
		}
		public List<EMPVO> selectManagerAll() {
			return empDAO.selectManagerAll();
		}
		//2.조건조회(특정부서)
		public List<EMPVO> selectByDept(int deptid) {
			return empDAO.selectByDept(deptid);
			
		}
		//3.조건조회(특정매니저)
		public List<EMPVO> selectByManager(int mid) {
			return empDAO.selectByManager(mid);
			
		}
		//4.조건조회(특정JOB)
		public List<EMPVO> selectByJob(String job_id) {
			return empDAO.selectByJob(job_id);
			
		}
		//5.조건조회(특정 deptid, jobid, salary, hire_date)
		public List<EMPVO> selectByCondition(int deptid, String job_id, double sal, String hire_date) {
			return empDAO.selectByCondition(deptid, job_id, sal, hire_date);
			
		}
		//6.특정직원 한건만 조회
		public EMPVO selectById(int empid) {
			return empDAO.selectById(empid);
			
		}
		public EMPVO selectByEmail(String empEmail) {
			return empDAO.selectByEmail(empEmail);
		}
		//7.insert
		public int empInsert(EMPVO emp) {
			return empDAO.empInsert(emp);
		}
		//8.update(특정직원1건 수정)
		public int empUpdate(EMPVO emp) {
			return empDAO.empUpdate(emp);
		}
		
		//10.delete(특정직원 1건 삭제)
		public int empDelete(int empid) {
			return empDAO.empDelete(empid);
		}
		//11.delete(조건 department_id에 해당하는 직원을 삭제)
		public int empDeleteByDept(int deptid) {
			return empDAO.empDeleteByDept(deptid);
		}
		public int selectByEmailDuplication(String email) {
			return empDAO.selectByEmailDuplication(email);
		}
}
