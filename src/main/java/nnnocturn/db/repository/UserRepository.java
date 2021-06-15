package nnnocturn.db.repository;

import nnnocturn.db.dto.UserDTO;
import nnnocturn.db.entity.User;

import java.util.List;

public interface UserRepository {

    User getUser(User user);

    void createUser(User user);

    void updateUser(User user);

    void updateUserStatus(User user);

    List<UserDTO> getUserDTO();

}
