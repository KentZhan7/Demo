package com.demo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class Interceptor1 implements HandlerInterceptor {
	private static Logger log = LoggerFactory.getLogger(Interceptor1.class);
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
		log.info("preHandle");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object o, ModelAndView mv)
			throws Exception {
		log.info("postHandle");
//		mv.setViewName("redirect:/index.html");
	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object o, Exception e)
			throws Exception {
		log.info("afterCompletion");
	}
}
