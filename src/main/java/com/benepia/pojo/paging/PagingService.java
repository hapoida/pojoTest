package com.benepia.pojo.paging;

import com.benepia.common.to.PagingFront;
import com.benepia.common.to.PagingTO;

public interface PagingService {
	public PagingFront getPagingFront(int pages, int countPerPage, int totalCount) throws Exception ;	
}
