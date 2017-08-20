/**
 * @Title:Application.java
   @Package cn.itcast.springboot
   @Description:TODO
   @author myl
   @date 2017年8月18日下午7:21:30
   @version V1.0


*/
package cn.itcast.springboot;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.introspector.BeanAccess;

/**
 * @author Administrator
 *
 */
@SpringBootApplication(scanBasePackages={"cn.itcast.springboot"})  //加上这个注解就说明springboot应用的运行主类
public class Application {

	public static void main(String[] args) {
		/*//运行springboot的应用
		SpringApplication.run(Application.class, args);*/
		//创建springApplication的应用的对象
		SpringApplication springApplication = new SpringApplication(Application.class);
		//设置横幅模式的关闭
		springApplication.setBannerMode(Mode.OFF);
		springApplication.run(args);
	}
}
