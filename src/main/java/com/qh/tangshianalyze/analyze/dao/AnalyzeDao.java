package com.qh.tangshianalyze.analyze.dao;

import com.qh.tangshianalyze.analyze.entity.PoetryInfo;
import com.qh.tangshianalyze.analyze.model.AuthorCount;

import java.util.List;

/**
 * Author:qh
 * Created:2019/8/6
 */

public interface AnalyzeDao {
    /**
     *
     * 分析唐诗中作者的创作数量
     * @return
     */
    List<AuthorCount> analyzeAuthorCount();

    /**
     * 查询所有的诗文进行业务分析
     * @return
     */
    List<PoetryInfo> queryAllPoetryInfo();
}
