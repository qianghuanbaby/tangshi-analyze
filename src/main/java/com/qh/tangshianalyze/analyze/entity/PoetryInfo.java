package com.qh.tangshianalyze.analyze.entity;

import lombok.Data;

/**
 * Author:qh
 * Created:2019/8/4
 */

@Data
public class PoetryInfo {
    /**
     * 标题
     */
    private String title;
    /**
     * 作者朝代
     */
    private String dynasty;
    /**
     * 作者
     */
    private String author;
    /**
     * 正文
     */
    private String content;
}
