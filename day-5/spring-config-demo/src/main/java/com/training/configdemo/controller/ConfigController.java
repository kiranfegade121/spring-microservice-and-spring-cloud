package com.training.configdemo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.configdemo.util.DbSettings;
import com.training.configdemo.util.MyAppUtil;

@RestController
@RequestMapping("/api/v1")
public class ConfigController {

	@Value("${my.greeting}")
	private String greetingMessage;
	
	@Value("${app.name: SpringCloudAppDemo}")
	private String appName;
	
	@Value("some static value")
	private String staticMessage;
	
	@Value("${my.list.values}")
	private List<String> names;
	
	@Value("#{${dbvalues}}")
	private Map<String, String> dbValues;
	
	@Autowired
	private DbSettings dbSettings;
	
	@Autowired
	private MyAppUtil myAppUtil;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/greeting")
	public String greetingMessage() {
		return greetingMessage + " - " + staticMessage + " - " + names + " - " + dbValues + " - " + myAppUtil.getAppName() + " - " + myAppUtil.getAppVersion();
	}
	
	@GetMapping("/appdetails")
	public String getAppDetails() {
		return appName;
	}
	
	@GetMapping("/dbsettings")
	public String getDBSettings() {
		return dbSettings.getUrl() + " - " + dbSettings.getHost() + " - " + dbSettings.getPort() + " - " + dbSettings.getUsername() + " - "  + dbSettings.getPassword();
	}
	
	@GetMapping("/envdetails")
	public String getEnvDetails() {
		//return env.toString();
		return env.getProperty("my.greeting") + " - " + env.getProperty("application.version") + " - " + env.getProperty("mysql.db.password") + " - " + env.getProperty("server.port"); 
	}
} 
















