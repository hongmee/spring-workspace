package kr.or.student.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
}
