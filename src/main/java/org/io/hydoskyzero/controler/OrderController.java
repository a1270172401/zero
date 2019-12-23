package org.io.hydoskyzero.controler;

import org.io.hydoskyzero.service.OrderService;
import org.io.hydoskyzero.util.Rsult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * 查询今日订单数量
     * @return Rsult
     */
    @GetMapping("/getOrderNum")
    public Rsult getOrderNum(){
        Integer num = orderService.getOrderNum();
        return Rsult.data(num);
    }

    /**
     * 查询今日订单总额
     * @return
     */
    @GetMapping("/getOrderSum")
    public Rsult getOrderSum(){
        BigDecimal num = orderService.getOrderSum();
        return Rsult.data(num);
    }

    /**
     * 查询今日货物总量
     * @return
     */
    @GetMapping("/getCargoNum")
    public Rsult getCargoNum(){
        Integer num = orderService.getCargoNum();
        return  Rsult.data(num);
    }

    /**
     * 历史订单总量
     * @return
     */
    @GetMapping("/getAllOrderNum")
    public Rsult getAllOrderNum(){
        Integer num = orderService.getAllOrderNum();
        return Rsult.data(num);
    }

    /**
     * 历史订单总额
     * @return
     */
    @GetMapping("/getAllOrderSum")
    public Rsult getAllOrderSum(){
        BigDecimal num = orderService.getAllOrderSum();
        return Rsult.data(num);
    }

    /**
     * 历史货物总量
     * @return
     */
    @GetMapping("/getAllCargoNum")
    public Rsult getAllCargoNum(){
        Integer num = orderService.getAllCargoNum();
        return Rsult.data(num);
    }

    /**
     * 近期订单金额统计
     * @return
     */
    @GetMapping("/getRecentOrderSum")
    public Rsult getRecentOrderSum(){
        List<Map> list = orderService.getRecentOrderSum();
        return Rsult.data(list);
    }

    /**
     * 城市货运金额统计
     * @return
     */
    @GetMapping("/getCityOrderSum")
    public Rsult getCityOrderSum(){
        List<Map> list = orderService.getCityOrderSum();
        return Rsult.data(list);
    }

    /**
     * 城市货运数量统计
     * @return
     */
    @GetMapping("/getCityCargoNum")
    public Rsult getCityCargoNum(){
        List<Map> list = orderService.getCityCargoNum();
        return Rsult.data(list);
    }

}
