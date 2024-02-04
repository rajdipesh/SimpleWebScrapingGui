package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.function.Consumer;


public class App {

    public static String getDta(String c)throws Exception{
        StringBuffer br=new StringBuffer();

        br.append("<html>" +
                 "<body style='text-align:center;color:red;'>");
        br.append(c.toUpperCase()+"<br>");

        //System.out.println("You are in side getDta");
        String url="https://www.worldometers.info/coronavirus/country/"+ c + "/";
        Document doc= Jsoup.connect(url).get();
        //main counter wrap
        Elements elements =doc.select("#maincounter-wrap");
        //System.out.println(elements.html());
        elements.forEach((e) ->{
            String text=e.select("h1").text();
            String count=e.select(".maincounter-number>span").text();
            //System.out.println(text+" "+count);
            br.append(text).append(" ").append(count).append("<br>");

        });
        br.append("</body>" +
               "</html>");
        return br.toString();

    }

    public static void main(String[] args)throws Exception
    {
        //System.out.println("Hello World!");
        //Scanner sc=new Scanner(System.in);
       //System.out.println("Enter country: ");
        //String co=sc.nextLine();
        //System.out.println(getDta(co));

        //#CREATE GUI
        JFrame root=new JFrame("Details of country");
        root.setSize(500,500);

        Font f=new Font("Poppins",Font.BOLD,30);

        //TextField
        JTextField field=new JTextField();

        //label
        JLabel dataL=new JLabel();
        field.setFont(f);
        dataL.setFont(f);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        dataL.setHorizontalAlignment(SwingConstants.CENTER);

        //Button
        JButton button=new JButton("Get");
        button.setFont(f);

        //LISTNER IN GET
        button.addActionListener((event)->{
            //click
            try {
                String maindata=field.getText();
                String result=getDta(maindata);
                dataL.setText(result);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        //ADD on frame all above
        root.setLayout(new BorderLayout());

        root.add(field,BorderLayout.NORTH);
        root.add(dataL,BorderLayout.CENTER);
        root.add(button,BorderLayout.SOUTH);

        //#For windows visualization
        root.setVisible(true);



    }
}