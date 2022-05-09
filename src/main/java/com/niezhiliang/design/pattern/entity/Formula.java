package com.niezhiliang.design.pattern.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author niezhiliang
 * @version v
 * @date 2022/5/7 17:01
 * 大数据公式实体类
 */
@Data
public class Formula implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long stationId;
    private Long monitoredObjectId;
    private String metricCode;
    private String statPointId;
    private String parameterPointIds;
    private String constants;
    private Integer newBillDay;
    private Integer lastBillDay;
    private String className;
    private String method;
    private Boolean isRealtime;
    private Long versionTime = System.currentTimeMillis() / 1000L;
    private Float tag;
}
