package com.soyoung.crawlingTest;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        CrawlingService crawlingService = new CrawlingService();
        crawlingService.getCrawlingInfos();
        System.out.println("hihi");
    }
}
