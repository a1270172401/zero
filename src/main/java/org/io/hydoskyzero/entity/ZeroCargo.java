package org.io.hydoskyzero.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ZeroCargo implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 货物类型
     */
    private String cargoType;

    /**
     * 货物详情
     */
    private String cargoDetail;

    /**
     * 货物日期
     */
    private LocalDate cargoDate;

    /**
     * 货物类型总和
     */
    private String cargoTypeAll;
}
