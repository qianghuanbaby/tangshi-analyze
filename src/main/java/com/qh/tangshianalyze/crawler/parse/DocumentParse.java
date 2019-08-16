package com.qh.tangshianalyze.crawler.parse;

import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.qh.tangshianalyze.crawler.common.Page;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Author:qh
 * Created:2019/8/4
 */

public class DocumentParse implements Parse{

    @Override
    public void parse(final Page page) {
        if(page.isDetail()){
            return;
        }

        //统计链接个数
        final AtomicInteger count = new AtomicInteger(0);
        HtmlPage htmlPage = page.getHtmlPage();
        htmlPage.getBody()
                .getElementsByAttribute("div","class","typecont")
                .forEach(div-> {
                        //取出超链接
                        List<HtmlElement> aNodeList =
                                div.getHtmlElementsByTagName("a");
                        aNodeList.forEach(aNode ->
                                    {
                                        String path = aNode.getAttribute("href");
//                                        count.getAndIncrement();
//                                        System.out.println(path);
                                        Page subPage = new Page(
                                                page.getBase(),
                                                path,
                                                true
                                        );
                                        page.getSubPage().add(subPage);
                                    }
                         );
                });
//        System.out.println("总共："+count.get()+"地址");
    }
}
