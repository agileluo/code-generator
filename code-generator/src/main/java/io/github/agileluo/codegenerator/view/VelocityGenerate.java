package io.github.agileluo.codegenerator.view;

import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import io.github.agileluo.codegenerator.parse.Model;
import io.github.agileluo.codegenerator.parse.ModelField;
import io.github.agileluo.codegenerator.util.CodeUtil;

public class VelocityGenerate {
	private Model model; 
	public void generate(Model model){
		this.model = model;
		Properties p = new Properties();
		p.put("file.resource.loader.class",
		"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		VelocityEngine ve = new VelocityEngine();
		ve.init(p);
		VelocityContext context = new VelocityContext();
		model.setDbName(CodeUtil.toDbName(model.getName()));
		model.setOrderBy(CodeUtil.toDbName(model.getOrderBy()));
		context.put("model", model);
		List<ModelField> fieldList = model.getFieldList();
		for(ModelField f : fieldList){
			if(f.isKey() && f.isAutoIncrease()){
				model.getConf().put("isKeyAutoIncrease", true);
			}
			f.setDbName(CodeUtil.toDbName(f.getName()));
			if("createDate".equals(f.getName()) 
					||"updateDate".equals(f.getName()) ){
				f.setDbValue("now()");
			}else{
				f.setDbValue("#" + f.getName() + "#");
			}
		}
		genIbatis(ve, fieldList);
		genSql(ve, fieldList);
		
		parseVm("sql", "sql", ve, null);
		parseVm("Dao", "java", ve, null);
		parseVm("DaoImpl", "java", ve, null);
		parseVm("Service", "java", ve, null);
		parseVm("ServiceImpl", "java", ve, null);
		parseVm("Controller", "java", ve, null);
		
		
		/*parseVm("jsp_Edit", "jsp", ve, null);
		parseVm("jsp_Query", "jsp", ve, new ModelConfig() {
			public void configModel(Model m) {
				Map<String, Object> conf = m.getConf();
				List<ModelField> queryList = new ArrayList<>();
				conf.put("queryList", queryList);
				fieldList.forEach(f ->{
					if(f.isQuery())
						queryList.add(f);
				});
			}
		});
		parseVm("js_Query", "js", ve, null);
		parseVm("js_Edit", "js", ve, null);*/
		parseVmWithTarget("static/html_detail", "-detail.shtml", ve, null);
		parseVmWithTarget("static/html_list", "-list.shtml", ve, null);
		parseVmWithTarget("static/html_pop", "-pop.shtml", ve, null);
		parseVmWithTarget("static/html_select", "-select.shtml", ve, null);
		parseVmWithTarget("static/js_list", "-list.js", ve, null);
		parseVmWithTarget("static/js_service", "-service.js", ve, null);
		parseVmWithTarget("static/js_detail", "-detail.js", ve, null);
		
	}
	private void genSql(VelocityEngine ve, List<ModelField> fieldList) {
		parseVm("sql", "sql", ve, new ModelConfig() {
			public void configModel(Model m) {
				Map<String, Object> conf = m.getConf();
				conf.put("keyDbName", CodeUtil.toDbName(m.getKey().getName()));
				List<Map<String, String>> sqlList = new ArrayList<>();
				conf.put("sqlList", sqlList);
				fieldList.forEach(f ->{
					Map<String, String> o = new HashMap<>();
					o.put("dbName", f.getDbName());
					String sqlType = null;
					if(f.getJavaType() == String.class){
						if(f.isBigText()){
							sqlType = "text";
						}else{
							sqlType = "varchar(" + f.getLength() + ")";
						}
					}else if(f.getJavaType() == Integer.class
							|| f.getJavaType() == int.class){
						sqlType = "int";
					}else if(f.getJavaType() == Long.class
							|| f.getJavaType() == long.class){
						sqlType = "bigint";
					}else if(f.getJavaType() == Date.class){
						sqlType = "datetime";
					}else if(f.getJavaType() == BigDecimal.class
							|| f.getJavaType() == Float.class
							|| f.getJavaType() == Double.class
							|| f.getJavaType() == float.class
							|| f.getJavaType() == double.class){
						sqlType = "decimal(" + f.getInteger() + ", " + f.getFraction() + ")";
					}
					o.put("sqlType", sqlType);
					
					if(f.isNotNull() || f.isKey()){
						o.put("notNull", "NOT NULL");
					}else{
						o.put("notNull", "");
					}
					if(f.isAutoIncrease()){
						o.put("autoIncrease", "AUTO_INCREMENT");
					}else{
						o.put("autoIncrease", "");
					}
					o.put("comment", f.getComment());
					if(StringUtils.isNotBlank(f.getCodeType())){
						o.put("codeType",  ", codeType:" + f.getCodeType());
					}else{
						o.put("codeType", "");
					}
					sqlList.add(o);
				});
			}		
		});
	}
	private void genIbatis(VelocityEngine ve, List<ModelField> fieldList) {
		parseVm("ibatis", "xml", ve, new ModelConfig() {
			public void configModel(Model m) {
				Map<String, Object> conf = m.getConf();
				conf.put("fullClassName", m.getCls().getName());
				conf.put("className", m.getCls().getSimpleName());
				conf.put("keyType", m.getKey().getType().getName());
				conf.put("key", m.getKey().getName());
				conf.put("keyClassName", CodeUtil.getClassName(m.getKey().getName()));
				conf.put("keyDbName", CodeUtil.toDbName(m.getKey().getName()));
				
				List<ModelField> queryList = new ArrayList<>();
				conf.put("queryList", queryList);
				fieldList.forEach(f ->{
					if(f.isQuery())
						queryList.add(f);
				});
				List<ModelField> insertList = new ArrayList<>();
				conf.put("insertList", insertList);
				fieldList.forEach(f ->{
					if(!f.isAutoIncrease())
						insertList.add(f);
				});
				List<ModelField> updateList = new ArrayList<>();
				conf.put("updateList", updateList);
				fieldList.forEach(f ->{
					if(!"createDate".equals(f.getName()) 
							&& !"createBy".equals(f.getName())
							&& !"updateDate".equals(f.getName()) 
							&& !"updateBy".equals(f.getName())
							&& !f.isKey())
						updateList.add(f);
				});
			}		
		});
	}
	
	private void parseVmWithTarget(String template, String target, VelocityEngine ve, ModelConfig config){
		try {
			VelocityContext context = new VelocityContext();
			context.put("m", model);
			if(config != null){
				config.configModel(model);
			}
			Template t = ve.getTemplate("template/" + template + ".vm", "UTF-8");
			String fileName = model.getName() + target;
			Writer writer = new FileWriterWithEncoding("target/" + fileName, "UTF-8");
			t.merge(context, writer);
			writer.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void parseVm(String template, String extend, VelocityEngine ve, ModelConfig config){
		try {
			VelocityContext context = new VelocityContext();
			context.put("m", model);
			if(config != null){
				config.configModel(model);
			}
			
			Template t = ve.getTemplate("template/" + template + ".vm", "UTF-8");
			String fileName = null;
			if("xml".equals(extend)){
				fileName = model.getName() + "-ibatis.xml";
			}else if("java".equals(extend)){
				if("pojo_".equals(template)){
					fileName = model.getClassName()+".java";
				}else{
					fileName = model.getClassName() + template +".java";
				}
			}else if("jsp".equals(extend)){
				fileName = model.getName() + template.substring(template.indexOf("_") + 1) + ".jsp";
			}else if("js".equals(extend)){
				fileName = model.getName() + template.substring(template.indexOf("_") + 1) + ".js";
			}else if("sql".equals(extend)){
				fileName = model.getName() + ".sql";
			}else{
				fileName =  model.getName() + template + "." +  extend;
			}
			Writer writer = new FileWriterWithEncoding("target/" + fileName, "UTF-8");
			t.merge(context, writer);
			writer.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
