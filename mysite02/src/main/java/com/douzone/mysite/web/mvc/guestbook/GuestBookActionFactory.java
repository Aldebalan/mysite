package com.douzone.mysite.web.mvc.guestbook;

import com.douzone.mysite.web.mvc.main.DefaultAction;
import com.douzone.mysite.web.mvc.user.JoinAction;
import com.douzone.mysite.web.mvc.user.JoinFormAction;
import com.douzone.mysite.web.mvc.user.JoinSuccess;
import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("deleteform".equals(actionName)) {
			action = new deleteformAction();
		} else if("add".equals(actionName)) {
			action = new addAction();
		} else if("delete".equals(actionName)) {
			action = new deleteAction();
		} else {
			action = new IndexAction();
		}
		
		return action;
	}

}