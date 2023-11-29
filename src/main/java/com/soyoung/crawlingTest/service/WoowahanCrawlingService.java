package com.soyoung.crawlingTest.service;

import com.soyoung.crawlingTest.post.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WoowahanCrawlingService implements CrawlingService {
    private WebDriver webDriver;

    public List<Post> getCrawlingPosts() throws InterruptedException {
        List<Post> crawlingPostList = new ArrayList<>();

        String url = "https://techblog.woowahan.com/";

        log.info("우아한형제들 기술블로그 크롤링 시작");

        System.setProperty("webdriver.chrome.driver", "/Users/soyoung/DevSpace/chromedriver-mac-x64/chromedriver");

        webDriver = new ChromeDriver();
        System.out.println("...Chrome webDriver 생성 완료");

        System.out.println("...url 가져오는 중");
        webDriver.get(url);

        System.out.println("...sleep 1000ms");
        Thread.sleep(1000);

//        List<WebElement> postElementList = webDriver.findElement(By.className("content")).findElement(By.className("content-wrap")).findElement(By.className("posts")).findElements(By.className("item"));
        List<WebElement> postElementList = webDriver.findElement(By.className("content")).findElement(By.className("content-wrap")).findElement(By.className("posts")).findElements(By.xpath("//div[@class='item' and not(position()=1)]"));
        System.out.println("...post list 가져오기 완료");

        for (WebElement item : postElementList) {
            String postUrl = item.findElement(By.tagName("a")).getAttribute("href");
            String thumbnailUrl = null;
            String title = item.findElement(By.tagName("h1")).getText();
            String description = item.findElement(By.tagName("p")).getText();
            String regDate = item.findElement(By.tagName("span")).getText();

            Post post = Post.builder().postUrl(postUrl).thumbnailUrl(thumbnailUrl).title(title).description(description).regDate(regDate).publisher("우아한형제들").build();
            crawlingPostList.add(post);

        }

        webDriver.close();
        webDriver.quit();

        crawlingPostList.stream().forEach(System.out::println);

        return crawlingPostList;
    }
}