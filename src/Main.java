/**
 * Created by David on 27/01/2017.
 */
public class Main {
    public static void main(String args[]) {
        ScriptLauncher sl = new ScriptLauncher("jdbc:postgresql://localhost:5433/todo","postgres","lolilol97");

        sl.getAllScript();
    }

}
