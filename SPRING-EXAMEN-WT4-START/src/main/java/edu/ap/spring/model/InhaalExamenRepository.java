package edu.ap.spring.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import edu.ap.spring.redis.RedisService;

@Repository
public class InhaalExamenRepository implements RedisRepository {
	private RedisService service;
	  private static final String KEY = "examen";
	    private RedisTemplate<String, Object> redisTemplate;
	    private HashOperations hashOperations;
	    
	    @Autowired
		public void setRedisService(RedisService service) {
			this.service = service;
		}
	    
	    @Autowired
	    public InhaalExamenRepository(RedisTemplate<String, Object> redisTemplate){
	        this.redisTemplate = redisTemplate;
	    }
	    @PostConstruct
	    private void init(){
	        hashOperations = redisTemplate.opsForHash();
	    }

	@Override
	public Map<Object, Object> findAllExamen() {
		 return hashOperations.entries(KEY);
	}

	@Override
	public void add(InhaalExamen examen) {
		Map<String, String> examenDetails = new HashMap<String, String>();
		examenDetails.put("exam", examen.getExam());
		examenDetails.put("date", examen.getDate());
		examenDetails.put("reason", examen.getReason());
		
		 service.hset(KEY, examenDetails);
		 
		 
		
	}

	

	@Override
	public InhaalExamen findExamen(String student) {
		return (InhaalExamen) hashOperations.get(KEY, student);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
