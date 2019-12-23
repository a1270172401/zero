package org.io.hydoskyzero.controler;

import org.io.hydoskyzero.service.CargoService;
import org.io.hydoskyzero.service.QuartzOrder;
import org.io.hydoskyzero.service.impl.QuartzOrderImpl;
import org.io.hydoskyzero.util.Rsult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.io.hydoskyzero.util.Utility.getAllCityParams;

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
    public Rsult getHotCargoDetail(){
        List<Map> list= cargoService.getHotCargoDetail();
        return Rsult.data(list);
    }

    /**
     * 实时热门货运城市
     * @return
     */
    @GetMapping("/getHotCity")
    public Rsult getHotCity(){
        Map list = cargoService.getHotCity();
        return Rsult.data(list);
    }

    /**
     * 全网货运品类排行
     * @return
     */
    @GetMapping("/getCargoType")
    public Rsult getCargoType(){
        List<Map> list = cargoService.getCargoType();
        return Rsult.data(list);
    }
}
