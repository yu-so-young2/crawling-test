package com.soyoung.crawlingTest;

import com.soyoung.crawlingTest.service.CrawlingService;
import com.soyoung.crawlingTest.service.CrawlingServiceFactory;
import com.soyoung.crawlingTest.service.TossCrawlingService;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        String publisher = "TOSS";

        CrawlingServiceFactory crawlingServiceFactory = new CrawlingServiceFactory();
        CrawlingService crawlingService = crawlingServiceFactory.createCrawlingService(publisher);
        crawlingService.getCrawlingPosts();
    }
}
