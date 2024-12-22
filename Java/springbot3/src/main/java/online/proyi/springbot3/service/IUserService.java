package online.proyi.springbot3.service;

import online.proyi.springbot3.entity.User;
import online.proyi.springbot3.entity.query.UserQueryBean;

import java.util.List;

public interface IUserService {

    List<User> findList(UserQueryBean userQueryBean);

    User findById(Long id);

    int deleteById(Long id);

    int deleteByIds(Long[] ids);

    int update(User user);

    int save(User user);

    int updatePassword(User user);

    User findById2(Long userId);
}
