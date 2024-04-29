package com.example.weblogin.domain.session;

public interface SessionRepository {

    public void createSession(String key, Object value);

    public Object getSession(String key);

    public void removeSession(String key);

}
