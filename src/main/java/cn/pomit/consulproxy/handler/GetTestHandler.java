package cn.pomit.consulproxy.handler;

import java.util.Map;

import cn.pomit.consul.annotation.Mapping;
import cn.pomit.consul.handler.resource.AbstractResourceHandler;
import cn.pomit.consul.http.HttpRequestMessage;
import cn.pomit.consul.http.HttpResponseMessage;
import cn.pomit.consulproxy.dto.ResultModel;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;

public class GetTestHandler extends AbstractResourceHandler {
	
	@Mapping(value = "/test/get")
	public HttpResponseMessage testGet(HttpRequestMessage httpRequestMessage) {
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
	
	@Mapping(value = "/test/cookie")
	public HttpResponseMessage cookie(HttpRequestMessage httpRequestMessage) {
		try {
			String pomit = httpRequestMessage.getParameterString("pomit");
			int version = httpRequestMessage.getParameterInt("version");
			
			String ret = pomit + version;
			
			Map<String, Cookie> cookies = httpRequestMessage.getCookies();
			System.out.println(cookies);
			return HttpResponseMessage.responeseBody(ResultModel.ok(ret));
		} catch (Exception e) {
			e.printStackTrace();
			HttpResponseMessage.responeseBody(ResultModel.error(e.getMessage()));
		}
		return HttpResponseMessage.responeseBody(ResultModel.ok());
	}
}
