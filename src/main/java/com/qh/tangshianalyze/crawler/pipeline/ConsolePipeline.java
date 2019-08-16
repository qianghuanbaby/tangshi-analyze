package com.qh.tangshianalyze.crawler.pipeline;

import com.qh.tangshianalyze.crawler.common.Page;

import java.util.Map;

/**
 * Author:qh
 * Created:2019/8/3
 */

public class ConsolePipeline implements Pipeline {

    @Override
    public void pipeline(Page page) {
        Map<String,Object> data=page.getDataSet().getData();
        //存储
        System.out.println(data);
    }
}
