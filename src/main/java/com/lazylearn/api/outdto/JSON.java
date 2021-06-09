package com.lazylearn.api.outdto;

import com.lazylearn.api.config.Consts;

/**
 * @author McFly the Kid
 */
public class JSON {

    private String status;

    private String msg;

    public JSON(String status) {
        this.status = status;
    }

    public static JSON ok() {
        return new JSON(Consts.RET_OK);
    }

    public static JSON error() {
        return new JSON(Consts.RET_ERROR);
    }

    public static JSON ok(String msg) {
        JSON json = ok();
        json.setMsg(msg);
        return json;
    }

    public static JSON error(String msg) {
        JSON json = error();
        json.setMsg(msg);
        return json;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
