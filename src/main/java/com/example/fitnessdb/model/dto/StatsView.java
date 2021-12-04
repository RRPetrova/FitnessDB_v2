package com.example.fitnessdb.model.dto;

import com.example.fitnessdb.model.entity.UserEntity;

import java.time.LocalDateTime;

public class StatsView {
    private Long id;
    private String ip;
    private String url;
    private String page;
    private String refererPage;
    private String userAgent;
    private String user;
    private String requestMethod;
    private LocalDateTime timestamp;

    public StatsView() {
    }

    public Long getId() {
        return id;
    }

    public StatsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public StatsView setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public StatsView setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPage() {
        return page;
    }

    public StatsView setPage(String page) {
        this.page = page;
        return this;
    }

    public String getRefererPage() {
        return refererPage;
    }

    public StatsView setRefererPage(String refererPage) {
        this.refererPage = refererPage;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public StatsView setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getUser() {
        return user;
    }

    public StatsView setUser(String user) {
        this.user = user;
        return this;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public StatsView setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public StatsView setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
