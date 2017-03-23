package io.github.agileluo.codegenerator.rest.vo;

import java.util.List;

/**
 * 接口返回值
 * @author marlon.luo
 *
 */
public class RestResponse {
	private String name;
	private String type;
	private List<RestResponse> details;
	private String example;
	private String desc;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<RestResponse> getDetails() {
		return details;
	}
	public void setDetails(List<RestResponse> details) {
		this.details = details;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
