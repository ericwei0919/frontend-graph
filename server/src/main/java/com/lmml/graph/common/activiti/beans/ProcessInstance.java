package com.lmml.graph.common.activiti.beans;

import lombok.Data;

@Data
public class ProcessInstance {
    Long actBusinessId;
    Long applicantId;
    Long applicantName;
    String applicantTime;
    String lastProcessingTime;
    String previousApproverId;
    String previousApproverName;
    String previousApprovertatus;
}
