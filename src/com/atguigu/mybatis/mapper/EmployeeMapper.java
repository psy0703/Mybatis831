package com.atguigu.mybatis.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.atguigu.mybatis.pojo.Employee;

public interface EmployeeMapper {
	
	List<Employee> getEmployeeById(Employee employee);
	
	void updateEmployee(Employee employee);
	
	List<Employee> getEmployeeBySingleCondition(Employee employee);
	
	List<Employee> getEmployeeByCondition(List<Object> conditions);

	List<Employee> getEmployeeByCondition(Object args);
	
	void batchInsert(List<Employee> emps);
	
	List<Employee> getEmployeeByEmail(String email);
	
}
