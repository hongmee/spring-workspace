package kr.or.dm.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.dm.model.dao.DmDao;
import kr.or.dm.model.vo.DirectMessage;

@Service
public class DmService {

	@Autowired
	private DmDao dao;

	@Transactional
	public int insertDm(DirectMessage dm) {
		return dao.insertDm(dm);
	}

	public ArrayList<DirectMessage> selectDmList(DirectMessage dm) {
		return dao.selectDmList(dm);
	}

	@Transactional
	public DirectMessage selectOneDm(int dmNo) {
		
		DirectMessage dm = dao.selectOneDm(dmNo);
		
		// 읽지않았을때만 읽음 처리
		if(dm.getReadCheck() == 0) {
			dao.updateReadCheck(dmNo);			
		}
		
		return dm;
	}

	public int selectDmCount(String memberId) {
		return dao.selectDmCount(memberId);
	}
}
