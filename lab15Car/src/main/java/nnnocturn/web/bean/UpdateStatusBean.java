package nnnocturn.web.bean;

import nnnocturn.db.UserStatus;

import java.util.Objects;

public class UpdateStatusBean {

    private long id;

    private UserStatus userStatus;

    public UserStatus getStatus() {
        return userStatus;
    }

    public void setStatus(long idStatus) {
        this.userStatus = UserStatus.getStatus(idStatus);
    }

    public long getId() {
        return id;
    }

    public void setId(String id) {
        this.id = (Objects.isNull(id) || id.isEmpty()) ? 0 : Long.parseLong(id);
    }
}
