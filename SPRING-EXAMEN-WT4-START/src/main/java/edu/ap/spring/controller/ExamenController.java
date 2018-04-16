package edu.ap.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.ap.spring.redis.RedisService;

@Controller
public class ExamenController {
	
	private List<String> redisMessages = new ArrayList<String>();
	   private RedisService service;
		
	   @Autowired
		public void setRedisService(RedisService service) {
			this.service = service;
	   }
	   
	   @RequestMapping("/examen")
	   @ResponseBody
	   public String messages() {
		   String html = "<HTML><HEAD><meta http-equiv=\"refresh\" content=\"5\"></HEAD>";
		   html += "<BODY><h1>Voeg Examen Toe</h1><br/><br/>";
		   html += "<form>";
		   
		   
		   
		   html += "</BODY></HTML>";
		   
		   return html;
	   }

}
