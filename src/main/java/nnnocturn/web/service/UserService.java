package nnnocturn.web.service;

import nnnocturn.db.dto.UserDTO;
import nnnocturn.db.entity.User;
import nnnocturn.web.bean.AuthBean;
import nnnocturn.web.bean.RegistrationBean;
import nnnocturn.web.bean.SettingBean;
import nnnocturn.web.bean.UpdateStatusBean;

import java.util.List;

public interface UserService {

    void updateUserStatus(UpdateStatusBean updateStatusBean);

    void updateUserInfo(SettingBean settingBean);

    void createClient(RegistrationBean registrationBean);

    void createManager(RegistrationBean registrationBean);

    List<UserDTO> getUserDTO();

    User findUser(AuthBean authBean);

    User findUser(RegistrationBean registrationBean);

    User findUser(User user);
}
