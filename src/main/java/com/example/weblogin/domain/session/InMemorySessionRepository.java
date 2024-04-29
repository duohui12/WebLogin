package com.example.weblogin.domain.session;

import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemorySessionRepository implements SessionRepository{

    private Map<String,Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션 생성
     */
    @Override
    public void createSession(String key, Object value) {
        sessionStore.put(key,value);
    }

    /**
     * 세션 조회
     */
    @Override
    public Object getSession(String key) {
        return sessionStore.get(key);
    }

    /**
     * 세션 만료
     */
    @Override
    public void removeSession(String key) {
        sessionStore.remove(key);
    }

}
