package io.github.agileluo.codegenerator.rest.vo;

/**
 * 请求参数
 * @author marlon.luo
 *
 */
public class RestRequest {
	private String name;
	private String type;
	private String must;
	private String example;
	private String limit;
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
	public String getMust() {
		return must;
	}
	public void setMust(String must) {
		this.must = must;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
