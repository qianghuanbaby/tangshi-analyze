package com.qh.tangshianalyze.web;

import com.google.gson.Gson;
import com.qh.tangshianalyze.analyze.model.AuthorCount;
import com.qh.tangshianalyze.analyze.model.WordCount;
import com.qh.tangshianalyze.analyze.service.AnalyzeService;
import com.qh.tangshianalyze.config.ObjectFactory;
import com.qh.tangshianalyze.crawler.Crawler;
import spark.ResponseTransformer;
import spark.Route;
import spark.Spark;

import java.util.List;

/**
 * Web API
 * 1. Sparkjava 框架完成Web API开发
 * 2. Servlet 技术实现Web API
 * 3. Java-Httpd 实现Web API
 * (纯Java语言实现的Web服务)
 * Socket Http协议非常清楚
 *
 * Author:qh
 * Created:2019/8/6
 */

public class WebController {

    private final AnalyzeService analyzeService;

    public WebController(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    //->http://127.0.0.1:4567/
    //->analyze/author_count
    private List<AuthorCount> analyzeAuthorCount(){
        return analyzeService.analyzeAuthorCount();
    }

    //->http://127.0.0.1:4567/
    //->analyze/word_cloud
    private List<WordCount> analyzeWordCount(){
        return analyzeService.analyzeWordCloud();
    }

    public void launch(){

        ResponseTransformer transformer = new JSONResponseTransformer();
        //src/main/resource/static
        //前端静态文件的目录
        Spark.staticFileLocation("/static");
        Spark.get("/analyze/author_count", (request, response) -> analyzeAuthorCount(),
                transformer);

        //服务端接口
        Spark.get("/analyze/word_cloud", (request, response) -> analyzeWordCount(),
                transformer);

        Spark.get("/crawler/stop",((request, response) ->{
            Crawler crawler = ObjectFactory.getInstance().getObject(Crawler.class);
            crawler.stop();
            return "爬虫停止";
        }));
    }

    public static class JSONResponseTransformer implements ResponseTransformer{

        //Object->String
        private  Gson gson = new Gson();


        @Override
        public String render(Object o) throws Exception {
            return gson.toJson(o);
        }

    }
}
