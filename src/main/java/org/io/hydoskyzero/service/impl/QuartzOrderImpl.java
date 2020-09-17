package org.io.hydoskyzero.service.impl;

import org.io.hydoskyzero.entity.ZeroCargo;
import org.io.hydoskyzero.entity.ZeroOrder;
import org.io.hydoskyzero.mapper.CargoMapper;
import org.io.hydoskyzero.mapper.OrderMapper;
import org.io.hydoskyzero.service.QuartzOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.time.LocalDate.now;
import static org.io.hydoskyzero.util.Utility.*;


/**
 * 定时生成订单类
 * @author 念着倒才子傻
 */
@Service
public class QuartzOrderImpl implements QuartzOrder {
    @Autowired
    private CargoMapper cargoMapper;

    @Autowired
    private OrderMapper orderMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzOrderImpl.class);



    /**
     * 调用该方法,查询今日是否生成过订单,
     * 若生成过则修改订单,若没生成则新增一条数据
     */
//    @Scheduled(cron="0/1 * * * * ?")    //表示每过一秒执行一次该方法
    @Transactional
    public Map getAnOrder(){
        //判断是否往下执行
        Boolean isPlay = play();
        if (!isPlay) {
            return null;  //不执行
        }
        System.out.println("自动执行生成订单方法。。。");
        //获取今日时间
        String date = getTime();
        //查询今日是否有数据,如果有则返回该数据的id
        String result =exists();
        //初始化返回值
        Map map = new HashMap();
        if (strValueOf(result)!=null){
            //存在数据,直接修改
            map = updateOrder(result);
        }else {
            //不存在数据,新增
            map = insertOrder();
        }
        return map;
    }

    /**
     * 1.判断时间点是否执行（上午8点到中午12点可执行）
     * 2.根据随机数确定是否执行（确定10-20秒执行一次）
     * @return
     */
    public Boolean play(){
        if (!isEffectiveDate()){return false;}   //如果不在规定时间内不生成
        Integer integer = getNum(1,100);    //获取一个随机数（如果在0-10,生成一条订单,如果不在则不生成）
        System.out.println("生成的随机数"+integer);
        if (integer<10){
            return true;
        }
        return false;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间,注意时间格式要一致
     *
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss");
            //获得现在的时分秒
            Date b = new Date();
            DateFormat df3 = DateFormat.getTimeInstance();//只显示出时分秒
            String time = df3.format(b);
            Date nowTime = sdf.parse(time);
            Date startTime = sdf.parse("08:00:00"); //读取yml中的参数
            Date endTime = sdf.parse("12:00:00");
            if (nowTime.getTime() == startTime.getTime()
                    || nowTime.getTime() == endTime.getTime()) {
                return true;
            }

            Calendar date = Calendar.getInstance();
            date.setTime(nowTime);

            Calendar begin = Calendar.getInstance();
            begin.setTime(startTime);

            Calendar end = Calendar.getInstance();
            end.setTime(endTime);

            if (date.after(begin) && date.before(end)) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       return  false;
    }


    /**
     * 修改生成订单数据
     * @return
     */
    public Map updateOrder(String id){
        //初始化返回值
        Map map = new HashMap();
        ZeroOrder zeroOrder = new ZeroOrder();
        zeroOrder.setId(id);
        ZeroOrder zer = orderMapper.selectOne(getQueryWrapper(zeroOrder));
        Integer cargoNum = getNum(3,5);//每个订单随机3-5件货物
        BigDecimal orderSum = new BigDecimal(getNum(7,10)*cargoNum);//每件货物7-10元 货物数量X货物件数
        zer.setOrderNum(zer.getOrderNum()+1);//订单数量增加1
        zer.setCargoNum(zer.getCargoNum()+cargoNum);//货物总量增加
        //获取订单金额
        BigDecimal sum = strValueOf(zeroOrder.getOrderSum())==null? new BigDecimal(0):zeroOrder.getOrderSum();
        zer.setOrderSum(sum.add(orderSum));//总金额增加
        int result = orderMapper.update(zer,getQueryWrapper(zeroOrder));
        //修改货物数量
        int cargo =updateCargo(id,cargoNum);
        System.out.println("修改订单的状态----"+result);
        if (result==1 &&cargo==1){
            map.put("status","1");
            map.put("msg","修改订单成功");
            return map;
        }else{
            map.put("status","-1");
            map.put("msg","修改订单失败");
            return map;
        }
    }

    /**
     * 根据传入货物数量随机生成货物类型
     * @param id
     * @param num
     * @return
     */
    public Integer updateCargo(String id,Integer num){
        //查询今日货物对象
        ZeroCargo zeroCargo = new ZeroCargo();
        zeroCargo.setId(id);
        ZeroCargo resutCargo = cargoMapper.selectOne(getQueryWrapper(zeroCargo));
        System.out.println("查询到的货物对象-----"+resutCargo);
        //获取查询到的货物详情
        String detail = resutCargo.getCargoDetail();
        //将查询到的货物详情拆分为数组
        String[] intger = detail.split(",");

        System.out.println(intger[30]);
        //获取到查询到的货物品类
        String zerotype = resutCargo.getCargoType();
        //将查询到的货物品类拆分为数组
        String[] typeInteger = zerotype.split(",");
        //几件货物则随机生成几次货物品类
        for (int i =0;i<num;i++){
            //获取随机生成的货物详情
            Integer cargodetail = getNum(0,30);
            System.out.println("生成的货物详情为————————》"+cargodetail);
            //将生成的新的货物详情数量放入数组
            Arrays.fill(intger,cargodetail,cargodetail+1,(Integer.parseInt(intger[cargodetail])+1)+"");
            //根据生成的货物详情返回货物的类型
            Integer type = getType(cargodetail);
            //将生成的货物详情存入数组
            Arrays.fill(typeInteger,type,type+1,(Integer.parseInt(typeInteger[type])+1)+"");
        }
        //将修改过的数据存入数据库
        ZeroCargo zeroCargoNew = resutCargo;
        zeroCargoNew.setCargoDetail(ArryToString(intger));
        zeroCargoNew.setCargoType(ArryToString(typeInteger));
        int result = cargoMapper.update(zeroCargoNew,getQueryWrapper(zeroCargo));
        return result;
    }


    /**
     * 数组转换为逗号拼接的字符串
     * @param strings
     * @return
     */
    public static String ArryToString(String[] strings){
        String str = "";
        for (int i=0;i<strings.length;i++){
            if (i==strings.length-1){
                str = str+strings[i];
            }else {
                str = str + strings[i]+",";
            }
        }
        return str;
    }

    /**
     * 根据货物详情,返回货物品类
     货物详情：
     货物类型为01时：（01汽车配件,02汽车用品,03汽保工具,04汽车电器,05汽车整车,06摩电整车）
     货物类型为02时：（01五金工具,02机械设备,03日用五金,04水暖五金,05建筑五金）
     货物类型为03时：（01卫浴家装,02建材配件,03安防建材,04厨房家装,05办公家具,06户外家具,07成套家具）
     货物类型为04时：（01家用电器,02日化用品,03日杂百货,04小家电,05针织家纺,06窗帘布艺,07玩具益智,08文体用品）
     货物类型为05时：（01箱包皮具,02图书音像,03服装饰品）
     货物类型为06时：（01农机农资）
     货物类型为07时：（01粮油副食）
     * @return
     */
    public Integer getType(Integer detail){
        if (0<=detail&&detail<=5) {
            return 0;
        }
        if (6<=detail&&detail<=10) {
            return 1;
        }
        if (11<=detail&&detail<=17) {
            return 2;
        }
        if (18<=detail&&detail<=25) {
            return 3;
        }
        if (26<=detail&&detail<=28) {
            return 4;
        }
        if (detail==29) {
            return 5;
        }
        if (detail==30) {
            return 6;
        }
        return null;
    }

    /**
     * 生成今日订单数据
     * @return
     */
    public Map insertOrder(){
        Map map = new HashMap();
        //获取订单和货物id
        String id =  UUID.randomUUID().toString().replace("-", "").toLowerCase();
        System.out.println("生成的id========"+id);
        //生成今日订单数据
        ZeroOrder zeroOrder = new ZeroOrder();
        System.out.println("时间======"+now());
        zeroOrder.setOrderDate(now());
        zeroOrder.setId(id);
        int order = orderMapper.insert(zeroOrder);
        //生成今日货物数据
        ZeroCargo zeroCargo = new ZeroCargo();
        //生成货物详情初始化数据
        String CargoDetail = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
        //生成今日货物类型数据
        String CaegoType= "0,0,0,0,0,0,0";
        //返回全网货物类型的数量和(昨日生成量+昨日所保存的总量)
        String cargoTypeAll = getCargoTypeAll();
        zeroCargo.setId(id);
        zeroCargo.setCargoType(CaegoType);
        zeroCargo.setCargoDetail(CargoDetail);
        zeroCargo.setCargoDate(now());
        zeroCargo.setCargoTypeAll(cargoTypeAll);
        int cargo = cargoMapper.insert(zeroCargo);
        if (order==1&&cargo==1){
            map.put("status","1");
            map.put("msg","生成订单成功");
            return map;
        }else{
            map.put("status","-1");
            map.put("msg","生成订单失败");
            return map;
        }
    }


    /**
     * 获取全网货物品类排行
     * @param
     * @return
     */
    public String getCargoTypeAll(){
        //获取昨日日期
        String date = getPastDate(1,new Date());
        //查询昨日订单id
        String result = orderMapper.selectIsNone(date);
        if (strValueOf(result)==null) {
            return "0,0,0,0,0,0,0";
        }
        //根据日期查询数据
        ZeroCargo zeroCargo = new ZeroCargo();
        zeroCargo.setId(result);
        ZeroCargo zeroCargoyestoday = cargoMapper.selectOne(getQueryWrapper(zeroCargo));
        //查询昨日生成的订单类型
        String yestody = zeroCargoyestoday.getCargoType();
        //获取昨日的单类型加上总归的订单总和
        String all = zeroCargoyestoday.getCargoTypeAll();
        //获取总和并返回
        String typeAll = getTypeAll(yestody.split(","),all.split(","));
       return typeAll;
    }

    /**
     * 获取两个相同大小数组的各个位置的和,并且返回字符串
     * @return String
     */
    public static String getTypeAll(String[]stringy,String[]stringa){
        String[] rsult = new String[7];
        for (int i=0;i<stringy.length;i++){
            rsult[i]= (Integer.parseInt(stringa[i])+Integer.parseInt(stringy[i]))+"";
        }
        return ArryToString(rsult);
    }

    /**
     * 判断今天是否存在数据
     * 不存在返回null 存在返回订单主键
     * @return
     */
    public String exists(){
        //获取今天日期
        String date = getTime();
        System.out.println(date);
        //查询今天是否存在数据
//        ZeroOrder zeroOrder = new ZeroOrder();
//        zeroOrder.setOrderDate(getDateOfString(date));//获取当日的时间
        String result = orderMapper.selectIsNone(date);
        System.out.println(result);
        if (strValueOf(result) == null) {
            return null;
        } else {
            return result;
        }
    }

}
