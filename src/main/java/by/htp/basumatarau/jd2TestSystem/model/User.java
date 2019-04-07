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

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

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
    private Set<User> subscribers;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_has_subscribers",
            inverseJoinColumns = {@JoinColumn(name = "users_iduser")},
            joinColumns = {@JoinColumn(name = "users_idsubscriber")}
    )
    private Set<User> assigners;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isEnabled == user.isEnabled &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(nickName, user.nickName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(salt, user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, nickName, email, password, salt, isEnabled);
    }
}
