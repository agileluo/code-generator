package io.github.agileluo.codegenerator.parse;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.github.agileluo.codegenerator.core.MyEntity;
import io.github.agileluo.codegenerator.core.MyField;
import io.github.agileluo.codegenerator.util.CodeUtil;

public class JavaClassModelParse implements ModelParse {
	private Model m;
	
	public JavaClassModelParse(Class<?> c){
		m = new Model();
		m.setCls(c);
		m.setClassName(c.getSimpleName());
		m.setName(classNameToModelName(c.getSimpleName()));
		String packageName = c.getPackage().getName();
		String[] split = packageName.split("\\.");
		m.setAggregate(split[2]);
		
		m.setPackageName(packageName.substring(0, packageName.lastIndexOf(".")));
		
		MyEntity sparkModel = c.getAnnotation(MyEntity.class);
		if(sparkModel != null){
			m.setComment(sparkModel.comment());
			m.setEditAble(sparkModel.editAble());
		}
		
		List<ModelField> fieldList = new ArrayList<>();
		for(Field f : c.getDeclaredFields()){
			ModelField mf = new ModelField();
			mf.setName(f.getName());
			
			mf.setJavaType(f.getType());
			MyField fild = f.getAnnotation(MyField.class);
			if(fild == null){
				continue;
			}
			if(fild != null){
				
				mf.setNotNull(fild.notNull());
				mf.setComment(fild.comment());
				mf.setCodeType(fild.codeType());
				mf.setIndex(fild.index());
				mf.setQuery(fild.query());
				mf.setAutoIncrease(fild.autoIncrease());
				mf.setLength(fild.length());
				
				if(fild.key()){
					mf.setKey(fild.key());
					m.setKey(f);
					m.setKeyClassName(CodeUtil.getClassName(f.getName()));
					mf.setNotNull(true);
				}
				if(fild.orderBy()){
					m.setOrderBy(f.getName());
				}
				
			}
			
			if("updateDate".equals(f.getName())){
				m.setOrderBy("update_date");
			}else{
				m.setOrderBy("create_date");
			}
			fieldList.add(mf);
		}
		m.setFieldList(fieldList);
	}
	
	public String classNameToModelName(String className){
		return className.substring(0,1).toLowerCase() + className.substring(1);
	}
	
	@Override
	public Model getModel() {
		return this.m;
	}
}
