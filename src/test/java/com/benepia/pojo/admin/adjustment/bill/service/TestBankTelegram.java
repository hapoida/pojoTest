package com.benepia.pojo.admin.adjustment.bill.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.benepia.admin.adjustment.bill.service.BankTelegramIF;
import com.benepia.admin.adjustment.bill.to.BankTelegramSearchTO;
import com.benepia.admin.adjustment.bill.vo.BankTelegramVO;
import com.benepia.common.to.PagingFront;
import com.benepia.pojo.paging.PagingService;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBankTelegram {
	
	@Autowired
	private ApplicationContext context;
	
	PagingFront pagingFront;
	
	BankTelegramIF bankTelegramIF; 
	
	BankTelegramSearchTO bankTelegramSearchTO = null;
	
	PagingService pagingService;
	
	@Test
	public void testService(){
		assertNotNull(bankTelegramIF);
		assertNotNull(context);
	}
	
	@Before
	public void init_bankTelegram_정보초기화(){
		
		String cardCd = "001";
		String billYm= "201102";
		String tlgrmSndYn= "Y";
		int pages = 1;
		int countPerPage = 1;
		int totalCount = 45;
		
		bankTelegramSearchTO = new BankTelegramSearchTO();
		bankTelegramSearchTO.setBillYm(billYm);
		bankTelegramSearchTO.setCardCd(cardCd);
		bankTelegramSearchTO.setTlgrmSndYn(tlgrmSndYn);
		bankTelegramSearchTO.setSessionCurrentPageNo(pages);
		
		bankTelegramIF 	= (BankTelegramIF) context.getBean("bankTelegramIF");
		pagingService 	= (PagingService) context.getBean("pagingService");
		
		try {
			pagingFront = pagingService.getPagingFront(pages, countPerPage, totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test 
	public void getBankTelegramTotalCount() {
		
		int totalCount = 0;
		
		try {
			totalCount = bankTelegramIF.getBankTelegramTotalCount(bankTelegramSearchTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(45, totalCount);
	}
	
	@Test
	public void test_getBankTelegramList(){
		
		List<BankTelegramVO> banktelgramVoList = null;
		
		try {
			banktelgramVoList = bankTelegramIF.getBankTelegramList(bankTelegramSearchTO, pagingFront.getPagingTO());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BankTelegramVO bankTelegramVO = banktelgramVoList.get(0);
		
		assertEquals(1, banktelgramVoList.size());
		assertNotNull(bankTelegramVO);
		
		assertEquals("발송"			, bankTelegramVO.getTlgrmSndYn());
		assertEquals("농협복지카드"	, bankTelegramVO.getCardNm());
		assertEquals("기상청"		, bankTelegramVO.getCustCoNm());
		assertEquals("2011-03-10"	, bankTelegramVO.getTlgrmSndDy());
		assertEquals(1465			, bankTelegramVO.getCardBillCnt());
		assertEquals(new 
				BigDecimal(70375058), bankTelegramVO.getCardBillAmt());
		assertEquals(new 
				BigDecimal(0)		, bankTelegramVO.getOilDscntAmt());
		
	}
	
	
}
