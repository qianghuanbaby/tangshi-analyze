package com.qh.tangshianalyze.crawler.parse;

import com.gargoylesoftware.htmlunit.html.*;
import com.qh.tangshianalyze.crawler.common.Page;
import com.qh.tangshianalyze.analyze.entity.PoetryInfo;

/**
 * 详情页面的解析
 * Author:qh
 * Created:2019/8/3
 */

public class DataPageParse implements Parse {
    @Override
    public void parse(final Page page) {
        if(!page.isDetail()){
            return;
        }

        HtmlPage htmlPage = page.getHtmlPage();
        HtmlElement body = htmlPage.getBody();
        //标题
        String titlePath= "//div[@class='cont']/h1/text()";
        DomText titleDom = (DomText) body.getByXPath(titlePath).get(0);
        String title = titleDom.asText();
        //System.out.println(titleDom.asText());

        //朝代
        String dynastyPath = "//div[@class='cont']/p/a[1]";
        HtmlAnchor dynastyDom = (HtmlAnchor) body.getByXPath(dynastyPath).get(0);
        String dynasty = dynastyDom.asText();
        //System.out.println(dynasty);

        //作者
        String authorPath = "//div[@class='cont']/p/a[2]";
        HtmlAnchor authorDom = (HtmlAnchor) body.getByXPath(authorPath).get(0);
        String author = authorDom.asText();
        //System.out.println(author);

        //正文
        String contentPath = "//div[@class='cont']/div[@class='contson']";
//            body.getByXPath(contentPath)
//                    .forEach(o->{
//                        HtmlDivision division = (HtmlDivision) o;
//                        System.out.println(division.asText());
//                        System.out.println("------------------");
//                    });
        HtmlDivision contentDom = (HtmlDivision) body.getByXPath(contentPath).get(0);
        String content = contentDom.asText();
        //System.out.println(content);

        PoetryInfo poetryInfo = new PoetryInfo();
        poetryInfo.setDynasty(dynasty);
        poetryInfo.setAuthor(author);
        poetryInfo.setTitle(title);
        poetryInfo.setContent(content);

        page.getDataSet().putData("title",title);
        page.getDataSet().putData("dynasty",dynasty);
        page.getDataSet().putData("author",author);
        page.getDataSet().putData("content",content);
        //更多的数据
        page.getDataSet().putData("url",page.getUrl());

    }
}
