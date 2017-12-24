package com.agile.framework.listener;

import com.agile.framework.persistence.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Entity监听器.
 *
 * @author linweixuan@gmial.com
 * @version 1.0
 *
 */
public class EntityListener {

    /**
     * 保存前处理
     *
     * @param entity 基类
     */
    @PrePersist
    public void prePersist(BaseEntity entity) {
        //entity.setCreateDate(new Date());
        //entity.setModifyDate(new Date());
    }

    /**
     * 更新前处理
     *
     * @param entity 基类
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        //entity.setModifyDate(new Date());
    }

}