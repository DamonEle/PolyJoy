package com.damon43.polyjoy.bean;

/**
 * Created by Administrator on 2017/5/2.
 */

public class NewsRecordBean {
    private String type;
    private int count;

    public NewsRecordBean(String type, int count) {
        this.type = type;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

}
