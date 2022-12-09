package org.yzr.service;

import org.springframework.stereotype.Service;
import org.yzr.dao.AppDao;
import org.yzr.dao.UserDao;
import org.yzr.model.App;
import org.yzr.model.User;

import javax.annotation.Resource;
import javax.transaction.Transactional;


@Service
public class UserService {
      @Resource
      private UserDao userDao;

      @Transactional
      public User login(String name, String pwd) {
            User user = this.userDao.login(name, pwd);
            return user;
      }
}
