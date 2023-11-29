package com.soyoung.crawlingTest.service;

public class CrawlingServiceFactory {
    public CrawlingService createCrawlingService(String publisher){
        CrawlingService crawlingService = null;
        if(publisher.equals("TOSS")) crawlingService = new TossCrawlingService();
        if(publisher.equals("WOOWAHAN")) crawlingService = new WoowahanCrawlingService();

        return crawlingService;
    }
}
