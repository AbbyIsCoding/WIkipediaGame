import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;

public class WikiGame {

    private int maxDepth;
    private ArrayList<String> path = new ArrayList<>();
    private HashSet badLinks = new HashSet();

    public static void main(String[] args) {
        WikiGame w = new WikiGame();
    }

    public WikiGame() {

        String startLink = "https://en.wikipedia.org/wiki/Ian_Somerhalder";  // beginning link, where the program will start
        String endLink = "https://en.wikipedia.org/wiki/The_Vampire_Diaries";    // ending link, where the program is trying to get to
        maxDepth = 2;           // start this at 1 or 2, and if you get it going fast, increase

        // put array list with things that we don't want
        badLinks.add("/wiki/Ian_Somerhalder");
        badLinks.add("/wiki/Main_Page");
        badLinks.add("/wikipedia/commons/6/65/Lock-green.svg");
        badLinks.add("/wiki/Terms_of_Use");
        badLinks.add("/wiki/Privacy_policy");
        badLinks.add("/wiki/Cookie_statement");




        if (findLink(startLink, endLink, 0)) {
            System.out.println("found it********************************************************************");
            path.add(startLink);
        } else {
            System.out.println("did not find it********************************************************************");
        }

    }

    // recursion method
    public boolean findLink(String startLink, String endLink, int depth) {

        System.out.println("depth is: " + depth + ", link is: " + startLink);

        // BASE CASE
        if (startLink.equals(endLink)) {
            return true;

        } else if (depth == maxDepth) {
            return false;

        }

        // GENERAL RECURSIVE CASE
        else {

            try {
                System.out.println();
                URL url = new URL("https://en.wikipedia.org/wiki/Ian_Somerhalder");

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(url.openStream())
                );
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("/wiki/")){
                        int start = line.indexOf("/wiki/");
                        int enddouble = line.indexOf("\"", start);

                        String link =  line.substring(start, enddouble);

                        if (! link.contains(":") && ! link.contains("identifier") && !badLinks.contains(link)) {

//                            System.out.println(link);
                            String link2 = "https://en.wikipedia.org" + link;
//                            System.out.println("the link is: " + link2);
                            if (findLink(link2, endLink,depth + 1)) {

                                return true;
                            }
                        }

                    }

                }
            }catch(Exception e){
                System.out.println(e);
            }


        }

        return false;
    }

}