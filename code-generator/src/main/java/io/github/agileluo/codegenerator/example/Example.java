package io.github.agileluo.codegenerator.example;

import io.github.agileluo.codegenerator.example.entity.User;
import io.github.agileluo.codegenerator.generate.CodeGenerate;
import io.github.agileluo.codegenerator.generate.Conf;

public class Example {
	public static void main(String[] args) {
		CodeGenerate.generateCode(User.class, new Conf()
				.enable(Conf.All)
				.disable(Conf.MENU)
				.disable(Conf.STATIC)
				.setDeployDbInfo("localhost", "example", "root", "12345678"));
	}
}
