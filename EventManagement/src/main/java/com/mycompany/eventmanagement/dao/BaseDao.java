package com.mycompany.eventmanagement.dao;

import java.io.IOException;

import com.mycompany.eventmanagement.model.BasePO;

public interface BaseDao {

	public void insert(BasePO obj) throws IOException;
	public void delete(BasePO obj) throws IOException;
	public void update(BasePO obj) throws IOException;
}
