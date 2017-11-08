package com.lmml.graph.common.activiti.impl;

import com.lmml.graph.common.activiti.ProcessWorkFlowService;
import com.lmml.graph.common.activiti.beans.BpmTaskCommand;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcessWorkFlowServiceImpl implements ProcessWorkFlowService {

	public static final String KEY_TASK_OBJ_UID = "taskObjectUid";
	public static final String KEY_PROCESS_TYPE = "processType";

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	@Autowired
	HistoryService historyService;

	final static Logger logger = Logger.getLogger(ProcessWorkFlowServiceImpl.class);

	@Override
	public String start(String processDefinitionKey, Map<String, Object> variableMap) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, variableMap);
		return processInstance.getId();
	}

	@Override
	public boolean completeTask(String userId, String hostObjId, BpmTaskCommand command,Map<String, Object> variableMap) {
		Map<String, Object> varFilters = new HashMap<String, Object>();
		varFilters.put(KEY_TASK_OBJ_UID, hostObjId);
		List<Task> list = taskService.createTaskQuery()
				.taskAssignee(userId)
				.orderByTaskCreateTime().asc()
				.list();
		try {
			taskService.complete(list.get(0).getId(), variableMap);
		} catch(Exception ex) {
			logger.error("任务["+userId+"]没有完成成功, 原因是:" + ex.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean assignTask(String userId, String hostObjId, BpmTaskCommand command) {
		try {
			taskService.claim(userId, userId);
		} catch(Exception ex) {
			logger.error("任务["+userId+"]没有分配成功, 原因是:" + ex.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean assignAndCompleteTask(String userId, String hostObjId, BpmTaskCommand command) {
		return false;
	}

	@Override
	public boolean processTaskThenDesignatedHandler(String userId, String hostObjId, List<Long> groupIds, BpmTaskCommand command) {
		return false;
	}

	@Override
	public boolean processTaskThenDesignatedProcessingDepartment(String userId, String hostObjId, List<Long> groupIds, BpmTaskCommand command) {
		return false;
	}

	@Override
	public boolean terminateTheProcess(String hostObjId) {
		return false;
	}

	@Override
	public boolean endTheProcess(String hostObjId) {
		return false;
	}

	@Override
	public boolean deleteTheProcess(String hostObjId) {
		return false;
	}
}
