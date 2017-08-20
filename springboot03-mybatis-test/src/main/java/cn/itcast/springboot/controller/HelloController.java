/**
 * @Title:HelloController.java
   @Package cn.itcast.springboot.controller
   @Description:TODO
   @author myl
   @date 2017年8月18日下午7:43:51
   @version V1.0


*/
package cn.itcast.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 *
 */
@Controller  //RestController其实就是@Controller和@ResponseBody注解加在一起
//@RestController相当于对全文的方法都设定了@ResponseBody注解，返回的是json视图。
//但是@Controller和@ResponseBody在一起，只对@ResponseBody注解下的方法限制返回的是json，没有的就返回视图
public class HelloController {
	@Autowired//注入环境的类
	private Environment environment;
	// http:localhost:8080/hello
	//只需要@value就可以获取name  value 的值
	/*@Value("${name}")
	private String name;
	@Value("${url}")
	private String url;*/
	@GetMapping("/hello")
	@ResponseBody
	public String hello(){
		/*System.out.println(environment.getProperty("name"));
		System.out.println(environment.getProperty("url"));
		System.out.println(name);
		System.out.println(url);*/
		return "hello世纪";
		}
		@GetMapping("/user")
		public String user(){
			return "/user/user.html";
	}
}
