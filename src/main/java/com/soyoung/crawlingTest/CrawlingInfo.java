package com.soyoung.crawlingTest;

import lombok.Builder;
import lombok.Data;

@Data
public class CrawlingInfo {
    private String title;
    private String place;
    private String date;
    private String singer;
    private String time;
    private String maxPrice;
    private String minPrice;

    @Builder
    public CrawlingInfo(String title, String place, String date, String singer, String time, String maxPrice, String minPrice) {
        this.title = title;
        this.place = place;
        this.date = date;
        this.singer = singer;
        this.time = time;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }
}