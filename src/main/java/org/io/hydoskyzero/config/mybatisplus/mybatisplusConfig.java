package org.io.hydoskyzero.config.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 念着倒才子傻
 */
@Configuration
@MapperScan("org.io.hydoskyzero.**.mapper.**")
public class mybatisplusConfig {
    // 分页拦截器
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
