package edu.ap.spring.model;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InhaalExamenRepository implements RedisRepository {
	
	  private static final String KEY = "Examen";
	    private RedisTemplate<String, Object> redisTemplate;
	    private HashOperations hashOperations;
	    
	    @Autowired
	    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
	        this.redisTemplate = redisTemplate;
	    }
	    @PostConstruct
	    private void init(){
	        hashOperations = redisTemplate.opsForHash();
	    }

	@Override
	public Map<Object, Object> findAllExamen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(InhaalExamen examen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InhaalExamen findExamen(String student) {
		// TODO Auto-generated method stub
		return null;
	}

}
