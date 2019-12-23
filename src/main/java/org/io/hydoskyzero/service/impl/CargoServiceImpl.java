package org.io.hydoskyzero.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.io.hydoskyzero.entity.ZeroCargo;
import org.io.hydoskyzero.mapper.CargoMapper;
import org.io.hydoskyzero.service.CargoService;
import org.io.hydoskyzero.util.ParameterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.io.hydoskyzero.util.Utility.getProp;
import static org.io.hydoskyzero.util.Utility.getTime;

@Service
public class CargoServiceImpl extends ServiceImpl<CargoMapper,ZeroCargo> implements CargoService {
    @Autowired
    private CargoMapper cargoMapper;

    /**
     * 当日实时热门货物品类
     * @return
     */
    @Override
    public List<Map> getHotCargoDetail() {
        String time = getTime(); //获取今日时间(年月日)
        String  cargoDetail= cargoMapper.getHotCargoDetail(time);
        //根据查询到的热门货物,返回数量和名字
        List<Map> result = getTodayCargoDetail(cargoDetail);
        return result;
    }


    /**
     * 返回货物详情Map
     * @param cargoDetail
     * @return
     */
    public List<Map> getTodayCargoDetail(String cargoDetail){
        //初始化返回值
        List<Map> list = new ArrayList();
        //获取货物详情的数组
        String[] cargo = cargoDetail.split(",");
        for (int i= 0;i<cargo.length;i++){
            Map map = new HashMap();
            map.put("name",cargos.values()[i]); //从枚举类中获取名字
            map.put("num",addZeroCargo(Integer.parseInt(cargo[i])));
            list.add(map);
        }
        return list;
    }

    /**
     * 实时热门货运城市
     * @return
     */
    @Override
    public Map getHotCity() {
        //返回城市和其所占比例
       Map result = HotCity();
        return result;
    }

    /**
     * 返回热门城市和其所占比例
     * @return map
     */
    public Map HotCity(){
        Map map = new HashMap();
        map.put("Gan_Zhou", ParameterConfig.getGanZhou());
        map.put("Chang_Sha", ParameterConfig.getChangSha());
        map.put("Lan_Zhou", ParameterConfig.getLanZhou());
        map.put("Liu_Zhou", ParameterConfig.getLiuZhou());
        map.put("Ji_Ning", ParameterConfig.getJiNing());
        map.put("He_Ze", ParameterConfig.getHeZe());
        map.put("Heng_Yang", ParameterConfig.getHengYang());
        return map;
    }

    /**
     * 全网货运品类排行
     * @return
     */
    @Override
    public List<Map> getCargoType() {
        String time = getTime(); //获取今日时间(年月日)
        //获取今天之前的货物总量
        String  cargoType= cargoMapper.getCargoType(time);
        //获取今日生成的货物量
        String  todyCargoType= cargoMapper.getTodyCargoType(time);
        //根据查询到货物类型,返回数量和名字
        List<Map> result = getCargoTypes(cargoType,todyCargoType);
        return result;
    }


    /**
     * 返回类型Map
     * @param cargoDetail
     * @return
     */
    public List<Map> getCargoTypes(String cargoDetail,String todyCargoType) {
        //初始化返回值
        List<Map> list = new ArrayList();
        //获取货物类型的数组
        String[] cargo = cargoDetail.split(",");
        String[] cargotody = todyCargoType.split(",");
        for (int i = 0; i < cargo.length; i++) {
            Map map = new HashMap();
            map.put("name", cargostype.values()[i]); //从枚举类中获取名字
            map.put("num", addZeroCargo(Integer.parseInt(cargo[i])+Integer.parseInt(cargotody[i])));
            list.add(map);
        }
        return list;
    }

    /**
     * 货物类型枚举
     */
    enum cargostype{
        汽摩配件,五金机电,建材家居,日用百货,服装鞋包,农资农机,粮油副食
    }


        /**
         * 传入货物数量,根据赣州比例生成总货物数量
         *
         * @param num
         */;
    public Integer addZeroCargo(Integer num){
        //获取赣州所占比例
        BigDecimal prop = getProp();
        //获取全国数
        BigDecimal all= new BigDecimal(num).divide(prop,0, RoundingMode.HALF_UP);
        return Integer.parseInt(all.toString());
    }
    /**
     * 货物详情：
     01汽车配件,02汽车用品,03汽保工具,04汽车电器,05汽车整车,06摩电整车,
     07五金工具,08机械设备,09日用五金,10水暖五金,11建筑五金,12卫浴家装,
     13建材配件,14安防建材,15厨房家装,16办公家具,17户外家具,18成套家具,
     19家用电器,20日化用品,21日杂百货,22小家电,23针织家纺,24窗帘布艺,
     25玩具益智,26文体用品,27箱包皮具,28图书音像,29服装饰品,30农机农资,
     31粮油副食
     */
    enum cargos{
        汽车配件,汽车用品,汽保工具,汽车电器,汽车整车,摩电整车,五金工具,机械设备,日用五金,水暖五金,建筑五金,卫浴家装,建材配件,安防建材,厨房家装,办公家具,户外家具,成套家具,
        家用电器,日化用品,日杂百货,小家电,针织家纺,窗帘布艺,玩具益智,文体用品,箱包皮具,图书音像,服装饰品,农机农资,粮油副食

    }
}
