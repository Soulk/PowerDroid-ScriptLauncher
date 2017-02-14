package model;

import com.sun.media.jfxmedia.logging.Logger;
import database.FileDatabase;
import database.ResultDatabase;
import database.ScriptDatabase;
import pdsl.PowerDroidSL;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static pdsl.PowerDroidSL.CSV_DATA_FILE_NAME;
import static pdsl.PowerDroidSL.POWERDROID_SL_HOME_DIRECTORY;

/**
 * Created by David on 27/01/2017.
 */
public class ScriptLauncher {

    List<Script> l_script;
    File pythonScriptFolder;
    public List<Script> getL_script() {
        return l_script;
    }

    public ScriptLauncher() {
        l_script = new ArrayList<Script>();
    }

    public void run(File pythonScriptFolder) {
        this.pythonScriptFolder = pythonScriptFolder;
        getAllScript();
        System.out.println("Script(s) to execute : " + l_script.size());
        for(int i = 0; i < l_script.size(); i ++){
            Script script = l_script.get(i);
            ScriptFiles files = loadScriptFiles(script);
            if(files != null){
                Result result = executeScript(script, files);
                deleteScriptFiles(files);
                deleteScript(script);
                updateResult(result);
            }
        }
        l_script.clear();
    }

    public void getAllScript() {
        l_script = ScriptDatabase.all();
    }

    public ScriptFiles loadScriptFiles(Script script){
        return FileDatabase.select(script.getIdFile());
    }
    public void deleteScriptFiles(ScriptFiles scriptFile) {
        FileDatabase.delete(scriptFile);
    }
    public Result executeScript(Script script, ScriptFiles files){
        Result result = null;
        File pathFile = new File(POWERDROID_SL_HOME_DIRECTORY+File.separator+"script.sh");//new File("\"./script/platform-tools/script.bat\"");

        Path path = pathFile.toPath();
        if(script.getMethod().equals("robotium")){

            if(files.getManifest() != null && files.getApkTest() != null) {
                           // String regex = /package="(.*)" / g;
                           // String regex1 = /<instrumentation android:name = "(.*)" / g;

                String content = null;
                try {
                    content = String.join("\n", Files.readAllLines(files.getManifest().toPath()));


                    Pattern regex = Pattern.compile(" /package=\"(.*)\" / g");
                    String result1 = regex.split(content)[0];

                    Pattern regex1 = Pattern.compile("/<instrumentation android:name = \"(.*)\" / g");
                    String result2 = regex1.split(content)[0];

                    String data = "adb shell input keyevent 26\nadb install -r " + files.getApk().getName() + "\nadb install -r " + files.getApkTest().getName() + "\nadb shell am instrument -w " + result1 + "/" + result2 + "\nadb shell input keyevent 26\nexit\n";


                    Files.write(path, data.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if(script.getMethod().equals("monkey")){

            if(files.getManifestAndroid() != null) {

                String content = null;
                try {
                    content = String.join("\n", Files.readAllLines(files.getManifestAndroid().toPath()));


                    Pattern regex3 = Pattern.compile("/package=\"(.*)\" /g");
                    String result3 = regex3.split(content)[0];

                    String data = "adb shell input keyevent 26\nadb install -r " + files.getApk().getName() + "\nadb shell monkey -p " +result3+ " -v -s 0005 1000\nadb shell input keyevent 26\nexit\n";

                    Files.write(path, data.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(Files.exists(path)){
            Logger.logMsg(Logger.INFO, "Successful Write to " + path);

            ProcessBuilder buildMonitor = new ProcessBuilder("sudo", "python", "collectData.py",  "data.csv",  "/dev/ttyACM0",  "1");
            buildMonitor.directory(pythonScriptFolder);

            ProcessBuilder builderScriptADB = new ProcessBuilder("/bin/bash", pathFile.getAbsolutePath());//new ProcessBuilder("start","script.bat");

            try {


                File dataCSV = new File(POWERDROID_SL_HOME_DIRECTORY+File.separator+CSV_DATA_FILE_NAME);

                //builder.redirectOutput(outputFile);

                Process monitorProcess = buildMonitor.start();

                Process adbProcess = builderScriptADB.start();

                adbProcess.waitFor();

                monitorProcess.destroy();

                result = new Result(script.getId(),script.getIdFile(), dataCSV);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Logger.logMsg(Logger.ERROR, "write error : " + path);
        }

        /*fs.writeFile(path, data, function(error) {
            if (error) {
                console.error("write error:  " + error.message);
                for(var file in files){
                    fs.unlink("./script/platform-tools/"+files[file]);
                }
                fs.unlink("./script/platform-tools/script.bat");
            } else {
                console.log("Successful Write to " + path);
                child = exec('start script.bat',{cwd: 'script\\platform-tools'},
                function (error, stdout, stderr) {
                    console.log('stdout: ' + stdout);
                    console.log('stderr: ' + stderr);
                    if (error !== null) {
                        console.log('exec error: ' + error);
                    }
                    for(var file in files){
                        fs.unlink("./script/platform-tools/"+files[file]);
                    }
                    fs.unlink("./script/platform-tools/script.bat");
                });
            }
        });*/

        return result;
    }

    public void deleteScript(Script script){
        ScriptDatabase.delete(script);
    }

    private void updateResult(Result result) {
        ResultDatabase.insertResult(result);
    }
}
