package cn.pomit.consulproxy.handler;

import com.alibaba.fastjson.JSONObject;

import cn.pomit.consul.annotation.Mapping;
import cn.pomit.consul.handler.resource.AbstractResourceHandler;
import cn.pomit.consul.http.HttpRequestMessage;
import cn.pomit.consul.http.HttpResponseMessage;
import cn.pomit.consulproxy.dto.ResultModel;
import io.netty.handler.codec.http.HttpHeaders;

public class PostTestHandler extends AbstractResourceHandler {
	
	@Mapping(value = "/test/form")
	public HttpResponseMessage form(HttpRequestMessage httpRequestMessage) {
		try {
			String pomit = httpRequestMessage.getParameterString("pomit");
			int version = httpRequestMessage.getParameterInt("version");
			
			String ret = pomit + version;
			HttpHeaders httpHeaders = httpRequestMessage.headers();
			String headConnection = httpHeaders.get("Connection");
			System.out.println(headConnection);
			return HttpResponseMessage.responeseBody(ResultModel.ok(ret));
		} catch (Exception e) {
			e.printStackTrace();
			HttpResponseMessage.responeseBody(ResultModel.error(e.getMessage()));
		}
		return HttpResponseMessage.responeseBody(ResultModel.ok());
	}
	
	@Mapping(value = "/test/json")
	public HttpResponseMessage json(HttpRequestMessage httpRequestMessage) {
		try {
			String content = httpRequestMessage.getContent();
			ResultModel resultModel = JSONObject.parseObject(content, ResultModel.class);
			
			System.out.println(resultModel.toString());
			
			return HttpResponseMessage.responeseBody(ResultModel.ok(resultModel));
		} catch (Exception e) {
			e.printStackTrace();
			HttpResponseMessage.responeseBody(ResultModel.error(e.getMessage()));
		}
		return HttpResponseMessage.responeseBody(ResultModel.ok());
	}
}
