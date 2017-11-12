package com.lmml.graph.domain.activiti;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "activiti_task_template")
public class ActivitiTaskTemplate {

    private Long taskTemplateId;
    private String templateName;
    private Timestamp createTimestamp;
    private String content;

    @Id
    @Column(name = "task_template_id", nullable = false)
    public Long getTaskTemplateId() {
        return taskTemplateId;
    }

    public void setTaskTemplateId(Long taskTemplateId) {
        this.taskTemplateId = taskTemplateId;
    }

    @Basic
    @Column(name = "create_timestamp")
    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }
    @Basic
    @Column(name = "content", nullable = false, length = 21000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "template_name", nullable = false, length = 255)
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
