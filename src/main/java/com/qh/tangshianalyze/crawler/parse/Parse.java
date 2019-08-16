package com.qh.tangshianalyze.crawler.parse;

import com.qh.tangshianalyze.crawler.common.Page;

/**
 * Author:qh
 * Created:2019/8/3
 */

//解析
public interface Parse {
    /*
    解析页面
     */
    void parse(final Page page);
}
