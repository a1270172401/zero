package org.io.hydoskyzero.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/**
 * 赣州系数
 */
@Configuration
public class ParameterConfig {
    private static final Logger logger = LoggerFactory.getLogger(ParameterConfig.class);
    private volatile static ParameterConfig parameterConfig;
    public ParameterConfig(){}
     /**
     * 赣州系数
     */
    private static BigDecimal Gan_Zhou;

    /**
     * 绵阳系数
     */
    private static BigDecimal Mian_Yang;

    /**
     * 长沙系数
     */
    private static BigDecimal Chang_Sha;

    /**
     * 兰州系数
     */
    private static BigDecimal Lan_Zhou;

    /**
     * 柳州系数
     */
    private static BigDecimal Liu_Zhou;

    /**
     * 济宁系数
     */
    private static BigDecimal Ji_Ning;

    /**
     * 菏泽系数
     */
    private static BigDecimal He_Ze;

    /**
     * 衡阳系数
     */
    private static BigDecimal Heng_Yang;

    /**
     * 开始生成订单时间
     */
    private static String startTime;

    /**
     * 结束生成订单时间
     */
    private static String endTime;

    /**
     * 赣州
     * @return
     */
    public static String getStartTime() {
        return startTime;
    }
    @Value("${params.start_time}")
    public void setStartTime(String paramProp) {
        ParameterConfig.getParameterConfig().startTime = paramProp;
    }

    /**
     * 赣州
     * @return
     */
    public static String getEndTime() {
        return endTime;
    }
    @Value("${params.end_time}")
    public void setEndTime(String paramProp) {
        ParameterConfig.getParameterConfig().endTime = paramProp;
    }

    /**
     * 赣州
     * @return
     */
    public static BigDecimal getGanZhou() {
        return Gan_Zhou;
    }
    @Value("${params.Gan_Zhou}")
    public void setGanZhou(BigDecimal paramProp) {
        ParameterConfig.getParameterConfig().Gan_Zhou = paramProp;
    }

    /**
     * 绵阳
     * @return
     */
    public static BigDecimal getMianYang() {
        return Mian_Yang;
    }
    @Value("${params.Mian_Yang}")
    public void setMianYang(BigDecimal paramProp) {
        ParameterConfig.getParameterConfig().Mian_Yang = paramProp;
    }

    /**
     * 长沙
     * @return
     */
    public static BigDecimal getChangSha() {
        return Chang_Sha;
    }
    @Value("${params.Chang_Sha}")
    public void setChangSha(BigDecimal paramProp) {
        ParameterConfig.getParameterConfig().Chang_Sha = paramProp;
    }

    /**
     * 兰州
     * @return
     */
    public static BigDecimal getLanZhou() {
        return Lan_Zhou;
    }
    @Value("${params.Lan_Zhou}")
    public void setLanZhou(BigDecimal paramProp) {
        ParameterConfig.getParameterConfig().Lan_Zhou = paramProp;
    }

    /**
     * 柳州
     * @return
     */
    public static BigDecimal getLiuZhou() {
        return Liu_Zhou;
    }
    @Value("${params.Liu_Zhou}")
    public void setLiuZhou(BigDecimal paramProp) {
        ParameterConfig.getParameterConfig().Liu_Zhou = paramProp;
    }

    /**
     * 济宁
     * @return
     */
    public static BigDecimal getJiNing() {
        return Ji_Ning;
    }
    @Value("${params.Ji_Ning}")
    public void setJiNing(BigDecimal paramProp) {
        ParameterConfig.getParameterConfig().Ji_Ning = paramProp;
    }

    /**
     * 菏泽
     * @return
     */
    public static BigDecimal getHeZe() {
        return He_Ze;
    }
    @Value("${params.He_Ze}")
    public void setHeZe(BigDecimal paramProp) {
        ParameterConfig.getParameterConfig().He_Ze = paramProp;
    }
    /**
     * 衡阳
     * @return
     */
    public static BigDecimal getHengYang() {
        return Heng_Yang;
    }
    @Value("${params.Heng_Yang}")
    public void setHengYang(BigDecimal paramProp) {
        ParameterConfig.getParameterConfig().Heng_Yang = paramProp;
    }

    /**
     * 单例模式（懒汉式）
     * @return
     */
    public static ParameterConfig getParameterConfig(){
        if (parameterConfig == null) {
            synchronized (ParameterConfig.class) {
                if (parameterConfig == null) {
                    parameterConfig = new ParameterConfig();
                }
            }
        }
        return parameterConfig;
    }
}
