package org.io.hydoskyzero.util;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 字符串工具类
 * @author 念着倒才子傻
 */
public class Utility {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str.trim()));
    }

    /**
     * object转字符串（String.ValueOf()------->为空的时候会返回 字符串‘null’）
     *
     * @param obj
     * @return
     */
    public static String strValueOf(Object obj) {
        if (obj == null) {
            return null;
        }
        if ("null".equals(obj.toString()) || "undefined".equals(obj.toString()) || "{}".equals(obj.toString()) || "[]".equals(obj.toString()) || "[null]".equals(obj.toString()) || "".equals(obj.toString())) {
            return null;
        }
        return obj.toString();
    }

    /**
     * json字符串转Map
     *
     * @param str
     * @return
     */
    public static Map<String, String> JsonStrToMap(String str) {
        return JSON.parseObject(str, Map.class);
    }

    /**
     * 获取wapper对象
     */
    public static <T> QueryWrapper<T> getQueryWrapper(T entity) {
        return new QueryWrapper(entity);
    }

    /**
     * 获取今日时间 年月日
     */
    public static String getTime(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate time = LocalDate.now();
        String format = df.format(time);
        return  format;
    }

    /**
     * 获取今年年份
     */
    public static String getYear(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy");
        LocalDate time = LocalDate.now();
        String format = df.format(time);
        return  format;
    }

    /**
     * 根据传入的赣州数据,返回所有城市的数据
     * @return
     */
    public static List<Map> getAllCityParams(BigDecimal params){
        List list = new ArrayList();
        String[] city = {"赣州","绵阳","长沙","兰州","柳州","济宁","菏泽","衡阳"};
        BigDecimal[] num = {ParameterConfig.getGanZhou(),ParameterConfig.getMianYang(),ParameterConfig.getChangSha(),
                ParameterConfig.getLanZhou(),ParameterConfig.getLiuZhou(),ParameterConfig.getJiNing(),ParameterConfig.getHeZe(),ParameterConfig.getHengYang()};
        for (int i=0;i<city.length;i++){
            Map map = new HashMap();
            map.put("city",city[i]);
            map.put("value",params.multiply(num[i]).setScale(0,BigDecimal.ROUND_HALF_UP));
            list.add(map);
        }
        return list;
    }

    /**
     * 根据赣州系数获取赣州所占比例
     */
    public static BigDecimal getProp(){
        //获取所有城市系数和
        BigDecimal sum = ParameterConfig.getGanZhou().add(ParameterConfig.getMianYang()).add(ParameterConfig.getChangSha()).add(ParameterConfig.getLanZhou()).
                add(ParameterConfig.getLiuZhou()).add(ParameterConfig.getJiNing()).add(ParameterConfig.getHeZe()).add(ParameterConfig.getHengYang());
        //获取赣州所占比例,取两位小数
        BigDecimal num = ParameterConfig.getGanZhou().divide(sum, 2, RoundingMode.HALF_UP);
        return num;
    }

    /**
     * Java 非小数BigDecimal转换为Integer
     */
    public static Integer getInteger(BigDecimal num){
        if (strValueOf(num)==null) {
            return null;
        }
        String str=num.toString();
        Integer integer=Integer.parseInt(str);
        return integer;
    }

    /**
     * 获取今天前7天的日期 （yyyy-MM-dd）
     */
    public static List<String> getOldDate(){
        String time = getTime();
        ArrayList<String> pastDaysList = new ArrayList<>();
        try {
            //我这里传来的时间是个string类型的,所以要先转为date类型的。
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(time);
            for (int i = 7; i >= 0; i--) {
                if (i==0) {
                    continue;
                }
                pastDaysList.add(getPastDate(i,date));
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return pastDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String result = sdf.format(today);
        return result;
    }

    /**
     * 生成n-m指定数字的随机整数
     * @return
     */
    public static Integer getNum(Integer n ,Integer m){
        if (!(n<m)){
            System.out.println("生成随机数失败,传入数据不正确");
            return null;
        }
        Random rand = new Random();
        //生成n-m随机整数
        Integer num = rand.nextInt(m-n+1)+n;
        return num;
    }


    /**
     * 字符串类型"yyyy-MM-DD"转换为date类型
     * @param date
     * @return
     */
    public static LocalDate getDateOfString(String date){
        try {
            Date result = simpleDateFormat.parse(date);
            Instant instant = result.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDate localDate = instant.atZone(zoneId).toLocalDate();
            return localDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
