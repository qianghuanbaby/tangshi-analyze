package com.qh.tangshi;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.qh.tangshianalyze.analyze.entity.PoetryInfo;

import java.io.IOException;

/**
 * Author:qh
 * Created:2019/8/3
 */

public class TestHtmlUnit {
    public static void main(String[] args) {
        try(WebClient webClient = new WebClient(BrowserVersion.CHROME)){
            //下面这行代码主要就是保证在body里面禁用js文件，但是我的不禁用也可以输出
            webClient.getOptions().setJavaScriptEnabled(false);

            HtmlPage htmlPage = webClient.getPage("https://so.gushiwen.org/shiwenv_d1e07474d2e2.aspx");

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
            System.out.println(dynasty);

            //作者
            String authorPath = "//div[@class='cont']/p/a[2]";
            HtmlAnchor authorDom = (HtmlAnchor) body.getByXPath(authorPath).get(0);
            String author = authorDom.asText();
            System.out.println(author);

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
            System.out.println(content);

            PoetryInfo poetryInfo = new PoetryInfo();
            poetryInfo.setDynasty(dynasty);
            poetryInfo.setAuthor(author);
            poetryInfo.setTitle(title);
            poetryInfo.setContent(content);

        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
