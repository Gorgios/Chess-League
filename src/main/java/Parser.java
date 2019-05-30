

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.*;

public class Parser {
    private SAXReader reader;
    private Document document;

    public Parser(String filePath) throws DocumentException {
        reader= new SAXReader();
        this.document=reader.read(filePath);
    }

    public void teamMake(Scanner sc) throws IOException {
        System.out.println("Podaj ID teamu (3 litery, bez cyfr)");
        sc.nextLine();
        String id = sc.nextLine();
        System.out.println("Podaj nazwe");
        String name = sc.nextLine();

        System.out.println("Podaj ranking");
        int rank = sc.nextInt();
        System.out.println("Podaj imie pierwszego trenera");
        sc.nextLine();
        String firstCoachFirstName = sc.nextLine();
        System.out.println("Podaj nazwisko pierwszego trenera");
        String firstCoachLastName = sc.nextLine();
        System.out.println("Podaj imie trenera od analizy");
        String analCoachFirstName = sc.nextLine();
        System.out.println("Podaj nazwisko trenera od analizy");
        String analCoachLastName = sc.nextLine();
        System.out.println("Podaj imie trenera mlodzikow");
        String youngCoachFirstName = sc.nextLine();
        System.out.println("Podaj nazwisko trenera mlodzikow");
        String youngCoachLastName = sc.nextLine();
        System.out.println("Podaj ulice");
        String street = sc.nextLine();

        System.out.println("Podaj miasto");
        String city= sc.nextLine();

        System.out.println("Podaj kraj");
        String country = sc.nextLine();
        createTeam(id, name, rank, firstCoachFirstName, firstCoachLastName, analCoachFirstName, analCoachLastName, youngCoachFirstName, youngCoachLastName, street, city, country);

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
                    + "  Naazwa: " + node.selectSingleNode("name").getText()
                    + "  Ranking: " + node.selectSingleNode("rank").getText()
                    +"   Trener : "+node.selectSingleNode("staff/first_coach/firstname").getText()+" " + node.selectSingleNode("staff/first_coach/lastname").getText()
                    +"   Ulica: "+node.selectSingleNode("address/street").getText()
                    +"   Miasto: "+ node.selectSingleNode("address/city").getText()
                    +"   Kraj: "+node.selectSingleNode("address/country").getText());
            a++;
        }
        return nodes;
    }
    public void modifyTeamName(Scanner sc) throws IOException {
        System.out.println("Wpisz ID zespolu: ");
        sc.nextLine();
        String id = sc.nextLine();
        Node node = this.document.selectSingleNode("//chessLeague/teams/team[@id = '"+id+"']");
        System.out.println("Wpisz nowa nazwe zespolu: ");
        String newName = sc.nextLine();
        Element team = (Element) node;
        team.element("name").setText(newName);
        print();
    }
    public void modifyTeamRank(Scanner sc) throws IOException {
        System.out.println("Wpisz ID zespolu: ");
        sc.nextLine();
        String id = sc.nextLine();
        Node node = this.document.selectSingleNode("//chessLeague/teams/team[@id = '"+id+"']");
        System.out.println("Wpisz nowy ranking zespolu: ");
        int newRank = sc.nextInt();
        Element team = (Element) node;
        team.element("rank").setText(Integer.toString(newRank));
        print();
    }
    public void deleteTeam(Scanner sc)  throws IOException {
        System.out.println("Jaki zespol usunac (Podaj ID): ");
        sc.nextLine();
        String id = sc.nextLine();
        try {
            Node node = document.selectSingleNode("//chessLeague/teams/team[@id = '" + id + "']");
            node.detach();
            print();
            System.out.println("Usunieto pomyslnie");
        }
        catch (Exception exc){
            exc.printStackTrace();
            System.out.println("Nie udalo sie usunac");
        }
    }

    public void print() throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        Writer w=new OutputStreamWriter(new FileOutputStream(this.document.getName()));
        XMLWriter writer;
        writer = new XMLWriter( w, format );
        writer.write( document );
        writer.close();
    }

    public void sort(String xPath) throws IOException {
        List<Node> nodes = this.document.selectNodes(xPath);
        Collections.sort(nodes, (Node n1, Node n2)->n1.getStringValue().compareTo(n2.getStringValue()));
        int a=1;
        for (Node node : nodes) {
            System.out.println(a+". ID : "+node.valueOf("@id")
                    + "  Nazwa: " + node.selectSingleNode("name").getText()
                    + "  Ranking: " + node.selectSingleNode("rank").getText()
                    +"  Trener: "+node.selectSingleNode("staff/first_coach/firstname").getText()+" " +node.selectSingleNode("staff/first_coach/lastname").getText()
                    +"  Ulica: "+node.selectSingleNode("address/street").getText()
                    +"  Miasto: "+ node.selectSingleNode("address/city").getText()
                    +"  Kraj: "+node.selectSingleNode("address/country").getText());
            a++;
        }
        print();
    }
    private String getContent(Element element) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Iterator<Element> i = element.elementIterator(); i.hasNext();) {
            Element e = i.next();
            stringBuilder.append(e.asXML());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}