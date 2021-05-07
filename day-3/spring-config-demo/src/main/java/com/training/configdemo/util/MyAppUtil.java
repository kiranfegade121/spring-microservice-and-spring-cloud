package com.training.configdemo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:myapp.properties")
//@PropertySource("classpath: myapp2.properties")

//@PropertySources({
//	@PropertySource("classpath: myapp.properties"),
//	@PropertySource("classpath: myapp2.properties")
//})
public class MyAppUtil {

	@Value("${application.name}")
	private String appName;

	@Value("${application.version}")
	private String appVersion;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

}
