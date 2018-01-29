package com.mcflythekid.lazylearncore.outdto;

import java.util.Collection;

/**
 * @author McFly the Kid
 */
public class BootstrapTableOutDto {

    private Long total = 0L;
    private Collection<?> rows;

    public BootstrapTableOutDto(Collection<?> rows, Long total) {
        this.rows = rows;
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Collection<?> getRows() {
        return rows;
    }

    public void setRows(Collection<?> rows) {
        this.rows = rows;
    }
}
