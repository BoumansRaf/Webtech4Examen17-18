package edu.ap.spring.model;

import java.util.Map;

public interface RedisRepository {
    Map<Object, Object> findAllExamen();
    void add(InhaalExamen examen);
    void delete(String id);
    InhaalExamen findExamen(String student);
}