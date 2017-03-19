package io.github.agileluo.codegenerator.resource;

import java.util.List;
import java.util.Map;

public abstract class ModelRecource {
	protected List<String> fieldList;
	protected Map<String, String> props;
	
	public List<String> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}
	public Map<String, String> getProps() {
		return props;
	}
	public void setProps(Map<String, String> props) {
		this.props = props;
	}
	
	
}
