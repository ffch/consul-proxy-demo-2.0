package cn.pomit.consulproxy.handler;

import cn.pomit.consul.annotation.Mapping;
import cn.pomit.consul.handler.resource.AbstractResourceHandler;
import cn.pomit.consul.http.HttpRequestMessage;
import cn.pomit.consul.http.HttpResponseMessage;
import cn.pomit.consul.rest.RestClient;
import cn.pomit.consulproxy.dto.ResultModel;
import cn.pomit.consulproxy.dto.mail.Email;

public class RibbonRestHandler extends AbstractResourceHandler {
	private RestClient restClient = RestClient.getInstance();
	
	@Mapping(value = "/rest/consul")
	public HttpResponseMessage consul(HttpRequestMessage httpRequestMessage) {
		try {
			ResultModel rm = restClient.getForObject("http://consulServer/consulServer/ip", ResultModel.class, null);
			return HttpResponseMessage.responeseBody(rm);
		} catch (Exception e) {
			e.printStackTrace();
			HttpResponseMessage.responeseBody(ResultModel.error(e.getMessage()));
		}
		return HttpResponseMessage.responeseBody(ResultModel.ok());
	}
	
	@Mapping(value = "/rest/url")
	public HttpResponseMessage url(HttpRequestMessage httpRequestMessage) {
		try {
			ResultModel rm = restClient.getForObject("http://127.0.0.1:8812/consulClient/ip", ResultModel.class, null);
			return HttpResponseMessage.responeseBody(rm);
		} catch (Exception e) {
			e.printStackTrace();
			HttpResponseMessage.responeseBody(ResultModel.error(e.getMessage()));
		}
		return HttpResponseMessage.responeseBody(ResultModel.ok());
	}
	
	@Mapping(value = "/rest/consulPost")
	public HttpResponseMessage consulPost(HttpRequestMessage httpRequestMessage) {
		try {
			Email email = new Email();
			email.setContent("asdasdasdas");
			email.setTo("a123123123");
			ResultModel rm = restClient.postForObject("http://consulServer/consulServer/post", ResultModel.class, email);
			return HttpResponseMessage.responeseBody(rm);
		} catch (Exception e) {
			e.printStackTrace();
			HttpResponseMessage.responeseBody(ResultModel.error(e.getMessage()));
		}
		return HttpResponseMessage.responeseBody(ResultModel.ok());
	}
}
