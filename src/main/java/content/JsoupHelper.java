package content;

import org.jsoup.nodes.Document;

import java.util.Arrays;

public class JsoupHelper {

    public String getTitle(Document document) {
        return document.body().getElementById("title").text();
    }

    public String getTable(Document document) {
        String tables = document.body().getElementsByTag("tr").text();
        String [] arr = tables.split(" ");
        String [] table = null;
        System.out.println(tables);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("Datum")) {
                table = Arrays.copyOfRange(arr, i + 7, arr.length);
            }
        }
        for (int i = 0; i < table.length; i++) {
            System.out.println(table[i]);
            //Game game = new Game(table[i]);
        }
        return "";
    }
}
