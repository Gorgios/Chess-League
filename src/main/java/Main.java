import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws DocumentException, IOException {
        String filePath = "src/main/resources/database.xml";
        Parser par = new Parser(filePath);
        Scanner sc = new Scanner(System.in);
        int a = 0;
        while (a != 7) {
            show();
            a = sc.nextInt();
            switch (a) {
                case 1: par.teamMake(sc);break;
                case 2: par.deleteTeam(sc);break;
                case 3: par.modifyTeamName(sc); break;
                case 4: par.modifyTeamRank(sc); break;
                case 5: par.getTeams();break;
                case 6: par.sort("//chessLeague/teams/team"); break;
                case 7: break;
                default: continue;
            }
        }

    }

    public static void show() {
        System.out.println("MENU");
        System.out.println("1. Dodaj druzyne");
        System.out.println("2. Usun druzyne");
        System.out.println("3. Zaaktualizuj nazwe druzyny");
        System.out.println("4. Zaaktualizuj ranking druzyny");
        System.out.println("5. Wypisz druzyny");
        System.out.println("6. Sortuj druzyny");
        System.out.println("7. Wyjdz");
    }
}
