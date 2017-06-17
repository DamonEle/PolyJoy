package com.damon43.polyjoy.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/4/23.
 */
@Entity
public class RecordableBean {
    @Id
    private Long id;
    @Property(nameInDb = "type")
    private String type;
    @Property(nameInDb = "recordDate")
    private long recoedDate;

    public RecordableBean(String type, long recoedDate) {
        this.type = type;
        this.recoedDate = recoedDate;
    }

    public RecordableBean() {
    }

    @Generated(hash = 1605748383)
    public RecordableBean(Long id, String type, long recoedDate) {
        this.id = id;
        this.type = type;
        this.recoedDate = recoedDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getRecoedDate() {
        return this.recoedDate;
    }

    public void setRecoedDate(long recoedDate) {
        this.recoedDate = recoedDate;
    }

}
