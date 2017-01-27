/**
 * Created by David on 27/01/2017.
 */
public class Script {
    private int id;
    private int idUser;
    private String filenameapk, filenameapktest, filenamemanifest, filenamemanifestandroid;
    public Script(int id, int idUser, String filenameapk, String filenameapktest, String filenamemanifest, String filenamemanifestandroid) {
        this.id = id;
        this.idUser = idUser;
        this.filenameapk = filenameapk;
        this.filenameapktest = filenameapktest;
        this.filenamemanifest = filenamemanifest;
        this.filenamemanifestandroid = filenamemanifestandroid;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFilenameapk() {
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
    }



}
