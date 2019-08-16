package com.qh.tangshianalyze;


import com.qh.tangshianalyze.config.ObjectFactory;
import com.qh.tangshianalyze.crawler.Crawler;
import com.qh.tangshianalyze.web.WebController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 唐诗分析程序的主类
 * Author:qh
 * Created:2019/8/2
 */

public class TangShiAnalyzeApplication {
    private static final Logger LOGGER =  LoggerFactory.getLogger(TangShiAnalyzeApplication.class);
    public static void main(String[] args) {

        WebController webController = ObjectFactory.getInstance().getObject(WebController.class);

        //运行了web服务，提供了接口
        LOGGER.info("Web Server launch...");
        webController.launch();

        //启动爬虫
        if(args.length==1 && args[0].equals("run-crawler")){
            Crawler crawler = ObjectFactory.getInstance().getObject(Crawler.class);
            LOGGER.info("Crawler started...");
            crawler.start();
        }

    }
}
