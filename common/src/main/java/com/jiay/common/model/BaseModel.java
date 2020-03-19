package com.jiay.common.model;

/**
 * 抽象模型类
 * @author jiay
 * @date 2020-03-17
 */
public abstract class BaseModel {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
