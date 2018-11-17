package com.atguigu.mybatis.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.mybatis.mapper.EmployeeMapper;
import com.atguigu.mybatis.pojo.Employee;



/*
 * 1. 动态sql： sql不是写死的，是可以发生变化的！
 * 					mybatis提供了一系列常用的标签，动态地修改或生成sql语句！
 * 
 * 		类似于jstl;
 * 
 * 2.  常用的标签：
 *  		if: 判断
 *  		trim:  对sql进行裁剪，进行加上或者裁剪前后缀！
 *  		choose:  类似分支
 *  			when: 带break的case
 *  			otherwise:  default语句块
 *  		foreach:   遍历集合！
 *  			collection:  集合的参数名！
 *  					数组： array
 *  					list：  list/collection
 *  					map:   ①使用@Param自定义 或者 ②_parameter
 *  
 *  			item:  
 *  					如果是list,代表参数值
 *  					如果是Map,代表value
 *  
 *  		核心：  所有的标签，传入的都是OGNL表达式！
 *  				会写OGNL表达式！ 确定 参数名是什么！
 *  					参数名，参加#{key}中的key！
 *  						特殊： map:  _parameter
 *  							单个参数： _parameter
 *  			 一招鲜确定：  每个参数都标注@Param!
 *  
 *  		了解：
 *  		bind: 
 *  		sql:
 *  		where:  去掉前部多余的and 或者 or
 *          set:    去掉更新语句多余的,
 */
public class CRUDTest {
	
	private SqlSessionFactory sqlSessionFactory ;
	
	@Before
	public void init() throws Exception {
		
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		 sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		
	}
	
	@Test
	public void test() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession(true);

		try {

			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			
			Employee employee = new Employee(null, "Tom", null, null);
			
			List<Employee> list = mapper.getEmployeeById(employee);
			
			System.out.println(list);

		} finally {

			sqlSession.close();
		}
	}

	@Test
	public void testUpdate() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession(true);

		try {

			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			
			Employee employee = new Employee(11, "Rose11", null, null);
			
			mapper.updateEmployee(employee);

		} finally {

			sqlSession.close();
		}
	}
	
	@Test
	public void testChooseTag() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession(true);

		try {

			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			
			Employee employee = new Employee(11, "", "", null);
			
			mapper.getEmployeeBySingleCondition(employee);

		} finally {

			sqlSession.close();
		}
	}
	
	@Test
	public void testForeach() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession(true);

		try {

			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			
			/*ArrayList<Object> list = new ArrayList<>();
			
			list.add(1);
			list.add(2);
			list.add(3);*/
			
			Map<String, Object> map=new HashMap<>();
			
			map.put("1", 5);
			map.put("2", 6);
			
			List<Employee> emps = mapper.getEmployeeByCondition(map);
			
			System.out.println(emps);

		} finally {

			sqlSession.close();
		}
	}
	
	@Test
	public void testBatchInsert() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession(true);

		try {

			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			
			List<Employee> emps=new ArrayList<>();
			
			Employee employee1 = new Employee(null, "Tom1", "Tom1", null);
			Employee employee2 = new Employee(null, "Tom1", "Tom1", null);
			Employee employee3= new Employee(null, "Tom1", "Tom1", null);
			
			emps.add(employee1);
			emps.add(employee2);
			emps.add(employee3);
			
			mapper.batchInsert(emps);

		} finally {

			sqlSession.close();
		}
	}
	
	@Test
	public void testBind() throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession(true);

		try {

			EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
			
			List<Employee> emps = mapper.getEmployeeByEmail("qq");
			
			System.out.println(emps);

		} finally {

			sqlSession.close();
		}
	}
	
	
}
