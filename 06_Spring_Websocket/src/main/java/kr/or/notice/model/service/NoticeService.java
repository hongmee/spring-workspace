package kr.or.notice.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.notice.model.dao.NoticeDao;
import kr.or.notice.model.vo.Notice;

@Service
public class NoticeService {

	@Autowired
	private NoticeDao dao;

	public ArrayList<Notice> selectNoticeList() {
		return dao.selectNoticeList();
	}

	public int insertNotice(Notice n) {
		return dao.insertNotice(n);
	}

	public Notice selectOneNotice(int noticeNo) {
		return dao.selectOneNoitce(noticeNo);
	}

	public int updateNotice(Notice notice) {
		return dao.updateNotice(notice);
	}

	public int deleteNotice(int noticeNo) {
		return dao.deleteNotice(noticeNo);
	}
}
