package com.qh.tangshianalyze.crawler.common;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * 存放公共模块
 * Author:qh
 * Created:2019/8/3
 */

//可以用@getter @setter替换
@Data
public class Page {
    /**
     * 数据网页的根地址
     * 比如：https://so.gushiwen.org
     */


    private final String base;

    /**
     * 具体网页的路径
     * 比如：/shiwenv_45c396367f59.aspx
     */
    private final String path;

    /**
     * 网页Dom对象
     */
    private HtmlPage htmlPage;

    /**
     * 标识网页是否是详情页
     */
    private final boolean detail;


    /**
     * 子页面对象集合
     */
    private Set<Page> subPage = new HashSet<>();

    /**
     * 数据对象
     */
    private DataSet dataSet = new DataSet();

    //详情页的url地址
    public String getUrl(){
        return this.base+this.path;
    }

}
