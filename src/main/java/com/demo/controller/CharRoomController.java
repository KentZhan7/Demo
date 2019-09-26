package com.demo.controller;


import java.security.Principal;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.demo.model.ServerResponseModel;
import com.demo.model.UserMessage;

@Controller
public class CharRoomController {
	private static Logger log = LoggerFactory.getLogger(CharRoomController.class);
	@Autowired
	public SimpMessagingTemplate template;
	
	@MessageMapping("/messageControl")
	@SendTo("/topic/getResponse")
	public ServerResponseModel said(UserMessage um) throws InterruptedException {
		return new ServerResponseModel("公告, " + um.getMessage());
	}

	@MessageMapping("/message")
//	@SendToUser("/message")
	public UserMessage userMessage(UserMessage userMessage, SimpMessageHeaderAccessor ha) throws Exception {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss");
		userMessage.setCreateDate(LocalDateTime.now().toString(fmt));
		template.convertAndSendToUser(userMessage.getChannel(), "/message", userMessage);
//		log.info("***********:" + principal.getName());
		return userMessage;
	}
}
