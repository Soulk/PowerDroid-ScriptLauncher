package model;

import database.FileDatabase;
import database.ResultDatabase;
import database.ScriptDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 27/01/2017.
 */
public class ScriptLauncher {
    List<Script> l_script;
    String dbname;

    public ScriptLauncher() {
        this.dbname= dbname;
        l_script = new ArrayList<Script>();
    }
    public void run() {
        getAllScript();
        for(int i = 0; i < l_script.size(); i ++){
            Script script = l_script.get(i);
            ScriptFiles files = loadScriptFiles(script);
            if(files != null){
                Result result = executeScript(script, files);
                deleteScript(script);
                updateResult(result);
            }
        }
    }

    public void getAllScript() {
        l_script = ScriptDatabase.all();
    }

    public ScriptFiles loadScriptFiles(Script script){
        return FileDatabase.select(script.getIdFile());
    }

    public Result executeScript(Script script, ScriptFiles files){
        //TODO
        File outputFile = new File("");

        return new Result(script.getIdFile(), outputFile);
    }

    public void deleteScript(Script script){
        ScriptDatabase.delete(script);
    }

    private void updateResult(Result result) {
        ResultDatabase.insertResult(result);
    }
}
