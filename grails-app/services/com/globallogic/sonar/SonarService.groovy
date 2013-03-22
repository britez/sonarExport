package com.globallogic.sonar

import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.services.Metric;
import org.sonar.wsclient.services.MetricQuery;
import org.sonar.wsclient.services.ResourceQuery;

class SonarService {
	
	/** The spring security service injection */
	def springSecurityService
	
	/** The user service */
	def userService

    def listMetrics() {
		SonarEnvironment env = this.getEnvironment()
		Sonar sonar = Sonar.create(env.serverUrl, env.username, env.password)
		sonar.findAll(MetricQuery.all())
    }
	
	def listProjects(def metricKey) {
		SonarEnvironment env = this.getEnvironment()
		Sonar sonar = Sonar.create(env.serverUrl, env.username, env.password)
		sonar.findAll(new ResourceQuery().setMetrics(metricKey))
	}
	
	def extractMetrics(def projectKey, def metricKey){
		this.getProject(projectKey, metricKey).getMeasure(metricKey)
	}
	
	def getProject(def projectKey, def metricKey){
		SonarEnvironment env = this.getEnvironment()
		Sonar sonar = Sonar.create(env.serverUrl, env.username, env.password)
		sonar.find(ResourceQuery.createForMetrics(projectKey,metricKey))
	}
	
	private def getEnvironment(){
		User user = this.userService.getSessionUser()
		return user.sonarEnvironment
	}
}
