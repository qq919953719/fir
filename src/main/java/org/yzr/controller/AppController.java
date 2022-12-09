package org.yzr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.yzr.model.User;
import org.yzr.service.AppService;
import org.yzr.utils.PathManager;
import org.yzr.vo.AppViewModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

      @Resource
      private AppService appService;
      @Resource
      private PathManager pathManager;

      @GetMapping("/apps")
      public String apps(HttpServletRequest request) {

            String userss = (String)request.getSession().getAttribute("userssss");
            System.out.println("AppController apps() 取值:" + userss);


            try {
                  List<AppViewModel> apps = this.appService.findAll();
                  request.setAttribute("apps", apps);
                  request.setAttribute("baseURL", this.pathManager.getBaseURL(false));
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return "login";
      }

      @GetMapping("/index")
      public String apps2(HttpServletRequest request) {
            try {
                  Object tmpObj = request.getSession().getAttribute("userssss");

                  request.getSession().setAttribute("userssss", "sss");
                  String userss = (String)request.getSession().getAttribute("userssss");
                  System.out.println("AppController apps2() 赋值:" + userss);

                  List<AppViewModel> apps = this.appService.findAll();
                  request.setAttribute("apps", apps);
                  request.setAttribute("baseURL", this.pathManager.getBaseURL(false));
            } catch (Exception e) {
                  e.printStackTrace();
            }

            return "index";
      }

      @GetMapping("/apps/{appID}")
      public String getAppById(@PathVariable("appID") String appID, HttpServletRequest request) {
            AppViewModel appViewModel = this.appService.getById(appID);
            request.setAttribute("package", appViewModel);
            request.setAttribute("apps", appViewModel.getPackageList());
            return "list";
      }

      @RequestMapping("/packageList/{appID}")
      @ResponseBody
      public Map<String, Object> getAppPackageList(@PathVariable("appID") String appID) {
            AppViewModel appViewModel = this.appService.getById(appID);
            Map<String, Object> map = new HashMap<>();
            try {
                  map.put("packages", appViewModel.getPackageList());
                  map.put("success", true);
            } catch (Exception e) {
                  map.put("success", false);
            }
            return map;
      }

      @RequestMapping("/app/delete/{id}")
      @ResponseBody
      public Map<String, Object> deleteById(@PathVariable("id") String id, HttpServletRequest request) {
            User user = (User) request.getSession().getAttribute("user");

            Map<String, Object> map = new HashMap<>();
            try {
                  this.appService.deleteById(id);
                  map.put("success", true);
            } catch (Exception e) {
                  map.put("success", false);
            }
            return map;
      }

}
