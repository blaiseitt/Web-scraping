package pl.buarzej;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SongScrapper {

    public static void main(String[] args) {

        String url = "https://www.empik.com/bestsellery/ksiazki/informatyka";

        try {
            Document document = Jsoup.connect(url).get();
            Elements songs = document.select(".search-list-item-hover");
            System.out.println("==========================");
            System.out.println("Songs - Web Scrapper");
            System.out.println("==========================");

            String title = songs.getFirst().select(".product-title > a").first().attr("title");
            String author = songs.getFirst().select(".smartAuthorWrapper").text();

            System.out.println(title);
            System.out.println(author);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
