package com.xupt.tmp.config;

public class PageUtils {
    public static final int defaultPageNum = 1;
    public static final int defaultPageSize = 10;

    public static boolean checkPageInfo(int pageNum, int pageSize) {
        if (pageNum < defaultPageNum) {
            return false;
        }
        if (pageSize < defaultPageSize) {
            return false;
        }
        return true;
    }
}
