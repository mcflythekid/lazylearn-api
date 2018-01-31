package com.mcflythekid.lazylearncore.indto;

/**
 * @author McFly the Kid
 */
public class SearchCardInDto extends BootstrapTableInDto {
    public SearchCardInDto(String sort, String order, Integer limit, Integer offset) {
        super(sort, order, limit, offset);
    }

    private String search;
    private String deckId;

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
