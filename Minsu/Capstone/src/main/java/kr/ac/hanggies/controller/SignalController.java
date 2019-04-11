package kr.ac.hanggies.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SignalController {
	
	@RequestMapping(value = "/sensing", headers="Accept=*/*", method = RequestMethod.POST, 
			produces = { "application/json" })
	public @ResponseBody String sensingSignal(@RequestBody Map<String, Object> info) {
	
		System.out.println("sid: " + info.get("sid"));
		System.out.println("signal: " + info.get("signal"));
		
		return "home";
	}
	
	@RequestMapping(value = "/change", headers="Accept=*/*", 
			produces = { "application/json" })
	public @ResponseBody String changeSignal(@RequestBody Map<String, Object> info) {
	
		System.out.println("sid: " + info.get("sid"));
		System.out.println("signal: " + info.get("signal"));
		
		return "home";
	}
	
}
