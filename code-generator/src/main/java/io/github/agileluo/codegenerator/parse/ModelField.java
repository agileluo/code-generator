package io.github.agileluo.codegenerator.parse;

import io.github.agileluo.codegenerator.util.CodeUtil;

public class ModelField {
	private String name;
	private int length;
	private String comment;
	private String codeType;
	
	private Class<?> javaType;
	private boolean key;
	private boolean notNull;
	private boolean query;
	private boolean index;
	private boolean autoIncrease;
	private boolean isBigText;
	
	//整数精度
	private int integer;
	//fraction
	private int fraction;
	
	private String dbName;
	private String dbValue;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Class<?> getJavaType() {
		return javaType;
	}
	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}
	public boolean isKey() {
		return key;
	}
	public void setKey(boolean key) {
		this.key = key;
	}
	public boolean isNotNull() {
		return notNull;
	}
	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}
	public boolean isQuery() {
		return query;
	}
	public void setQuery(boolean query) {
		this.query = query;
	}
	public boolean isIndex() {
		return index;
	}
	public void setIndex(boolean index) {
		this.index = index;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDbValue() {
		return dbValue;
	}
	public void setDbValue(String dbValue) {
		this.dbValue = dbValue;
	}
	public boolean isAutoIncrease() {
		return autoIncrease;
	}
	public void setAutoIncrease(boolean autoIncrease) {
		this.autoIncrease = autoIncrease;
	}
	public boolean isBigText() {
		return isBigText;
	}
	public void setBigText(boolean isBigText) {
		this.isBigText = isBigText;
	}
	public int getInteger() {
		return integer;
	}
	public void setInteger(int integer) {
		this.integer = integer;
	}
	public int getFraction() {
		return fraction;
	}
	public void setFraction(int fraction) {
		this.fraction = fraction;
	}
	public String getCodeType() {
		return codeType;
	}
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	public String getCName(){
		return CodeUtil.getClassName(this.name);
	}
}
