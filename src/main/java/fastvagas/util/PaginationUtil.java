package fastvagas.util;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtil {

    private final List<Integer> pages;
    private final Boolean hasNextPage;
    private final Boolean hasPreviousPage;
    public static final Integer PAGE_SIZE = 10;

    public PaginationUtil(Long count, Integer page) {
        this.pages = new ArrayList<>();

        int maxPages = count.intValue() / PaginationUtil.PAGE_SIZE;
        if ((maxPages * PaginationUtil.PAGE_SIZE < count.intValue())) {
            maxPages++;
        }

        for (int i=1; i<=maxPages; i++) {
            this.pages.add(i);
        }

        this.hasNextPage = this.pages.contains(page+1);
        this.hasPreviousPage = page > 1;
    }

    public static Integer getOffset(Integer page) {
        return (page - 1) * PAGE_SIZE;
    }

    public Boolean getHasNextPage() {
        return hasNextPage;
    }

    public Boolean getHasPreviousPage() {
        return hasPreviousPage;
    }

    public List<Integer> getPages() {
        return pages;
    }
}
