package by.htp.basumatarau.jd2TestSystem.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class NewAssignmentDto {

    @NotNull
    private List<@NotNull Integer> assigneeIds;

    @NotNull
    private Integer assignedTestId;

    @NotNull
    @Size(min = 1, max = 200)
    private String details;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    public List<Integer> getAssigneeIds() {
        return assigneeIds;
    }

    public void setAssigneeIds(List<Integer> assigneeIds) {
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
