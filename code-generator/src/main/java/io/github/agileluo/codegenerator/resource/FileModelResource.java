package io.github.agileluo.codegenerator.resource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class FileModelResource extends ModelRecource {
	
	public FileModelResource(String fieldFile, String configFile) throws IOException{
		List<String> lines = FileUtils.readLines(new File(configFile));
		props = new HashMap<>();
		for(String line : lines){
			String[] fields = line.split("=");
			if(fields.length == 2){
				props.put(fields[0].trim(), fields[1].trim());
			}
		}
		fieldList = FileUtils.readLines(new File(fieldFile));
	}
}
