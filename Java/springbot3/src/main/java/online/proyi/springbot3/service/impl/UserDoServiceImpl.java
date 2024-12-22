package online.proyi.springbot3.service.impl;


import online.proyi.springbot3.dao.IUserDao;
import online.proyi.springbot3.entity.User;
import online.proyi.springbot3.entity.query.UserQueryBean;
import online.proyi.springbot3.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDoServiceImpl implements IUserService {

    /**
     * userDao.
     */
    private final IUserDao userDao;

    /**
     * init.
     *
     * @param userDao2 user dao
     */
    public UserDoServiceImpl(final IUserDao userDao2) {
        this.userDao = userDao2;
    }

    @Override
    public List<User> findList(UserQueryBean userQueryBean) {
        return userDao.findList(userQueryBean);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
        public int deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return userDao.deleteByIds(ids);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }

    @Override
    public int save(User user) {
        int result = userDao.save(user);
        return result;
    }

    @Override
    public int updatePassword(User user) {
        return userDao.updatePassword(user);
    }

    @Override
    public User findById2(Long userId) {
        return userDao.findById2(userId);
    }
}
