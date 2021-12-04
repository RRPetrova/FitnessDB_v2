package com.example.fitnessdb.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "stats")
public class StatEntity extends BaseEntity{
   private String ip ;
   private String url ;
   private String page ;
   private String refererPage ;
   private String userAgent;
   private String user;
   private String requestMethod ;
   private LocalDateTime timestamp;

   public StatEntity() {
   }

   @Column
   public String getIp() {
      return ip;
   }

   public StatEntity setIp(String ip) {
      this.ip = ip;
      return this;
   }

   @Column
   public String getUrl() {
      return url;
   }

   public StatEntity setUrl(String url) {
      this.url = url;
      return this;
   }

   @Column
   public String getPage() {
      return page;
   }

   public StatEntity setPage(String page) {
      this.page = page;
      return this;
   }

   @Column
   public String getRefererPage() {
      return refererPage;
   }

   public StatEntity setRefererPage(String refererPage) {
      this.refererPage = refererPage;
      return this;
   }


   @Column
   public String getUser() {
      return user;
   }

   public StatEntity setUser(String user) {
      this.user = user;
      return this;
   }

   @Column
   public String getUserAgent() {
      return userAgent;
   }

   public StatEntity setUserAgent(String userAgent) {
      this.userAgent = userAgent;
      return this;
   }

   @Column
   public String getRequestMethod() {
      return requestMethod;
   }

   public StatEntity setRequestMethod(String requestMethod) {
      this.requestMethod = requestMethod;
      return this;
   }

   @Column
   public LocalDateTime getTimestamp() {
      return timestamp;
   }

   public StatEntity setTimestamp(LocalDateTime timestamp) {
      this.timestamp = timestamp;
      return this;
   }
}
