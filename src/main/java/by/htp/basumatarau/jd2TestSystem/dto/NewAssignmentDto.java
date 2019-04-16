package by.htp.basumatarau.jd2TestSystem.dto;

import by.htp.basumatarau.jd2TestSystem.model.Test;
import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.Date;
import java.util.List;

public class NewAssignmentDto {
    private Integer[] assigneeIds;
    private Integer assignedTestId;
    private String details;
    private String dueDate;
    private String name;

    public Integer[] getAssigneeIds() {
        return assigneeIds;
    }

    public void setAssigneeIds(Integer[] assigneeIds) {
        this.assigneeIds = assigneeIds;
    }

    public Integer getAssignedTestId() {
        return assignedTestId;
    }

    public void setAssignedTestId(Integer assignedTestId) {
        this.assignedTestId = assignedTestId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
