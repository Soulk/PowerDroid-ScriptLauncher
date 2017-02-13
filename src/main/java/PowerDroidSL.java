import database.Connexion;
import model.ScriptLauncher;

import java.io.*;

/**
 * Created by David on 27/01/2017.
 */
public class PowerDroidSL {

    public static final String POWERDROID_SL_HOME_DIRECTORY = System.getProperty("user.home") + File.separator + "powerdroid";

    public static void main(String args[]) {

        File PDSLFolder = new File(POWERDROID_SL_HOME_DIRECTORY);
        if(!PDSLFolder.exists()) PDSLFolder.mkdir();

        Connexion con = new Connexion("jdbc:postgresql://localhost:5432/todo","postgres","lolilol97");

        ScriptLauncher sl = new ScriptLauncher();

        //INIT POWER MONITOR TODO

        ProcessBuilder initMonitor = new ProcessBuilder("sudo", "python", "collectData.py",  "data.csv",  "/dev/ttyACM0",  "1",  "-s" );
        initMonitor.directory(new File("C:\\Users\\Geoffrey\\PowerDroid-ScriptAnalyser\\scriptPython"));

        initMonitor.redirectErrorStream(true);
        try {
            Process process = initMonitor.start();
            byte[] buff = new byte[1080];
            process.getInputStream().read(buff);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Details : " + line);
            }
            process.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Run script launcher
        sl.run();
    }

}
