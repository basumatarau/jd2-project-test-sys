package by.htp.basumatarau.jd2TestSystem.dto;

import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.Set;

public class UserAndFollowedUsers {
    private final User user;
    private final Set<User> followedUsers;

    UserAndFollowedUsers(User user, Set<User> followedUsers){
        this.user = user;
        this.followedUsers = followedUsers;
    }

    public User getUser() {
        return user;
    }

    public Set<User> getFollowedUsers() {
        return followedUsers;
    }
}
