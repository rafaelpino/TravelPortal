package com.crossover.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;

import com.crossover.entity.TicketVO;

@Transactional
@Qualifier("ticketsDao")
public interface TicketDao extends CrudRepository<TicketVO, Long> {
}
