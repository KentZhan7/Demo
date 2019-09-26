package com.demo.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.demo.orm.dao.UserInfoDao;
import com.demo.orm.model.UserInfo;
//import com.demo.service.CalendarQuickstart;
//import com.demo.service.TestGC;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;

@RestController
//@CrossOrigin(origins = "http://172.24.66.37:9999") // not work
public class BaseController {
	private static Logger log = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	UserInfoDao userInfoDao;
	
	@RequestMapping(value="/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Object home() {
		log.debug("debug=======");
		log.info("info=======");
		List<UserInfo> list = userInfoDao.getUserInfoByUserInfoModel(null);
		log.info("" + list);
		return list;
	}
	
	@GetMapping(value="/jsonp", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, headers="Content-Type=application/json")
	JSONPObject jsonp() {
		log.debug("debug=======");
		log.info("info=======");
		Map<String, String> map = new Hashtable<String, String>();
		map.put("aaa", "Hello~~~");
		JSONPObject j = new JSONPObject("param", map);
		return j;
	}
	
	@GetMapping(value="/main", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, headers="Content-Type=application/json")
	ModelAndView mainView() {
		log.debug("debug=======");
		log.info("info=======");
		ModelAndView mv = new ModelAndView("pages/main");
		return mv;
	}
	
//	@Resource
//	CalendarQuickstart cq;
//	
//	@Resource
//	TestGC testGC;
//	
//	@RequestMapping("/getGCInfo")
//	@ResponseBody
//	public String getGCInfo() throws IOException, GeneralSecurityException {
//		return testGC.oauth2Callback("123");
//	}
//	
//	@RequestMapping("/login/google")
//	public RedirectView loginGoogle() throws Exception {
//		return new RedirectView(testGC.authorize());
//	}
}
