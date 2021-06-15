package nnnocturn.web.service.impl;

import nnnocturn.db.Role;
import nnnocturn.db.UserStatus;
import nnnocturn.db.dto.UserDTO;
import nnnocturn.db.entity.User;
import nnnocturn.db.repository.UserRepository;
import nnnocturn.web.bean.AuthBean;
import nnnocturn.web.bean.RegistrationBean;
import nnnocturn.web.bean.SettingBean;
import nnnocturn.web.bean.UpdateStatusBean;
import nnnocturn.web.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository USER_REPOSITORY;

    public UserServiceImpl(UserRepository USER_REPOSITORY) {
        this.USER_REPOSITORY = USER_REPOSITORY;
    }

    /**
     * Update user status
     *
     * @param updateStatusBean to be update status
     */
    @Override
    public void updateUserStatus(UpdateStatusBean updateStatusBean) {
        USER_REPOSITORY.updateUserStatus(getEntity(updateStatusBean));
    }

    /**
     * Update user info
     *
     * @param settingBean to be update in user
     */
    @Override
    public void updateUserInfo(SettingBean settingBean) {
        USER_REPOSITORY.updateUser(getEntity(settingBean));
    }

    /**
     * Create new client
     *
     * @param registrationBean to be new client
     */
    @Override
    public void createClient(RegistrationBean registrationBean) {
        USER_REPOSITORY.createUser(getEntity(registrationBean, Role.CLIENT));
    }

    /**
     * Create new manager
     *
     * @param registrationBean to be new manager
     */
    @Override
    public void createManager(RegistrationBean registrationBean) {
        USER_REPOSITORY.createUser(getEntity(registrationBean, Role.MANAGER));
    }

    /**
     * @return list user DTO
     */
    @Override
    public List<UserDTO> getUserDTO() {
        return USER_REPOSITORY.getUserDTO();
    }

    /**
     * Search user
     *
     * @param authBean param user
     * @return user
     */
    @Override
    public User findUser(AuthBean authBean) {
        return USER_REPOSITORY.getUser(getEntity(authBean));
    }

    /**
     * Search user
     *
     * @param registrationBean param user
     * @return user
     */
    @Override
    public User findUser(RegistrationBean registrationBean) {
        return USER_REPOSITORY.getUser(getEntity(registrationBean));
    }

    /**
     * Search user
     *
     * @param user object
     * @return user
     */
    @Override
    public User findUser(User user) {
        return USER_REPOSITORY.getUser(user);
    }

    /**
     * Get entity user
     *
     * @param registrationBean param
     * @return user entity
     */
    private User getEntity(RegistrationBean registrationBean) {
        User user = new User();
        user.setLogin(registrationBean.getLogin());
        user.setFirstName(registrationBean.getFirstName());
        user.setLastName(registrationBean.getLastName());
        user.setAge(registrationBean.getAge());
        return user;
    }

    /**
     * Get entity user
     *
     * @param updateStatusBean user status
     * @return user entity
     */
    private User getEntity(UpdateStatusBean updateStatusBean) {
        User user = new User();
        user.setId(updateStatusBean.getId());
        user.setIdStatus(updateStatusBean.getStatus().getNumber());
        return user;
    }

    /**
     * Get entity user
     *
     * @param settingBean param
     * @return user entity
     */
    private User getEntity(SettingBean settingBean) {
        User user = new User();
        user.setId(settingBean.getId());
        user.setAge(settingBean.getAge());
        user.setFirstName(settingBean.getFirstName());
        user.setLastName(settingBean.getLastName());
        return user;
    }

    /**
     * Get entity user
     *
     * @param registrationBean param
     * @param role user
     * @return user entity
     */
    private User getEntity(RegistrationBean registrationBean, Role role) {
        User user = new User();
        user.setLogin(registrationBean.getLogin());
        user.setPassword(registrationBean.getPassword());
        user.setFirstName(registrationBean.getFirstName());
        user.setLastName(registrationBean.getLastName());
        user.setAge(registrationBean.getAge());
        user.setIdStatus(UserStatus.UNBUNNED.getNumber());
        user.setIdRole(role.getNumber());
        return user;
    }

    private User getEntity(AuthBean authBean) {
        User user = new User();
        user.setLogin(authBean.getLogin());
        return user;
    }
}
