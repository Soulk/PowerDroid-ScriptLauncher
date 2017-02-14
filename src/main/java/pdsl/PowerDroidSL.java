package pdsl;

import database.Connexion;
import database.FileDatabase;
import database.ResultDatabase;
import database.ScriptDatabase;
import model.Result;
import model.Script;
import model.ScriptFiles;
import model.ScriptLauncher;

import java.io.*;
import java.nio.file.Files;

/**
 * Created by David on 27/01/2017.
 */
public class PowerDroidSL {

    public static final String POWERDROID_SL_HOME_DIRECTORY = System.getProperty("user.home") + File.separator + ".powerdroid";
    public static final String CSV_DATA_FILE_NAME = "data.csv";

    public static void main(String args[]) {

        File PDSLFolder = new File(POWERDROID_SL_HOME_DIRECTORY);
        //Files.deleteIfExists(PDSLFolder.toPath());
        if(!PDSLFolder.exists()) PDSLFolder.mkdir();

        Connexion con = new Connexion("jdbc:postgresql://localhost:5432/todo","postgres","lolilol97");

        ScriptLauncher sl = new ScriptLauncher();

        //TEST DB+ Creation fichier
        /*sl.getAllScript();
        for(Script s : sl.getL_script()) {
            ScriptFiles sf= sl.loadScriptFiles(s);
            sl.deleteScriptFiles(sf);
        }
        ScriptDatabase.delete(ScriptDatabase.all().get(0));
        Result r = new Result(1, 1 , new File("pom.xml"));
        ResultDatabase.insertResult(r);*/

        //INIT POWER MONITOR TODO

        ProcessBuilder initMonitor = new ProcessBuilder("sudo", "python", "collectData.py",  CSV_DATA_FILE_NAME,  "/dev/ttyACM0",  "1",  "-s" );
                // new ProcessBuilder("cmd.exe", "/c", "python", "collectData.py",  CSV_DATA_FILE_NAME,  "/dev/ttyACM0",  "1",  "-s" );
        File pythonScriptFolder = null;

        if(args.length > 0 && Files.exists(new File(args[0]).toPath())){
            pythonScriptFolder = new File(args[0]);
            initMonitor.directory(pythonScriptFolder);
        } else {
            System.out.println("Python script folder not defined in command parameters :");
            System.out.println(" --> Define default Python script folder (named 'python-script') in '" + POWERDROID_SL_HOME_DIRECTORY + "' folder as default");

            pythonScriptFolder = new File(POWERDROID_SL_HOME_DIRECTORY + File.separator + "python-script");
            if(PDSLFolder.exists() && !pythonScriptFolder.exists()){
                pythonScriptFolder.mkdir();
            } else {
                initMonitor.directory(pythonScriptFolder);
            }
        }

        if(pythonScriptFolder != null && pythonScriptFolder.listFiles().length > 0) {
            initMonitor.redirectErrorStream(true);
            try {
                Process process = initMonitor.start();
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("> " + line);
                }
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Run script launcher
            sl.run(pythonScriptFolder);
        } else {
            System.out.println("ERROR : 'python-script' folder is empty ('"+pythonScriptFolder.getAbsolutePath()+"')");
        }
    }

}
