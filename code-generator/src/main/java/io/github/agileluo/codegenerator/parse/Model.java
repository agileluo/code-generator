package io.github.agileluo.codegenerator.parse;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
	private Class<?> cls;
	/** 所属领域 */
	private String aggregate;
	/** 模型名称 */
	private String name;
	private String dbName;
	
	private String className;
	private String comment;
	
	private String packageName;
	private boolean editAble;
	private String orderBy;
	
	/** 模型主键 */
	private Field key;
	private String keyClassName;
	
	private List<ModelField> fieldList;
	private Map<String, Object> conf = new HashMap<>();
	
	public String getAggregate() {
		return aggregate;
	}

	public void setAggregate(String aggregate) {
		this.aggregate = aggregate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Field getKey() {
		return key;
	}

	public void setKey(Field key) {
		this.key = key;
	}

	public List<ModelField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<ModelField> fieldList) {
		this.fieldList = fieldList;
	}
	public String getClassName() {
		return className;
	}

	public Class<?> getCls() {
		return cls;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, Object> getConf() {
		return conf;
	}

	public void setConf(Map<String, Object> conf) {
		this.conf = conf;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public boolean isEditAble() {
		return editAble;
	}

	public void setEditAble(boolean editAble) {
		this.editAble = editAble;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getKeyClassName() {
		return keyClassName;
	}

	public void setKeyClassName(String keyClassName) {
		this.keyClassName = keyClassName;
	}


	
}
