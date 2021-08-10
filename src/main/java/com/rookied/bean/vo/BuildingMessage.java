package com.rookied.bean.vo;

/**
 * 楼宇信息 json
 * @author zhangqiang
 * @date 2021/8/10
 */
public class BuildingMessage {
    private String buildingCode;
    private Integer unitCount;

    public BuildingMessage() {
    }

    public BuildingMessage(String buildingCode, Integer unitCount) {
        this.buildingCode = buildingCode;
        this.unitCount = unitCount;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public BuildingMessage setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
        return this;
    }

    public Integer getUnitCount() {
        return unitCount;
    }

    public BuildingMessage setUnitCount(Integer unitCount) {
        this.unitCount = unitCount;
        return this;
    }

    @Override
    public String toString() {
        return "BuildingMessage{" +
                "buildingCode='" + buildingCode + '\'' +
                ", unitCount=" + unitCount +
                '}';
    }
}
