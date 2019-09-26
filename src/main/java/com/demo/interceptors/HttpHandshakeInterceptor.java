package com.demo.interceptors;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpHandshakeInterceptor implements HandshakeInterceptor {
	private static Logger log = LoggerFactory.getLogger(HttpHandshakeInterceptor.class);
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession();
			attributes.put("sessionId", session.getId());
			Map<String, String[]> map = servletRequest.getServletRequest().getParameterMap();
			
			log.info("************" + objectMapper.writeValueAsString(map));
			log.info("************sessionId " + session.getId());
		}
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
	}
}
