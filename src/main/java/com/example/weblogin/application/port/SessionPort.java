package com.example.weblogin.application.port;

public interface SessionPort {

    public void createSession(String key, Object value);

    public Object getSession(String key);

    public void removeSession(String key);

}
