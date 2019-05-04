package by.htp.basumatarau.jd2TestSystem.dto;

import by.htp.basumatarau.jd2TestSystem.validator.ValidEmail;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

public class AdminFormDto {

    @NotNull
    private Integer id;

    @NotNull
    @Size(max = 60)
    private String firstName;

    @NotNull
    @Size(max = 60)
    private String lastName;

    @NotNull
    @ValidEmail
    private String email;

    @NotNull
    @Size(min = 1)
    public List<@Valid RoleDto> roles = new LinkedList<>();

    public AdminFormDto(){}

    public static class RoleDto{
        @Nullable
        @Size(max = 45)
        private String roleName;

        public RoleDto(){}

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
