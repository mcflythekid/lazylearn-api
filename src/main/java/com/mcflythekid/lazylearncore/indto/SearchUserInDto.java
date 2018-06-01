package com.mcflythekid.lazylearncore.indto;

/**
 * @author McFly the Kid
 */
public class SearchUserInDto extends BootstrapTableInDto {
    public SearchUserInDto(String sort, String order, Integer limit, Integer offset) {
        super(sort, order, limit, offset);
    }

    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
