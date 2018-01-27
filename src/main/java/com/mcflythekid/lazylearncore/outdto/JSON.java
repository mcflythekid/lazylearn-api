package com.mcflythekid.lazylearncore.outdto;

import com.mcflythekid.lazylearncore.Const;

/**
 * @author McFly the Kid
 */
public class JSON {

    private String status;

    public JSON(String status) {
        this.status = status;
    }

    public static JSON ok(){
        return new JSON(Const.RET_OK);
    }

    public static JSON error(){
        return new JSON(Const.RET_ERROR);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
