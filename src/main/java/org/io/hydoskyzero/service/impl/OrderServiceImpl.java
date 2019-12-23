package org.io.hydoskyzero.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.io.hydoskyzero.entity.ZeroOrder;
import org.io.hydoskyzero.mapper.OrderMapper;
import org.io.hydoskyzero.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.io.hydoskyzero.util.Utility.*;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,ZeroOrder> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询今日订单数量
     * @return
     */
    @Override
    public Integer getOrderNum() {
        //获取今日日期
        String time = getTime();
        //查询赣州今日订单数量
        Integer num = orderMapper.getOrderNum(time);
        //获取全国今日订单数量 赣州的数量/赣州所占比例
        BigDecimal result = new BigDecimal(num).divide(getProp(),0, RoundingMode.HALF_UP);
        return getInteger(result);
    }

    /**
     * 查询今日订单总额
     * @return
     */
    @Override
    public BigDecimal getOrderSum() {
        //获取今日日期
        String time = getTime();
        //查询今日订单总额
        BigDecimal num = orderMapper.getOrderSum(time);
        //获取全国今日订单总额 赣州的数量/赣州所占比例
        BigDecimal result = num.divide(getProp(),0, RoundingMode.HALF_UP);
        return result;
    }

    /**
     * 查询今日货物总量
     * @return
     */
    @Override
    public Integer getCargoNum() {
        //获取今日日期
        String time = getTime();
        //查询今日货物总量
        Integer num = orderMapper.getCargoNum(time);
        //获取全国今日货物总量 赣州的数量/赣州所占比例
        BigDecimal result = new BigDecimal(num).divide(getProp(),0, RoundingMode.HALF_UP);
        return getInteger(result);
    }

    /**
     * 历史订单总量
     * @return
     */
    @Override
    public Integer getAllOrderNum() {
        Integer num = orderMapper.getAllOrderNum();
        //获取全国历史订单总量 赣州的数量/赣州所占比例
        BigDecimal result = new BigDecimal(num).divide(getProp(),0, RoundingMode.HALF_UP);
        return getInteger(result);
    }

    /**
     * 历史订单总额
     * @return
     */
    @Override
    public BigDecimal getAllOrderSum() {
        BigDecimal num = orderMapper.getAllOrderSum();
        //获取全国历史订单总额 赣州的数量/赣州所占比例
        BigDecimal result = num.divide(getProp(),0, RoundingMode.HALF_UP);
        return result;
    }

    /**
     * 历史货物总量
     * @return
     */
    @Override
    public Integer getAllCargoNum() {
        Integer num = orderMapper.getAllCargoNum();
        //获取全国历史货物总量 赣州的数量/赣州所占比例
        BigDecimal result = new BigDecimal(num).divide(getProp(),0, RoundingMode.HALF_UP);
        return getInteger(result);
    }

    /**
     * 近期订单金额统计（今天之前7天）
     * @return
     */
    @Override
    public  List<Map> getRecentOrderSum() {
        //获取前面七天的日期
        List<String> date = getOldDate();
        //初始化返回数据
        List <Map> result = new ArrayList<Map>();
        for (String time:date){
            Map map = new HashMap();
            //获取传入日期的订单总额
            BigDecimal sum = orderMapper.getOrderSum(time);
            map.put("date",time);
            map.put("sum",sum == null?0:sum.divide(getProp(),0, RoundingMode.HALF_UP));
            result.add(map);
        }
        return result;
    }


    /**
     * 城市货运金额统计(本年度)
     * @return
     */
    @Override
    public List<Map> getCityOrderSum() {
        //获取今年年份
        String year = getYear();
        //获取今年赣州数据
        BigDecimal sum = orderMapper.getCityOrderSum(year);
        //获取所有城市数据
        return getAllCityParams(sum);
    }

    /**
     * 城市货运数量统计（本年度）
     * @return
     */
    @Override
    public List<Map> getCityCargoNum() {
        //获取今年年份
        String year = getYear();
        //获取今年赣州数据
        Integer sum = orderMapper.getCityCargoNum(year);
        //获取所有城市数据
        return getAllCityParams(new BigDecimal(sum));
    }
}
