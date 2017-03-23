package io.github.agileluo.codegenerator.rest.example;

import org.springframework.web.bind.annotation.RequestMapping;

import io.github.agileluo.codegenerator.core.RestDoc;
import io.github.agileluo.codegenerator.core.RestMethodDoc;

@RequestMapping("/user")
@RestDoc
public class UserController {

	@RequestMapping("/get")
	@RestMethodDoc(name="获取用户", desc="根据用户id，获取用户信息", errs={UserNotExistException.class})
	public User getUser(Long id){
		return null;
	}
	
}
