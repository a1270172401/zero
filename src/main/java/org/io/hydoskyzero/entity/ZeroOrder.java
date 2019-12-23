package org.io.hydoskyzero.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ZeroOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 城市
     */
    private String city;

    /**
     * 订单数量
     */
    private Integer orderNum;

    /**
     * 订单金额
     */
    private BigDecimal orderSum;

    /**
     * 货物数量
     */
    private Integer cargoNum;

    /**
     * 订单日期
     */
    private LocalDate orderDate;
}
