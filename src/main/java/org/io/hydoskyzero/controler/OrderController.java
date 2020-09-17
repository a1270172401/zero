package org.io.hydoskyzero.controler;

import org.io.hydoskyzero.service.OrderService;
import org.io.hydoskyzero.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 念着倒才子傻
 */
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
    public Result getOrderNum(){
        Integer num = orderService.getOrderNum();
        return Result.data(num);
    }

    /**
     * 查询今日订单总额
     * @return
     */
    @GetMapping("/getOrderSum")
    public Result getOrderSum(){
        BigDecimal num = orderService.getOrderSum();
        return Result.data(num);
    }

    /**
     * 查询今日货物总量
     * @return
     */
    @GetMapping("/getCargoNum")
    public Result getCargoNum(){
        Integer num = orderService.getCargoNum();
        return  Result.data(num);
    }

    /**
     * 历史订单总量
     * @return
     */
    @GetMapping("/getAllOrderNum")
    public Result getAllOrderNum(){
        Integer num = orderService.getAllOrderNum();
        return Result.data(num);
    }

    /**
     * 历史订单总额
     * @return
     */
    @GetMapping("/getAllOrderSum")
    public Result getAllOrderSum(){
        BigDecimal num = orderService.getAllOrderSum();
        return Result.data(num);
    }

    /**
     * 历史货物总量
     * @return
     */
    @GetMapping("/getAllCargoNum")
    public Result getAllCargoNum(){
        Integer num = orderService.getAllCargoNum();
        return Result.data(num);
    }

    /**
     * 近期订单金额统计
     * @return
     */
    @GetMapping("/getRecentOrderSum")
    public Result getRecentOrderSum(){
        List<Map> list = orderService.getRecentOrderSum();
        return Result.data(list);
    }

    /**
     * 城市货运金额统计
     * @return
     */
    @GetMapping("/getCityOrderSum")
    public Result getCityOrderSum(){
        List<Map> list = orderService.getCityOrderSum();
        return Result.data(list);
    }

    /**
     * 城市货运数量统计
     * @return
     */
    @GetMapping("/getCityCargoNum")
    public Result getCityCargoNum(){
        List<Map> list = orderService.getCityCargoNum();
        return Result.data(list);
    }

}
