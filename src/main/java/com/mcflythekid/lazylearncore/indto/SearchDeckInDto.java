package com.mcflythekid.lazylearncore.indto;

/**
 * @author McFly the Kid
 */
public class SearchDeckInDto extends BootstrapTableInDto {
    public SearchDeckInDto(String sort, String order, Integer limit, Integer offset) {
        super(sort, order, limit, offset);
    }

    private String search;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
