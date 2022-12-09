package org.yzr.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.yzr.model.User;


public interface UserDao extends CrudRepository<User, String> {

      @Query("select a from User a where a.userName=:userName and a.password=:password")
      User login(@Param("userName") String userName, @Param("password") String password);


      public User findFirstByUserNameAndPassword(String name, String password);


}