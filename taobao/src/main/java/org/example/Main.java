package org.example;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/*public class Main{
    public static void main(String[]args)throws Exception {
        /*String url="https://www.taobao.com/";
        HttpClient httpClient= HttpClient.newBuilder().build();
        HttpRequest request=HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(5))
                .build();
        try{
            HttpResponse<String> response=httpClient.send(request,HttpResponse.BodyHandlers.ofString());
            Document document=Jsoup.parse(response.body());
            output(response.body());
            Elements elements = document.select("span.info-wrapper-title-text");
            int n=0;
            for (Element element : elements) {
                n++;
                System.out.println(n+element.text() + "\n");
            }
            //System.out.println(document);
        }catch(Exception e){
            e.printStackTrace();
        }*/
        /*System.setProperty("webdriver.chrome.driver", "/users/zhangshiqi/Desktop/chrome-mac-arm64");
        WebDriver driver = new EdgeDriver();
        driver.get("https://s.taobao.com/search?localImgKey=&page=1&pvid=d0f2ec2810bcec0d5a16d5283ce59f70&q=连衣裙&refpid=430148_1006&source=tbsy&spm=a21bo.jianhua%2Fa.201856-fline.2.5e392a89CrrDCE&style=grid&tab=all");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //driver.findElement(By.cssSelector(".next-tabs-tab-inner")).click();


        /*JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 8; i++) {
            js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/


        /*try {
            String html = driver.getPageSource();
            //Document document = Jsoup.connect("https://www.taobao.com/").timeout(5000).get();
            //Document document = Jsoup.parse(html);
            //Elements elements = document.select("span.info-wrapper-title-text");
            //Elements elements = document.select(".Card--doubleCard--wznk5U4");

            Workbook workbook=new XSSFWorkbook();
            Sheet sheet= workbook.createSheet("Scraped data");
            Row headerRow= sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Title");
            headerRow.createCell(1).setCellValue("Price");
            headerRow.createCell(2).setCellValue("Image URL");
            headerRow.createCell(3).setCellValue("Product Link");
            headerRow.createCell(4).setCellValue("Shop Name");
            headerRow.createCell(5).setCellValue("Location");
            headerRow.createCell(6).setCellValue("Type");
            headerRow.createCell(7).setCellValue("Sales Count");

            int n = 0;
            int rowNum=1;
            int maxRages=30;
            StringBuilder sb = new StringBuilder();

            // 点击“销量”按钮
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement salesSortButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".SortBar--customTabItem--YnxmQgr[data-spm-click*='locaid=d2;name=销量']")));
            salesSortButton.click();

            // 等待页面加载完成
            Thread.sleep(5000);
            for(int i=0;i<maxRages;i++) {
                Document document = Jsoup.parse(html);
                Elements elements = document.select(".Card--doubleCard--wznk5U4");
                for (Element element : elements) {

                    Row row = sheet.createRow(rowNum++);
                    // 提取标题
                    String title = element.select(".Title--title--jCOPvpf").text();
                    // 提取价格
                    String price = element.select(".Price--priceInt--ZlsSi_M").text() + element.select(".Price--priceFloat--h2RR0RK").text();
                    // 提取图片链接
                    String imageUrl = element.select("img.MainPic--mainPic--rcLNaCv").attr("src");
                    // 提取商品链接
                    String productLink = "https:" + element.attr("href");
                    // 提取店铺名称
                    String shopName = element.select(".ShopInfo--shopName--rg6mGmy").text();
                    // 提取店铺所在地
                    String location = element.select(".Price--procity--_7Vt3mX span").text();
                    //  提取类型
                    String type = element.select(".Abstract--text--qexNC18").text();
                    // 提取购买人数
                    String selosCount = element.select(".Price--realSales--FhTZc7U").text();
                    // 商品链接
                    //String pLink = element.select(".Card--doubleCardWrapper--L2XFE73").text();
                    String itemLink = element.parent().attr("href");
                    if (!itemLink.startsWith("http")) {
                        itemLink = "https:" + itemLink;
                    }

                    // 将数据写入Excel
                    row.createCell(0).setCellValue(title);
                    row.createCell(1).setCellValue(price);
                    row.createCell(2).setCellValue(imageUrl);
                    row.createCell(3).setCellValue(productLink);
                    row.createCell(4).setCellValue(shopName);
                    row.createCell(5).setCellValue(location);
                    row.createCell(6).setCellValue(type);
                    row.createCell(7).setCellValue(selosCount);
                    row.createCell(8).setCellValue(itemLink);

                    n++;
                    sb.append(n + " " + element.text() + "\n");
                    System.out.println(n + " " + element.text() + "\n");
                }
                output(sb.toString());
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement nextPageButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".next-btn.next-medium.next-btn-normal.next-pagination-item.next-next"))); // 替换为实际的下一页按钮选择器
                if (nextPageButton != null) {
                    nextPageButton.click();
                    Thread.sleep(10000); // 等待页面加载
                } else {
                    break; // 如果没有下一页按钮，则退出循环
                }
            }

            try(FileOutputStream fileOut = new FileOutputStream("/users/zhangshiqi/desktop/pp.xlsx")){
                workbook.write(fileOut);
            }
            workbook.close();

            driver.close();
            //System.out.println(document);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void output(String n)throws Exception{
        try(BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/users/zhangshiqi/desktop/pp.txt")))){
            bufferedWriter.write(n);
        }
    }

}*/

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/users/zhangshiqi/Desktop/chrome-mac-arm64");
        WebDriver driver = new EdgeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://s.taobao.com/search?localImgKey=&page=1&pvid=d0f2ec2810bcec0d5a16d5283ce59f70&q=连衣裙&refpid=430148_1006&source=tbsy&spm=a21bo.jianhua%2Fa.201856-fline.2.5e392a89CrrDCE&style=grid&tab=all");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Scraped data");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Title");
            headerRow.createCell(1).setCellValue("Price");
            headerRow.createCell(2).setCellValue("Image URL");
            headerRow.createCell(3).setCellValue("Product Link");
            headerRow.createCell(4).setCellValue("Shop Name");
            headerRow.createCell(5).setCellValue("Location");
            headerRow.createCell(6).setCellValue("Type");
            headerRow.createCell(7).setCellValue("Sales Count");

            int rowNum = 1;
            int maxPages = 30;
            Set<String> processedTitles = new HashSet<>();

            // 点击"销量"按钮
            WebElement salesSortButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".SortBar--customTabItem--YnxmQgr[data-spm-click*='locaid=d2;name=销量']")));
            salesSortButton.click();

            // 等待页面加载完成
            Thread.sleep(5000);

            for (int i = 0; i < maxPages; i++) {
                // 等待商品卡片加载
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".Card--doubleCard--wznk5U4")));

                List<WebElement> productCards = driver.findElements(By.cssSelector(".Card--doubleCard--wznk5U4"));

                for (int j = 0; j < productCards.size(); j++) {
                    try {
                        // 重新获取元素以避免StaleElementReferenceException
                        WebElement card = driver.findElements(By.cssSelector(".Card--doubleCard--wznk5U4")).get(j);

                        String title = card.findElement(By.cssSelector(".Title--title--jCOPvpf")).getText();

                        if (!processedTitles.add(title)) {
                            continue; // 跳过已处理的商品
                        }

                        Row row = sheet.createRow(rowNum++);

                        // 提取价格
                        String price = card.findElement(By.cssSelector(".Price--priceInt--ZlsSi_M")).getText() +
                                card.findElement(By.cssSelector(".Price--priceFloat--h2RR0RK")).getText();

                        // 提取图片链接


                        // 提取商品链接
                        String itemLink = card.findElement(By.cssSelector("a")).getAttribute("href");

                        // 提取店铺名称
                        String shopName = card.findElement(By.cssSelector(".ShopInfo--shopName--rg6mGmy")).getText();

                        // 提取店铺所在地
                        String location = card.findElement(By.cssSelector(".Price--procity--_7Vt3mX span")).getText();

                        // 提取类型
                        String type = "";
                        try {
                            type = card.findElement(By.cssSelector(".Abstract--text--qexNC18")).getText();
                        } catch (NoSuchElementException e) {
                            // 如果没有找到类型，就留空
                        }

                        // 提取购买人数
                        String salesCount = card.findElement(By.cssSelector(".Price--realSales--FhTZc7U")).getText();

                        // 将数据写入Excel
                        row.createCell(0).setCellValue(title);
                        row.createCell(1).setCellValue(price);
                        row.createCell(2).setCellValue("imageUrl");
                        row.createCell(3).setCellValue(itemLink);
                        row.createCell(4).setCellValue(shopName);
                        row.createCell(5).setCellValue(location);
                        row.createCell(6).setCellValue(type);
                        row.createCell(7).setCellValue(salesCount);

                        System.out.println(rowNum + " " + title + " | " + price + " | " + salesCount);
                    } catch (StaleElementReferenceException e) {
                        System.out.println("Stale element, retrying...");
                        j--; // 重试当前元素
                    }
                }

                if (i < maxPages - 1) {
                    try {
                        WebElement nextPageButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".next-btn.next-medium.next-btn-normal.next-pagination-item.next-next")));
                        nextPageButton.click();
                        Thread.sleep(5000); // 等待页面加载
                    } catch (Exception e) {
                        System.out.println("No more pages or error clicking next page");
                        break;
                    }
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream("/users/zhangshiqi/desktop/pp.xlsx")) {
                workbook.write(fileOut);
            }

            System.out.println("Data has been written to taobao_products.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
