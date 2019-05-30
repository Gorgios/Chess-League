import org.dom4j.DocumentException;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws DocumentException, IOException {
        String filePath="src/main/resources/database.xml";
        Parser par= new Parser(filePath);
        par.getTeams();
    }
}
