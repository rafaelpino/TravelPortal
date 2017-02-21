package com.crossover.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import com.crossover.entity.UserVO;

@Transactional
@Qualifier("usersDao")
public interface UserDao extends CrudRepository<UserVO, Long> {
	public UserVO findByMail(String mail);
}
