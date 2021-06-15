package nnnocturn.db.repository.impl;

import org.apache.log4j.Logger;
import nnnocturn.db.DBManager;
import nnnocturn.db.dao.UserDAORepository;
import nnnocturn.db.dto.UserDTO;
import nnnocturn.db.entity.User;
import nnnocturn.db.repository.UserRepository;
import nnnocturn.exception.DBException;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);

    private final UserDAORepository userDAORepository;

    private final DBManager dbManager;

    public UserRepositoryImpl(UserDAORepository userDAORepository, DBManager dbManager) {
        this.userDAORepository = userDAORepository;
        this.dbManager = dbManager;
    }

    /**
     * Get user from db
     * @param user which need to get
     * @return user object
     */
    @Override
    public User getUser(User user) {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("User was get" + user);
                return userDAORepository.getUser(user);
            } catch (DBException e) {
                LOGGER.error("Cannot get user " + e);
            }
            return null;
        });
    }

    /**
     * Create new user
     * @param user object which need create
     */
    @Override
    public void createUser(User user) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Create user: " + user);
                return userDAORepository.insertUser(user);
            } catch (DBException e) {
                LOGGER.error("Cannot create new user " + e);
            }
            return false;
        });
    }

    /**
     * Update user
     *
     * @param user object which need update
     */
    @Override
    public void updateUser(User user) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Update user: " + user);
                return userDAORepository.updateUser(user);
            } catch (DBException e) {
                LOGGER.error("Cannot update user " + e);
            }
            return false;
        });
    }

    /**
     * Update user status
     *
     * @param user object which need update status
     */
    @Override
    public void updateUserStatus(User user) {
        dbManager.doTransaction(() -> {
            try {
                LOGGER.info("Update user status: " + user);
                return userDAORepository.updateUserStatus(user);
            } catch (DBException e) {
                LOGGER.error("Cannot update user status " + e);
            }
            return false;
        });
    }

    /**
     * Get list user DTO
     *
     * @return list user DTO
     */
    @Override
    public List<UserDTO> getUserDTO() {
        return dbManager.doTransaction(() -> {
            try {
                LOGGER.info("List with user DTO was get");
                return userDAORepository.getUserDTO();
            } catch (DBException e) {
                LOGGER.error("Cannot get list user " + e);
            }
            return null;
        });
    }
}
