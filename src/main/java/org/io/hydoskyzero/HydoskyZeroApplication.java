package org.io.hydoskyzero;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 念着倒才子傻
 */
@SpringBootApplication(scanBasePackages = "org.io.hydoskyzero.*")
@MapperScan("org.io.hydoskyzero.mapper")//标记扫描的mapper位置
@EnableScheduling
public class HydoskyZeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(HydoskyZeroApplication.class, args);
	}

}
