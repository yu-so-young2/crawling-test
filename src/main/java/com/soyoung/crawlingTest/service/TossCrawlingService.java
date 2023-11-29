package com.soyoung.crawlingTest.service;

import com.soyoung.crawlingTest.post.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TossCrawlingService implements CrawlingService {
    private WebDriver webDriver;

    public List<Post> getCrawlingPosts() throws InterruptedException {
        List<Post> crawlingPostList = new ArrayList<>();

        String url = "https://toss.tech/";

        log.info("Toss 기술블로그 크롤링 시작");

        System.setProperty("webdriver.chrome.driver", "/Users/soyoung/DevSpace/chromedriver-mac-x64/chromedriver");

        webDriver = new ChromeDriver();
        System.out.println("...Chrome webDriver 생성 완료");

        System.out.println("...url 가져오는 중");
        webDriver.get(url);

        System.out.println("...sleep 1000ms");
        Thread.sleep(1000);

        List<WebElement> postElementList = webDriver.findElements(By.className("css-15qqv3q"));
        System.out.println("...post list 가져오기 완료");

        for (WebElement item : postElementList) {
            String postUrl = item.getAttribute("href");
            String thumbnailUrl = item.findElement(By.tagName("img")).getAttribute("srcset");
            String title = item.findElement(By.className("typography--h3")).getText();
            String description = item.findElement(By.className("typography--h7")).getText();
            String regDate = item.findElement(By.className("typography--p")).getText();

            Post post = Post.builder().postUrl(postUrl).thumbnailUrl(thumbnailUrl).title(title).description(description).regDate(regDate).publisher("TOSS").build();
            crawlingPostList.add(post);

        }

        webDriver.close();
        webDriver.quit();

        crawlingPostList.stream().forEach(System.out::println);

        return crawlingPostList;
    }
}