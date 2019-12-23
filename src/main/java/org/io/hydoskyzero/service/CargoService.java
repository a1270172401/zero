package org.io.hydoskyzero.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.io.hydoskyzero.entity.ZeroCargo;

import java.util.List;
import java.util.Map;

public interface CargoService extends IService<ZeroCargo> {

    /**
     * 当日实时热门货物品类
     * @return
     */
    List<Map> getHotCargoDetail();

    /**
     * 实时热门货运城市
     * @return
     */
    Map getHotCity();

    /**
     * 全网货运品类排行
     * @return
     */
    List<Map> getCargoType();
}
