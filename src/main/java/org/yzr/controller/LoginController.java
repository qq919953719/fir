package org.yzr.controller;


import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;
import org.yzr.dao.UserDao;
import org.yzr.model.User;
import org.yzr.service.UserService;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {
      @Resource
      private UserService userService;
      private final UserDao userDao;

      public LoginController(UserDao userDao) {
            this.userDao = userDao;
      }

      @PostMapping("/user/login/{username}/{password}")
      @ResponseBody
      public Map<String, Object> loginByPwd(@PathVariable("username") String userName, @PathVariable("password") String password, HttpServletRequest request) {
            User user = null;
            Map<String, Object> map = new HashMap<>();
            try {

                  user = this.userService.login(userName, password);
                  if (user != null) {
                        request.getSession().setAttribute("userssss", user);
                        Object tmpObj = request.getSession().getAttribute("userssss");

                        request.getSession().setAttribute("userName", user.getUserName());
                        map.put("success", true);
                        map.put("roleType", user.getCredits());
                        return map;
                  } else {
                        System.out.println("return null");
                        map.put("success", false);
                  }
            } catch (Exception e) {
                  map.put("success", false);
            }

            return map;
      }

}
