package org.yzr.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_user1")
public class User {
      @Id
      @GeneratedValue(generator = "system-uuid")
      @GenericGenerator(name = "system-uuid", strategy = "uuid")
      @Column(length = 32)
      private String userId;
      @Column
      private String userName;

      public String getPassword() {
            return password;
      }

      public void setPassword(String password) {
            this.password = password;
      }

      @Column
      private String password;
      public String getUserId() {
            return userId;
      }

      public void setUserId(String userId) {
            this.userId = userId;
      }

      public Date getLastVisit() {
            return lastVisit;
      }

      public void setLastVisit(Date lastVisit) {
            this.lastVisit = lastVisit;
      }
      @Column
      private Integer credits;
      @Column
      private String lastIp;
      @Column
      private Date lastVisit;

      public String getLastIp() {
            return lastIp;
      }

      public void setLastIp(String lastIp) {
            this.lastIp = lastIp;
      }




      public String getUserName() {
            return userName;
      }

      public void setUserName(String userName) {
            this.userName = userName;
      }

      public Integer getCredits() {
            return credits;
      }

      public void setCredits(Integer credits) {
            this.credits = credits;
      }
}

