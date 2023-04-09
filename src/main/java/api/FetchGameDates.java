package api;


import StringUtils.StringHelper;
import content.JsoupHelper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class FetchGameDates {

    public static void main(String[] args) {
        JsoupHelper jsoupHelper = new JsoupHelper();
        StringHelper stringHelper = new StringHelper();
        String page = "https://baden.liga.nu/cgi-bin/WebObjects/nuLigaTENDE.woa/wa/groupPage?championship=B2+S+2023&group=15";
        Connection connection = Jsoup.connect(page);
        Document document = null;

        try {
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String title = jsoupHelper.getTitle(document);
        String team = stringHelper.extractTeam(title);
        System.out.printf("Mannschaft: %s%n", team);

        String table = jsoupHelper.getTable(document);
        System.out.println(table);

    }


}
