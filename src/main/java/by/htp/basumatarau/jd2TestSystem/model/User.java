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

    @OneToMany(mappedBy = "user")
    private Set<Authority> authoritySet;

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

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_has_tests",
            joinColumns = {@JoinColumn(name = "users_iduser")},
            inverseJoinColumns = {@JoinColumn(name = "tests_idtest")}
    )
    private Set<Test> managedTests;

    @OneToMany(mappedBy = "assignee")
    private Set<Assignment> assignmentSet;

    @OneToMany(mappedBy = "author")
    private Set<Test> authoredTests;

    @OneToMany(mappedBy = "author")
    private Set<Question> authoredQuestions;

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

    public Set<Authority> getAuthoritySet() {
        return authoritySet;
    }

    public void setAuthoritySet(Set<Authority> authoritySet) {
        this.authoritySet = authoritySet;
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
        return id == user.id &&
                isEnabled == user.isEnabled &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, passwordHash, isEnabled);
    }
}
