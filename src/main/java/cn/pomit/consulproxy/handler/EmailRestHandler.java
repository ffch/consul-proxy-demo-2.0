package cn.pomit.consulproxy.handler;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSONObject;

import cn.pomit.consul.annotation.Mapping;
import cn.pomit.consul.handler.resource.AbstractResourceHandler;
import cn.pomit.consul.http.HttpRequestMessage;
import cn.pomit.consul.http.HttpResponseMessage;
import cn.pomit.consul.http.res.ResCode;
import cn.pomit.consul.http.res.ResType;
import cn.pomit.consulproxy.dto.ResultCode;
import cn.pomit.consulproxy.dto.ResultModel;
import cn.pomit.consulproxy.dto.mail.Email;
import cn.pomit.consulproxy.service.mail.MailService;

public class EmailRestHandler extends AbstractResourceHandler {

	@Mapping(value = "/email/code")
	public HttpResponseMessage findUser(HttpRequestMessage httpRequestMessage) {
		ResultModel rm = null;

		try {
			String content = httpRequestMessage.getBody().toString(Charset.defaultCharset());
			Email email = JSONObject.parseObject(content, Email.class);
			if (email == null) {
				rm = new ResultModel(ResultCode.CODE_00003);
			} else {
				MailService.sendHtmlMail(email);
				rm = ResultModel.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			rm = ResultModel.error(e.getMessage());
		}
		HttpResponseMessage httpResponseMessage = new HttpResponseMessage();
		httpResponseMessage.setResCode(ResCode.OK.getValue());
		httpResponseMessage.setResType(ResType.JSON.getValue());
		httpResponseMessage.setMessage(JSONObject.toJSONString(rm));
		return httpResponseMessage;
	}

	@Mapping(value = "/health")
	public HttpResponseMessage add(HttpRequestMessage httpRequestMessage) throws Exception {
		HttpResponseMessage httpResponseMessage = new HttpResponseMessage();
		httpResponseMessage.setResCode(ResCode.OK.getValue());
		httpResponseMessage.setResType(ResType.JSON.getValue());
		httpResponseMessage.setMessage("echo");
		return httpResponseMessage;
	}

}
