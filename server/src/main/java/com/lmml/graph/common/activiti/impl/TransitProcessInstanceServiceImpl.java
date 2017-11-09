package com.lmml.graph.common.activiti.impl;

import com.lmml.graph.common.activiti.InquireWorkFlowService;
import com.lmml.graph.common.activiti.ProcessWorkFlowService;
import com.lmml.graph.common.activiti.TransitProcessInstanceService;
import com.lmml.graph.common.activiti.beans.BpmTaskCommand;
import com.lmml.graph.common.activiti.beans.PagingTask;
import com.lmml.graph.common.activiti.beans.ProcessInstance;
import com.lmml.graph.common.util.WorkflowConst;
import com.lmml.graph.domain.authority.RbacGroupUser;
import com.lmml.graph.service.authority.RbacGroupUserService;
import org.activiti.engine.task.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransitProcessInstanceServiceImpl implements TransitProcessInstanceService {

    @Autowired
    private InquireWorkFlowService inquireWorkFlowService;

    @Autowired
    private ProcessWorkFlowService processWorkFlowService;

    @Autowired
    private RbacGroupUserService rbacGroupUserService;

    @Override
    public ProcessInstance getProcessInstance(Long actBusinessId) {
        List<TaskInfo> taskInfos = inquireWorkFlowService.getProcessInstance(actBusinessId);
        if (null != taskInfos) {
            TaskInfo taskInfo = taskInfos.get(0);
            return this.convertProcessInstance(taskInfo);
        }
        return null;
    }

    @Override
    public PagingTask<ProcessInstance> getToDoTasks(Long userId, int index, int pageSize) {
        List<TaskInfo> toDoTasks = this.getToDoTasks(userId);
        return this.getPagingTask(toDoTasks, index, pageSize);
    }

    @Override
    public PagingTask<ProcessInstance> getInProgressTasks(Long userId, int index, int pageSize) {
        List<TaskInfo> unCompletedTasks = inquireWorkFlowService.getCompletedTasks(userId, null, false);
        List<TaskInfo> toDoTasks = this.getToDoTasks(userId);
        Set<String> filters = new HashSet<>();
        for (TaskInfo item : toDoTasks) {
            filters.add(item.getId());
        }
        List<TaskInfo> inProgressTasks = new ArrayList<>();
        for (TaskInfo item : unCompletedTasks) {
            if (!filters.contains(item.getId())) {
                filters.add(item.getId());
                inProgressTasks.add(item);
            }
        }
        return this.getPagingTask(inProgressTasks, index, pageSize);
    }

    @Override
    public PagingTask<ProcessInstance> getCompletedTasks(Long userId, int index, int pageSize) {
        List<TaskInfo> completedTasks = inquireWorkFlowService.getCompletedTasks(userId, null, true);
        return this.getPagingTask(completedTasks, index, pageSize);
    }

    @Override
    public PagingTask<ProcessInstance> getClaimTasks(Long userId, int index, int pageSize) {
        return null;
    }

    @Override
    public boolean assignTask(Long userId, Long hostObjId, BpmTaskCommand command) {
        if (processWorkFlowService.assignTask(userId, hostObjId, command)) {
            //todo 执行业务代码
        }
        return false;
    }

    @Override
    public boolean completeTask(Long userId, Long hostObjId, BpmTaskCommand command, Map<String, Object> variableMap) {
        if (processWorkFlowService.completeTask(userId, hostObjId, command, variableMap)) {
            //todo 执行业务代码
        }
        return false;
    }

    @Override
    public boolean assignAndCompleteTask(Long userId, Long hostObjId, BpmTaskCommand command) {
        if (processWorkFlowService.assignAndCompleteTask(userId, hostObjId, command)) {
            //todo 执行业务代码
        }
        return false;
    }

    List<TaskInfo> getToDoTasks(Long userId) {
        List<RbacGroupUser> groupUsers = rbacGroupUserService.findGroupUsersByUserId(userId);
        List<Long> groups = new ArrayList<>();
        groupUsers.forEach(rbacGroupUser -> {
            groups.add(rbacGroupUser.getGroup().getGroupId());
        });
        List<TaskInfo> candidateGroupTasks = inquireWorkFlowService.getTaskByCandidateGroup(groups, null, null);
        List<TaskInfo> assigneeTasks = inquireWorkFlowService.getTaskByAssignee(userId, null, null);
        List<TaskInfo> candidateUserTasks = inquireWorkFlowService.getTaskByCandidateUser(userId, null, null);
        candidateGroupTasks.removeAll(assigneeTasks);
        candidateGroupTasks.removeAll(candidateUserTasks);
        candidateGroupTasks.addAll(assigneeTasks);
        candidateGroupTasks.addAll(candidateUserTasks);
        return candidateGroupTasks;
    }

    PagingTask<ProcessInstance> getPagingTask(List<TaskInfo> tasks, int index, int pageSize) {
        PagingTask<ProcessInstance> pagingTask = new PagingTask<>();
        pagingTask.setIndex(index);
        pagingTask.setTotal(tasks.size());
        List<TaskInfo> taskInfos = this.pagingTasks(tasks, index, pageSize);
        List<ProcessInstance> collect = taskInfos
                .stream()
                .map(this::convertProcessInstance)
                .collect(Collectors.toList());
        pagingTask.setData(collect);
        return pagingTask;
    }

    List<TaskInfo> pagingTasks(List<TaskInfo> tasks, int index, int pageSize) {
        if (null == tasks) {
            return null;
        } else {
            tasks = this.tasksSortByCreateTime(tasks);
            int size = tasks.size();
            if (size < index) {
                return null;
            }
            index = index < 1 ? 0 : index;
            pageSize = pageSize < 1 ? 1 : pageSize;
            int subEnd = size > index + pageSize ? index + pageSize : size;
            return tasks.subList(index, subEnd);
        }
    }

    List<TaskInfo> tasksSortByCreateTime(List<TaskInfo> tasks) {
        Collections.sort(tasks, new Comparator<TaskInfo>() {
            @Override
            public int compare(
                    TaskInfo taska,
                    TaskInfo taskb) {
                if (taska.getCreateTime().before(taskb.getCreateTime())) {
                    return -1;
                } else if (taska.getCreateTime().after(taskb.getCreateTime())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return tasks;
    }

    ProcessInstance convertProcessInstance(TaskInfo taskInfo) {
        ProcessInstance processInstance = new ProcessInstance();
        Map<String, Object> processVariables = taskInfo.getProcessVariables();
        Long actBusinessId = (Long) processVariables.get(WorkflowConst.KEY_ACT_THREA_CODE);
        processInstance.setActBusinessId(actBusinessId);
        return processInstance;
    }

}
