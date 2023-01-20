import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class wikiHTML {



    public static void main(String[] args) {
        ArrayList<String> badLinks = new ArrayList<String>();
        badLinks.add("/wiki/Ian_Somerhalder");
        badLinks.add("/wiki/Main_Page");
        badLinks.add("/wikipedia/commons/6/65/Lock-green.svg");


        try {
            System.out.println();
            URL url = new URL("https://en.wikipedia.org/wiki/Ian_Somerhalder");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("/wiki/")){
                    int start = line.indexOf("/wiki");
                    int enddouble = line.indexOf("\"", start);

                    String link = line.substring(start, enddouble);

                    if (! link.contains(":") && ! link.contains("identifier")) {
                        System.out.println(link);
                    }

                }

            }
        }catch(Exception e){

        }
    }

}

