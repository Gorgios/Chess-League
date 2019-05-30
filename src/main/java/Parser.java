

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Parser {
    private SAXReader reader;
    private Document document;

    public Parser(String filePath) throws DocumentException {
        reader= new SAXReader();
        this.document=reader.read(filePath);
    }


    public void createTeam(String id, String name, int rank, String firstCoachFirstName, String firstCoachLastName, String analCoachFirstName,
                           String analCoachLastName, String youngCoachFirstName, String youngCoachLastName, String street, String city, String country) throws IOException {
        List<Node> nodes = document.selectNodes("//chessLeague/teams");
        for (Node node : nodes) {
            if (node instanceof Element) {
                Element e = (Element) node;
                Element team = e.addElement("team");
                team.addAttribute("id", id);
                Element tName = team.addElement("name");
                tName.setText(name);
                Element tRank = team.addElement("rank");
                tRank.setText(Integer.toString(rank));
                Element tStaff = team.addElement("staff");
                Element tFirstCoach = tStaff.addElement("first_coach");
                Element tFirstName = tFirstCoach.addElement("firstname");
                tFirstName.setText(firstCoachFirstName);
                Element tLastName = tFirstCoach.addElement("lastname");
                tLastName.setText(firstCoachLastName);
                Element tAnalCoach = tStaff.addElement("analising_coach");
                Element tFirstName2 = tAnalCoach.addElement("firstname");
                tFirstName2.setText(analCoachFirstName);
                Element tLastName2 = tAnalCoach.addElement("lastname");
                tLastName2.setText(analCoachLastName);
                Element tYoungCoach = tStaff.addElement("young_coach");
                Element tFirstName3 = tYoungCoach.addElement("firstname");
                tFirstName3.setText(youngCoachFirstName);
                Element tLastName3 = tYoungCoach.addElement("lastname");
                tLastName3.setText(youngCoachLastName);
                Element tAdress = team.addElement("address");
                Element tStreet = tAdress.addElement("street");
                tStreet.setText(street);
                Element tCity = tAdress.addElement("city");
                tCity.setText(city);
                Element tCountry = tAdress.addElement("country");
                tCountry.setText(country);
            }
        }
        print();
    }
    public List<Node> getTeams(){
        List<Node> nodes = document.selectNodes("/chessLeague/teams/team");
        int a=1;
        for( Node node: nodes){
            System.out.println(a+". TeamID: "+node.valueOf("@id")
                    + "  Name: " + node.selectSingleNode("name").getText()
                    + "  Rank: " + node.selectSingleNode("rank").getText()
                    +"   FirstCoach : "+node.selectSingleNode("staff/first_coach/firstname").getText()+node.selectSingleNode("staff/first_coach/lastname").getText()
                    +"   Street: "+node.selectSingleNode("address/street").getText()
                    +"   City: "+ node.selectSingleNode("address/city").getText()
                    +"   Country: "+node.selectSingleNode("address/country").getText());
            a++;
        }
        return nodes;
    }
    public void deleteTeam(String id) throws IOException {
        Node node= document.selectSingleNode("//ChessLeague/teams/team[@id = '"+id+"']");
        node.detach();
        print();
        //save
    }

    public void print() throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        Writer w=new OutputStreamWriter(new FileOutputStream(this.document.getName()));
        XMLWriter writer;
        writer = new XMLWriter( w, format );
        writer.write( document );
        writer.close();
    }

}