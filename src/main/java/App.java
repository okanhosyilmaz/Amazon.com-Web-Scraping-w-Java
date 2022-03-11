import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class App {
    static Document document;
    static Matcher matcher;
    static Pattern pattern;

    public static void main (String[] args){

        //List<String> productUrl = new ArrayList<>();
        String rawURL = "https://www.amazon.com/s?k=earbuds&crid=10BM6SH1F6P8R&sprefix=earbuds%2Caps%2C285&ref=nb_sb_noss_2";
        //productUrl.add(rawURL);
        int i = 0;
        int n = 5;

        for (; i<n ; i++){

            try {
                document = Jsoup.connect(rawURL).get();
            }
            catch (IOException e){
                e.printStackTrace();
            }

            Elements href = document.select("a[class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']");
            for (Element link : href){
                String linkText = link.attr("href");
                try {
                    String URL = "https://www.amazon.com" + linkText;
                    document = Jsoup.connect(URL).get();

                    Element productName = document.getElementById("productTitle");
                    Element productImage = document.getElementById("landingImage");
                    Element productAsin = document.getElementById("ASIN");
                    Elements productValue = document.select("span[class='a-price a-text-price a-size-medium apexPriceToPay']>span[class='a-offscreen']");
                    Element sellerName = document.getElementById("sellerProfileTriggerId");
                    Element ratingScore = document.getElementById("acrCustomerReviewText");
                    Elements shipsFrom = document.select("div[tabular-attribute-name='Ships from']>div[class='tabular-buybox-text a-spacing-none']>span");
                    Elements inStock = document.getElementsByClass("a-size-medium a-color-success");


                    System.out.println("Name of the Product: " + productName.text());
                    System.out.println("Url of Product: " + URL);
                    System.out.println("Product Image Source: " + productImage.attr("src"));
                    System.out.println("Product Asin Code: " + productAsin.attr("value"));
                    System.out.println("Product Rating Score: " + ratingScore.text());
                    System.out.println("---------------------------------------------------------------------------");


                    if (inStock.text().equals("In Stock.")) {
                       System.out.println("Procut Has Stock");
                       System.out.println("Product Value: " + productValue.text());
                       System.out.println("Product Ships From: " + shipsFrom.text());
                        if (sellerName == null) {
                           System.out.println("Seller Name: Undefined");
                           System.out.println("---------------------------------------------------------------------------");
                        }
                        else {
                           System.out.println("Seller Name: " + sellerName.text());
                           System.out.println("----------------------------------------------------------------------------");
                        }
                    }
                    //Elements clickNext = document.select("a[class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']");
                    //String clickElement = clickNext.attr("href");
                    //rawURL ="https://www.amazon.com" + clickElement;
                    //productUrl.add(rawURL);

                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }


    }

}
