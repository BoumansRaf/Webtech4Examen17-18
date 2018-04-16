package edu.ap.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import edu.ap.spring.model.InhaalExamen;
import edu.ap.spring.redis.RedisService;

@Controller
public class ExamenController {
	
	private List<String> redisMessages = new ArrayList<String>();
	   private RedisService service;
	   @Autowired
	    private edu.ap.spring.model.RedisRepository redisRepository;
		
	   @Autowired
		public void setRedisService(RedisService service) {
			this.service = service;
	   }
	   
	   @RequestMapping("/")
	   @ResponseBody
	   public String messages() {
		   String html = "<HTML><HEAD><meta http-equiv=\"refresh\" content=\"5\"></HEAD>";
		   html += "<BODY><h1>Voeg Examen Toe</h1><br/><br/>";
		   html += "<form id=\"addForm\">" + 
		   		"                <div class=\"form-group\">" + 
		   		"                    <label for=\"studentInput\">Student</label>" + 
		   		"                    <input name=\"studentInput\" id=\"studentInput\" class=\"form-control\"/>" + 
		   		"                </div>" + 
		   		"                <div class=\"form-group\">" + 
		   		"                    <label for=\"examInput\">Examen</label>" + 
		   		"                    <input name=\"examInput\" id=\"examInput\" class=\"form-control\"/>" + 
		   		"                </div>\r\n" + 
		   		"<div class=\"form-group\">" + 
				   		"                    <label for=\"reasonInput\">Reason</label>" + 
				   		"                    <input name=\"reasonInput\" id=\"reasonInput\" class=\"form-control\"/>" + 
				   		"                </div>" + 
		   		"                <button class=\"btn btn-default\" id=\"addButton\">Add</button>" + 
		   		"            </form>";
		   
		   
		   
		   html += "</BODY></HTML>";
		   
		   return html;
	   }
	   
	   @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public ResponseEntity<String> add(
	        @RequestParam String student,
	        @RequestParam String exam,
	        @RequestParam String reason) {
	        
	        InhaalExamen examen = new InhaalExamen(student, exam, reason);
	        
	        redisRepository.add(examen);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }

}
