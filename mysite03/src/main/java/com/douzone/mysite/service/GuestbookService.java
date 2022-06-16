package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookRepository guestBookRepository;
	
	public List<GuestbookVo> getMessage(){
		return guestBookRepository.findAll();
	}
	
	public boolean deleteMessage(Long no, String password) {
		return guestBookRepository.delete(no, password);
	}
	
	public boolean addMessage(GuestbookVo vo) {
		return guestBookRepository.insert(vo);
	}
		
}