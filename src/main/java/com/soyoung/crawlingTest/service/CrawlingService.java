package com.soyoung.crawlingTest.service;

import com.soyoung.crawlingTest.post.Post;

import java.util.List;

public interface CrawlingService {
    public List<Post> getCrawlingPosts() throws InterruptedException;
}
