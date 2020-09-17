package org.io.hydoskyzero.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.io.hydoskyzero.entity.ZeroCargo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 念着倒才子傻
 */
@Component
@Repository(value = "org.io.hydoskyzero.mapper.CargoMapper")
public interface CargoMapper extends BaseMapper<ZeroCargo> {
    String getHotCargoDetail(@Param("time") String time);

    String getCargoType(@Param("time")String time);

    String getTodyCargoType(@Param("time")String time);
}
