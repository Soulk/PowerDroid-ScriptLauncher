import database.Connexion;
import model.ScriptLauncher;

/**
 * Created by David on 27/01/2017.
 */
public class Main {
    public static void main(String args[]) {

        Connexion con = new Connexion("jdbc:postgresql://localhost:5433/todo","postgres","lolilol97");

        ScriptLauncher sl = new ScriptLauncher();

        //INIT POWER MONITOR TODO

        //Run script launcher
        sl.run();
    }

}
