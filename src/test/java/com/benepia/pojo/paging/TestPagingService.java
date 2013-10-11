package com.benepia.pojo.paging;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPagingService {
	
	@Autowired
	PagingService pagingService;
	
	@Test
	public void test_pagingService(){
		assertNotNull(pagingService);
	}

}
