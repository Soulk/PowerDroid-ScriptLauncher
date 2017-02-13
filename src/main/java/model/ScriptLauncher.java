package model;

import com.sun.media.jfxmedia.logging.Logger;
import database.FileDatabase;
import database.ResultDatabase;
import database.ScriptDatabase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by David on 27/01/2017.
 */
public class ScriptLauncher {
    List<Script> l_script;

    public ScriptLauncher() {
        l_script = new ArrayList<Script>();
    }

    public void run() {
        getAllScript();
        System.out.println("Script(s) to execute : " + l_script.size());
        for(int i = 0; i < l_script.size(); i ++){
            Script script = l_script.get(i);
            ScriptFiles files = loadScriptFiles(script);
            if(files != null){
                Result result = executeScript(script, files);
                deleteScript(script);
                updateResult(result);
            }
        }
        l_script.clear();

        //TEST


    }

    public void getAllScript() {
        l_script = ScriptDatabase.all();
    }

    public ScriptFiles loadScriptFiles(Script script){
        return FileDatabase.select(script.getIdFile());
    }

    public Result executeScript(Script script, ScriptFiles files){
        Result result = null;
        File pathFile = new File("\"./script/platform-tools/script.bat\"");

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

            ProcessBuilder builder = new ProcessBuilder("start script.bat");
            try {


                File outputFile = new File("");

                builder.redirectOutput(outputFile);
                Process process = builder.start();

                //process.waitFor();


                result = new Result(script.getIdFile(), outputFile);
            } catch (IOException e) {
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
