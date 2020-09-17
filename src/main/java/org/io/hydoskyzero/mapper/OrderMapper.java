package org.io.hydoskyzero.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.io.hydoskyzero.entity.ZeroOrder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author 念着倒才子傻
 */
@Repository(value = "org.io.hydoskyzero.mapper.OrderMapper")
@Component
public interface OrderMapper extends BaseMapper<ZeroOrder> {
    /**
     * 获取今日订单数量
     * @param time
     * @return
     */
    Integer getOrderNum(@Param("time") String time);

    /**
     * 获取传入日期的订单总额
     * @param time
     * @return
     */
    BigDecimal getOrderSum(@Param("time")String time);

    /**
     * 查询今日货物数量
     * @param time
     * @return
     */
    Integer getCargoNum(@Param("time")String time);

    /**
     * 历史订单总量
     * @return
     */
    Integer getAllOrderNum();

    /**
     * 历史订单总额
     * @return
     */
    BigDecimal getAllOrderSum();

    /**
     * 历史货物总量
     * @return
     */
    Integer getAllCargoNum();

    /**
     * 获取指定年度的总金额
     * @param year
     * @return
     */
    BigDecimal getCityOrderSum(@Param("year") String year);

    /**
     * 获取指定年度的城市货运数量
     * @param year
     * @return
     */
    Integer getCityCargoNum(@Param("year")String year);

    /**
     * 查询今日是否有数据
     * @return
     */
    String selectIsNone(@Param("time") String date);
}
