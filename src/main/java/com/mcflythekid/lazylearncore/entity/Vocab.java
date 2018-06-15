package com.mcflythekid.lazylearncore.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author McFly the Kid
 */
@Entity
@Table(name="vocab", indexes = {
        @Index(columnList = "userId")
})
public class Vocab extends AbstractEntity {

    private String userId;
    @Lob
    private String data1;
    @Lob
    private String data2;
    @Lob
    private String data3;
    @Lob
    private String data4;
    @Lob
    private String data5;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

    public String getData4() {
        return data4;
    }

    public void setData4(String data4) {
        this.data4 = data4;
    }

    public String getData5() {
        return data5;
    }

    public void setData5(String data5) {
        this.data5 = data5;
    }
}
