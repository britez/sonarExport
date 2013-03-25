package com.globallogic.sonar

import org.sonar.wsclient.Sonar;
import org.sonar.wsclient.services.Metric;
import org.sonar.wsclient.services.MetricQuery;
import org.sonar.wsclient.services.ResourceQuery;

class SonarService {
	
	/** The spring security service injection */
	def springSecurityService
	
	/** The sonar service */
	def sonar
	
	/** The user service */
	def userService
	
	private def getService(){
		if(sonar == null){
			SonarEnvironment env = this.getEnvironment()
			sonar = Sonar.create(env.serverUrl, env.username, env.password)
		}
		return sonar
	}

	/** @return all the metrics available in sonar */
    def listMetrics() {
		this.getService().findAll(MetricQuery.all())
    }
	
	/** @return all the projects available in sonar */
	def listProjects(def metricKey) {
		this.getService().findAll(new ResourceQuery().setMetrics(metricKey))
	}
	
	/** @return the result for a metric and project */
	def extractMetrics(def projectKey, def metricKey){
		this.getProject(projectKey, metricKey).getMeasure(metricKey)
	}
	
	/** @return a metric from sonar */
	def getMetric(def metricKey) {
		this.getService().find(MetricQuery.byKey(metricKey))
	}
	
	/** @return a project from sonar */
	def getProject(def projectKey, def metricKey){
		this.getService().find(ResourceQuery.createForMetrics(projectKey,metricKey))
	}
	
	/** @return the environment */
	private def getEnvironment(){
		User user = this.userService.getSessionUser()
		return user.sonarEnvironment
	}
}
