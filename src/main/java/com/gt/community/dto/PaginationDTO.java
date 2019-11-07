package com.gt.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuesstionDTO> questions;
    private boolean showPrevious;//上一页
    private boolean showFirsPage;//首页
    private boolean showNext;//下一页
    private boolean showEndPage;//到尾页
    private Integer page;//当前页
    private List<Integer> pages = new ArrayList<>();//展示的页码素数组
    private Integer totaPage;//分几页

    public void setPagination(Integer totaPage, Integer page) {
        this.totaPage = totaPage;
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totaPage) {
                pages.add(page + i);
            }
        }
        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == totaPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        //是否展示到首页
        if (pages.contains(1)) {
            showFirsPage = false;
        } else {
            showFirsPage = true;
        }
        //是否展示到尾页
        if (pages.contains(totaPage)||totaPage==0) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
