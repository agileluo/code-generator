package io.github.agileluo.codegenerator.rest.vo;

import java.util.List;

/**
 * 接口文档模型
 * @author marlon.luo
 *
 */
public class RestApi {
	
	//地址
	private String url;
	//名称
	private String name;
	//描述
	private String desc;
	//接口类型
	private String type;
	//支持的请求类型[GET, POST, DEL...]
	private List<String> methods;
	
	//请求参数
	private List<RestRequest> reqs;
	
	private List<RestResponse> resps;
	
	private List<RestError> errs;

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getMethods() {
		return methods;
	}

	public void setMethods(List<String> methods) {
		this.methods = methods;
	}

	public List<RestRequest> getReqs() {
		return reqs;
	}

	public void setReqs(List<RestRequest> reqs) {
		this.reqs = reqs;
	}

	public List<RestResponse> getResps() {
		return resps;
	}

	public void setResps(List<RestResponse> resps) {
		this.resps = resps;
	}

	public List<RestError> getErrs() {
		return errs;
	}

	public void setErrs(List<RestError> errs) {
		this.errs = errs;
	}
	
	
	
}
