package com.douzone.mysite.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.douzone.mysite.vo.GuestBookVo;

@Service
public class GuestbookService {

	public List<GuestBookVo> getMessageList() {
		return null;
	}

	public Boolean deleteMessage(Long no, String password) {
		return false;
	}

	public Boolean addMessage(GuestBookVo vo) {
		return false;
	}
}
