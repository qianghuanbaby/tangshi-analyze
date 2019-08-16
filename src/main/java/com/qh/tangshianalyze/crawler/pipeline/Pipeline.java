package com.qh.tangshianalyze.crawler.pipeline;

import com.qh.tangshianalyze.crawler.common.Page;

/**
 * Author:qh
 * Created:2019/8/3
 */

//清洗
public interface Pipeline {
    void pipeline(final Page page);
}
