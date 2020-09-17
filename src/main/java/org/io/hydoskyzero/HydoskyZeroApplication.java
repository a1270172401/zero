package org.io.hydoskyzero;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 念着倒才子傻
 */
@SpringBootApplication(scanBasePackages = "org.io.hydoskyzero.*")
@MapperScan("org.io.hydoskyzero.mapper")
@EnableScheduling
public class HydoskyZeroApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(HydoskyZeroApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(HydoskyZeroApplication.class, args);
		Environment environment = run.getBean(Environment.class);
		LOGGER.info("启动完成,当前端口号为 ==> {}",environment.getProperty("local.server.port"));
	}

}
