package code.flatura.teamlunch.to;

import code.flatura.teamlunch.model.User;

public class UserTo {
    private final User user;

    public UserTo(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
