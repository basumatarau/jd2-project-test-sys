package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "email")
    private String email;

    @Column(name = "passwordHash")
    private String passwordHash;

    @Column(name = "isEnabled")
    private boolean isEnabled;

    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(
            name = "users_has_roles",
            joinColumns = {@JoinColumn(name = "users_iduser")},
            inverseJoinColumns = {@JoinColumn(name = "roles_idrole")}
    )
    private Set<Role> roles;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_has_subscribers",
            joinColumns = {@JoinColumn(name = "users_iduser")},
            inverseJoinColumns = {@JoinColumn(name = "users_idsubscriber")}
    )
    private Set<User> followers;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_has_subscribers",
            inverseJoinColumns = {@JoinColumn(name = "users_iduser")},
            joinColumns = {@JoinColumn(name = "users_idsubscriber")}
    )
    private Set<User> followedUsers;

    @OneToMany(mappedBy = "assignee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Assignment> assignmentSet;

    @OneToMany(mappedBy = "author",
            cascade = CascadeType.ALL)
    private Set<Test> authoredTests;

    @OneToMany(mappedBy = "author")
    private Set<Question> authoredQuestions;

    public Set<Assignment> getAssignmentSet() {
        return assignmentSet;
    }

    public void setAssignmentSet(Set<Assignment> assignmentSet) {
        this.assignmentSet = assignmentSet;
    }

    public Set<Test> getAuthoredTests() {
        return authoredTests;
    }

    public void setAuthoredTests(Set<Test> authoredTests) {
        this.authoredTests = authoredTests;
    }

    public Set<Question> getAuthoredQuestions() {
        return authoredQuestions;
    }

    public void setAuthoredQuestions(Set<Question> authoredQuestions) {
        this.authoredQuestions = authoredQuestions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowedUsers() {
        return followedUsers;
    }

    public void setFollowedUsers(Set<User> followedUsers) {
        this.followedUsers = followedUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isEnabled == user.isEnabled &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, passwordHash, isEnabled);
    }
}
