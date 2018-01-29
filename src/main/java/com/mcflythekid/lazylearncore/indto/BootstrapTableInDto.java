package com.mcflythekid.lazylearncore.indto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author McFly the Kid
 */
public class BootstrapTableInDto {

    private String sort;
    private String order;
    private Integer limit = 100;
    private Integer offset = 0;

    public Pageable getPageable(){
        int page = offset / limit;
        return new PageRequest(page, limit, Sort.Direction.fromString(sort), order);
    }

    public BootstrapTableInDto(String sort, String order, Integer limit, Integer offset) {
        this.sort = sort;
        this.order = order;
        this.limit = limit;
        this.offset = offset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
