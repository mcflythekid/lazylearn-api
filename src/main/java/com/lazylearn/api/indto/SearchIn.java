package com.lazylearn.api.indto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author McFly the Kid
 */
public class SearchIn {

    public Pageable getPageable() {
        int page = offset / limit;
        return new PageRequest(page, limit, Sort.Direction.fromString(order), sort);
    }

    private String search;
    private String sort;
    private String order;
    private Integer limit;
    private Integer offset;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
