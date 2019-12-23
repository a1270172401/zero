package org.io.hydoskyzero.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.io.hydoskyzero.entity.ZeroOrder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderService extends IService<ZeroOrder> {
    /**
     * 查询今日订单数量
     * @return
     */
    Integer getOrderNum();

    /**
     * 查询今日订单总额
     * @return
     */
    BigDecimal getOrderSum();

    /**
     * 查询今日货物总量
     * @return
     */
    Integer getCargoNum();

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
     * 近期订单金额统计
     * @return
     */
    List<Map> getRecentOrderSum();

    /**
     * 城市货运金额统计
     * @return
     */
    List<Map> getCityOrderSum();

    /**
     * 城市货运数量统计
     * @return
     */
    List<Map> getCityCargoNum();
}
