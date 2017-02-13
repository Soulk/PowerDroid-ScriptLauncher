package model;

import java.io.File;

/**
 * Created by Geoffrey on 27/01/2017.
 */
public class ScriptFiles {

    private int id;
    private String method;
    private File apk;
    private File Manifest;
    private File ManifestAndroid;
    private File apkTest;

    public ScriptFiles(){

    }

    public ScriptFiles(int id, String method, File apk, File manifest, File manifestAndroid, File apkTest) {
        this.id = id;
        this.method = method;
        this.apk = apk;
        Manifest = manifest;
        ManifestAndroid = manifestAndroid;
        this.apkTest = apkTest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public File getApk() {
        return apk;
    }

    public void setApk(File apk) {
        this.apk = apk;
    }

    public File getManifest() {
        return Manifest;
    }

    public void setManifest(File manifest) {
        Manifest = manifest;
    }

    public File getManifestAndroid() {
        return ManifestAndroid;
    }

    public void setManifestAndroid(File manifestAndroid) {
        ManifestAndroid = manifestAndroid;
    }

    public File getApkTest() {
        return apkTest;
    }

    public void setApkTest(File apkTest) {
        this.apkTest = apkTest;
    }

}
