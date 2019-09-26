//package com.demo.service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.task.Task;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional
//public class ActivitiService {
//	// 注入為我們自動配置好的服務
//	@Autowired
//	private RuntimeService runtimeService;
//	@Autowired
//	private TaskService taskService;
//
//	// 開始流程，傳入申請者的id以及公司的id
//	public void startProcess(Long personId, Long compId) {
//		Map<String, Object> variables = new HashMap<String, Object>();
//		variables.put("personId", personId);
//		variables.put("compId", compId);
//		runtimeService.startProcessInstanceByKey("joinProcess", variables);
//	}
//
//	// 獲得某個人的任務別表
//	public List<Task> getTasks(String assignee) {
//		return taskService.createTaskQuery().taskCandidateUser(assignee).list();
//	}
//
//	// 完成任務
//	public void completeTasks(Boolean joinApproved, String taskId) {
//		Map<String, Object> taskVariables = new HashMap<String, Object>();
//		taskVariables.put("joinApproved", joinApproved);
//		taskService.complete(taskId, taskVariables);
//	}
//}
