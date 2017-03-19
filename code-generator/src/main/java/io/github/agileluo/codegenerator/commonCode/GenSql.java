package io.github.agileluo.codegenerator.commonCode;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class GenSql {
	public static void main(String[] args) throws Exception {
		List<String> lines = FileUtils.readLines(new File("src/main/java/com/yzb/framework/code/commonCode/data.txt"));
		int index = 1;
		int codeindex = 1;
		for (String line : lines) {
			String[] field = line.split("\\s+");
			String code = codeindex++ + "";
			if (field.length == 2) {
				code = field[1];
			}
			
			String sql = String.format(
					"INSERT INTO `common_code` (name,code,order_num,group_code, level, parent_code, create_by, create_date, update_by, update_date) VALUES ('%s', '%s', %d, '%s', 1, '%s', 'andy', now(), 'andy',now());",
												field[0], field[1], index++, "ipc.dutytype",field.length==3 ?  field[2] : "");
			System.out.println(sql);
		}
	}
}