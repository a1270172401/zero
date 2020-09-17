package org.io.hydoskyzero.controler;

import org.io.hydoskyzero.service.CargoService;
import org.io.hydoskyzero.service.impl.QuartzOrderImpl;
import org.io.hydoskyzero.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 念着倒才子傻
 */
@RestController
@RequestMapping("/cargo")
public class CargoController {
    @Autowired
    private CargoService cargoService;

    @Autowired
    QuartzOrderImpl quartzOrder;
    /**
     * 当日实时热门货物品类
     * @return
     */
    @GetMapping("/getHotCargoDetail")
    public Result getHotCargoDetail(){
        List<Map> list= cargoService.getHotCargoDetail();
        return Result.data(list);
    }

    /**
     * 实时热门货运城市
     * @return
     */
    @GetMapping("/getHotCity")
    public Result getHotCity(){
        Map list = cargoService.getHotCity();
        return Result.data(list);
    }

    /**
     * 全网货运品类排行
     * @return
     */
    @GetMapping("/getCargoType")
    public Result getCargoType(){
        List<Map> list = cargoService.getCargoType();
        return Result.data(list);
    }
}
