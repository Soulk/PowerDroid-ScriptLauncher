package model;

/**
 * Created by David on 27/01/2017.
 */
public class Script {
    private int id;
    private int idFile;
    String method;
    public Script() {

    }
    public Script(int id, int idFile, String method) {
        this.id = id;
        this.idFile = idFile;
        this.method = method;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFile() {
        return idFile;
    }

    public void setIdFile(int idFile) {
        this.idFile = idFile;
    }


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    /*public String getFilenameapk() {
        return filenameapk;
    }

    public void setFilenameapk(String filenameapk) {
        this.filenameapk = filenameapk;
    }

    public String getFilenameapktest() {
        return filenameapktest;
    }

    public void setFilenameapktest(String filenameapktest) {
        this.filenameapktest = filenameapktest;
    }

    public String getFilenamemanifest() {
        return filenamemanifest;
    }

    public void setFilenamemanifest(String filenamemanifest) {
        this.filenamemanifest = filenamemanifest;
    }

    public String getFilenamemanifestandroid() {
        return filenamemanifestandroid;
    }

    public void setFilenamemanifestandroid(String filenamemanifestandroid) {
        this.filenamemanifestandroid = filenamemanifestandroid;
    }*/

    @Override
    public String toString() {
        return "model.Script{" +
                "id=" + id +
                ", idFile=" + idFile +
                ",method=" + method
                /*", filenameapk='" + filenameapk + '\'' +
                ", filenameapktest='" + filenameapktest + '\'' +
                ", filenamemanifest='" + filenamemanifest + '\'' +
                ", filenamemanifestandroid='" + filenamemanifestandroid + '\'' */+
                '}';
    }



}
