package fastvagas.util;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtil {

    private final List<Integer> pages;
    private final Boolean hasNextPage;
    private final Boolean hasPreviousPage;

    public PaginationUtil(Long count, Integer page) {
        this.pages = new ArrayList<>();

        for (int i=0; i<count; i++) {
            this.pages.add(i+1);
        }

        this.hasNextPage = this.pages.contains(page+1);
        this.hasPreviousPage = page > 1;
    }

    public static Integer getOffset(Integer pageSize, Integer page) {
        return (page - 1) * pageSize;
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
