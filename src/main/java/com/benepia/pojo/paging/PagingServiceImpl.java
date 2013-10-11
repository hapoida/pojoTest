package com.benepia.pojo.paging;

import org.springframework.stereotype.Component;

import com.benepia.common.to.PagingFront;
import com.benepia.common.to.PagingTO;

@Component
public class PagingServiceImpl implements PagingService{

	public PagingFront getPagingFront(int pages, int countPerPage,
			int totalCount) throws Exception {
		return this.getPagingFront(pages, countPerPage, totalCount, false);
	}

	private PagingFront getPagingFront(int pages, int countPerPage,
			int totalCount, boolean isPopup) throws Exception {
		PagingTO pagingTO = new PagingTO();
		pagingTO.setPages(pages);
		pagingTO.setRowCountPerPage(countPerPage);
		pagingTO.setTotalRowCount(totalCount);
		pagingTO.init();

		PagingFront pagingFront = new PagingFront(pagingTO);
		
		if (isPopup) {			
			pagingFront.pagingProcess4Popup();
		} else {
			pagingFront.pagingProcess4Admin();
		}
		return pagingFront;
	}

}
