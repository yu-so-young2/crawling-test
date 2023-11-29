package com.soyoung.crawlingTest.post;

import lombok.Builder;
import lombok.Data;

@Data
public class Post {
    private String title;
    private String description;
    private String thumbnailUrl;
    private String postUrl;
    private String regDate;
    private String publisher;

    @Builder
    public Post(String title, String description, String thumbnailUrl, String postUrl, String regDate, String publisher) {
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.postUrl = postUrl;
        this.regDate = regDate;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", postUrl='" + postUrl + '\'' +
                ", regDate='" + regDate + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}