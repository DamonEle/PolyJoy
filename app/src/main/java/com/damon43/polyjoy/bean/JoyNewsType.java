package com.damon43.polyjoy.bean;

import java.io.Serializable;

/**
 * @author damonmasty
 *         Created on 上午11:21 17-2-6.
 */

public class JoyNewsType implements Serializable{
    private String typeName;
    private String typeId;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
