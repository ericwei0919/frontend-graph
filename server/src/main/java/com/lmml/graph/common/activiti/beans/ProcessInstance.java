package com.lmml.graph.common.activiti.beans;

import lombok.Data;

@Data
public class ProcessInstance {
    private Long actBusinessId;
    private Long applicantId;
    private String applicantName;
    private Long applicantType;
    private String applicantTypeName;
    private String applicantTime;
    private String lastProcessingTime;
    private String previousApproverId;
    private String previousApproverName;
    private String previousApprovertatus;
}
