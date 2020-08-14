package com.czm.managed_system.entity;

import com.czm.managed_system.supper.TransformUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author CZM
 * @create 2020/6/25 17:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dormitory {
    /**
     *  主键id
     */
    private int id;

    /**
     * 宿舍名
     */
    private String name;


    /**
     * 状态：0-空闲 1-租用中 -1=维修中
     */

    private int stage;
    /**
     * 展示
     */
    private String stageString;
    /**
     * 类型n---n人间
     */
    private int type;

    /**
     * 租用时间
     */
    private Date startTime;
    /**
     * 展示
     */
    private String startTimeString;

    public void setStage(int stage) {
        this.stage = stage;
        this.stageString = TransformUtil.stageSting(stage);
    }

    public int getStage() {
        return stage;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
        this.startTimeString = TransformUtil.formart(startTime);
    }

}
