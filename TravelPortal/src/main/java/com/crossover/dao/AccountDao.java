package com.crossover.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import com.crossover.entity.AccountVO;

@Transactional
@Qualifier("accountsDao")
public interface AccountDao extends CrudRepository<AccountVO, Long> {
}
