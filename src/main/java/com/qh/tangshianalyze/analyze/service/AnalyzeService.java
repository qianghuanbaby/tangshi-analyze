package com.qh.tangshianalyze.analyze.service;

import com.qh.tangshianalyze.analyze.model.AuthorCount;
import com.qh.tangshianalyze.analyze.model.WordCount;

import java.util.List;

/**
 * Author:qh
 * Created:2019/8/6
 */

public interface AnalyzeService {
    /**
     * 分析唐诗中作者的创作数量
     * @return
     */
    List<AuthorCount> analyzeAuthorCount();

    /**
     * 词云分析
     * @return
     */
    List<WordCount> analyzeWordCloud();
}
