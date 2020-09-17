package org.io.hydoskyzero.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

/**
 * @author 念着倒才子傻
 */
@Configuration
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstantiationTracingBeanPostProcessor.class);
    /**
     * 在创建bean后输出bean的信息
     * @param bean bean
     * @param beanName bean名称
     * @return bean
     * @throws BeansException Exception
     */
    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean,@NonNull String beanName) throws BeansException {
        LOGGER.info("load bean ==> {}",beanName);
        return bean;
    }
}
