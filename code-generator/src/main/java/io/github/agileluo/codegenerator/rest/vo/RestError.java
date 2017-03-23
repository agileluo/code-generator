package io.github.agileluo.codegenerator.rest.vo;


/**
 * 返回错误码
 * @author marlon.luo
 *
 */
public class RestError {
	//错误码
	private String code;
	//错误描述
	private String msg;
	//解决方案
	private String dealMethod;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDealMethod() {
		return dealMethod;
	}
	public void setDealMethod(String dealMethod) {
		this.dealMethod = dealMethod;
	}
}
