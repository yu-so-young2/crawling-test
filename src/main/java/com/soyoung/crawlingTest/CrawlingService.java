package com.soyoung.crawlingTest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CrawlingService {
    private WebDriver webDriver;

    public List<CrawlingInfo> getCrawlingInfos() throws IOException, InterruptedException {
        List<CrawlingInfo> crawlingInfoList = new ArrayList<>();

        String url = "https://toss.tech/";

        log.info("Toss 기술블로그 크롤링 시작");

        System.setProperty("webdriver.chrome.driver", "/Users/soyoung/DevSpace/chromedriver-mac-x64/chromedriver");

        System.out.println(1);
        webDriver = new ChromeDriver();
        System.out.println(2);

        webDriver.get(url);
        System.out.println(3);

        Thread.sleep(1000);

        List<WebElement> postElementList = webDriver.findElements(By.className("css-nsslhm"));
        System.out.println(4);
        List<String> urlLIst = new ArrayList<>();

        for (WebElement item : postElementList) {
            System.out.println(item.toString());
//            String href = item.findElement(By.cssSelector("a")).getAttribute("href");
//            thumbnail_url = item.find_element_by_css_selector('img').get_attribute('src')
//            title = item.find_element_by_css_selector('your_title_selector').text
//            summary = item.find_element_by_css_selector('your_summary_selector').text
//            print(f 'Href: {href}, Thumbnail: {thumbnail_url}, Title: {title}, Summary: {summary}')
//            System.out.println("href = "+href);

        }

        for (WebElement concertEl : postElementList){
            urlLIst.add(concertEl.getAttribute("href"));
        }

        Thread.sleep(10000);

        for (String concertUrl : urlLIst) {
            webDriver.get(concertUrl);

            Thread.sleep(15000);

            WebElement elementTitle = webDriver.findElement(By.cssSelector(".prdTitle"));
            WebElement elementPlace = webDriver.findElement(By.cssSelector(".infoBtn"));
            WebElement elementDate = webDriver.findElement(By.cssSelector(".infoText"));
            String elementTime = getCrawlingTime(webDriver);
            String elementSigner = getCrawlingSinger(webDriver);
            List<String> priceList = getMinMaxPrice(webDriver);

            CrawlingInfo crawlingInfo = CrawlingInfo.builder()
                    .title(elementTitle.getText())
                    .place(elementPlace.getText())
                    .date(elementDate.getText())
                    .singer(elementSigner)
                    .time(elementTime)
                    .maxPrice(priceList.get(0))
                    .minPrice(priceList.get(1))
                    .build();

            crawlingInfoList.add(crawlingInfo);

        }

        webDriver.close();
        webDriver.quit();

        System.out.println("size = "+crawlingInfoList.size());
        for (int i = 0; i < crawlingInfoList.size(); i++) {
            CrawlingInfo crawlingInfo = crawlingInfoList.get(i);
            System.out.println(crawlingInfo.getTitle());
        }

        return crawlingInfoList;
    }

    private String getCrawlingTime(WebDriver webDriver){
        String crawlingTime = "";
        try{
            WebElement webElement =webDriver.findElement(By.cssSelector(".timeTableLabel span"));
            crawlingTime = webElement.getText();
        }
        catch (NoSuchElementException e){
            crawlingTime = "NOT OPENED";
        }

        return crawlingTime;
    }

    private String getCrawlingSinger(WebDriver webDriver){
        String crawlingSinger = "";

        try{
            WebElement webElement = webDriver.findElement(By.cssSelector(".castingName"));

            crawlingSinger = webElement.getText();
        }
        catch (NoSuchElementException e){
            crawlingSinger = "NULL";
        }

        return crawlingSinger;
    }

    private List<String> getMinMaxPrice(WebDriver webDriver){
        List<String> priceList = new ArrayList<>();

        try {
            List<WebElement> elementPriceList = webDriver.findElements(By.cssSelector(".infoPriceItem .price"));

            Optional<String> maxPriceOptional = elementPriceList.stream().map(WebElement::getText).findFirst();
            Optional<String> minPriceOptional= Optional.empty();

            if (elementPriceList.size()==0){
                minPriceOptional = Optional.of("0");
            }
            else {
                minPriceOptional = elementPriceList.stream().skip(elementPriceList.size() - 1).map(WebElement::getText).findFirst();
            }

            priceList.add(maxPriceOptional.orElse("0"));
            priceList.add(minPriceOptional.orElse("0"));
        }
        catch (NoSuchElementException e){
            priceList.add("0");
            priceList.add("0");
        }

        return priceList;

    }
}