import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.*;

public class WikiGame {
// tryna make a layout
    private JFrame mainFrame;
    private int WIDTH=800;
    private int HEIGHT=700;


    private int maxDepth;
    private ArrayList<String> path = new ArrayList<>();
    private HashSet badLinks = new HashSet();
    // Sets frame dimensions



    public static void main(String[] args) {
        WikiGame w = new WikiGame();
    }

    public WikiGame() {
        prepareGUI();

        String startLink = "https://en.wikipedia.org/wiki/List_of_The_Vampire_Diaries_characters#Elena_Gilbert";  // beginning link, where the program will start
        String endLink = "https://en.wikipedia.org/wiki/Steven_R._McQueen";    // ending link, where the program is trying to get to
        maxDepth = 3;           // start this at 1 or 2, and if you get it going fast, increase

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

        //print path (for loop)

        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println(path.get(i));
        }

    }

    private void prepareGUI() {

        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(2,1));
        mainFrame.getContentPane().setBackground(Color.black);


        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
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
                URL url = new URL(startLink);

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

                                path.add(link2);
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