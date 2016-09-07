package com.newyeah.utils.mybatis.generator;

import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 
 * @Author LiuYang
 * @Date 2016Äê9ÔÂ6ÈÕ
 * @Description : 
 *
 */
public class AnnotationsPlugin extends PluginAdapter {

    private String[] anotations;

    public AnnotationsPlugin() {
        super();
    }

    public boolean validate(List<String> warnings) {
        // this plugin is always valid
        return true;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String anotationStr = properties.getProperty("anotations") ;
        if(anotationStr!=null && !anotationStr.isEmpty()){
        	String[] array_notation = anotationStr.split(",");
        	anotations = new String[array_notation.length] ;
        	System.arraycopy(array_notation, 0, anotations, 0, anotations.length);
        }
    }


	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		makeAnotations(interfaze, introspectedTable);
		return true ;
	}

	protected void makeAnotations(Interface interfaze,
            IntrospectedTable introspectedTable) {
    	if(anotations!=null && anotations.length>0) {
    		for(String anotation : anotations) {
    			String simpleAnotation = anotation.substring(anotation.lastIndexOf(".")+1) ;
    			interfaze.addImportedType(new FullyQualifiedJavaType(anotation));
    			interfaze.addAnnotation("@"+simpleAnotation);
    		}
    	}
    }
}
