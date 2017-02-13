package model;

import java.io.File;

/**
 * Created by Geoffrey on 03/02/2017.
 */
public class Result {
    private Integer idResult;
    private Integer idFile;
    private File fileResult;

    public Result(Integer idResult, Integer idFile, File fileResult) {
        this.idResult=idResult;
        this.idFile = idFile;
        this.fileResult = fileResult;
    }

    public Integer getIdResult() {
        return idResult;
    }

    public void setIdResult(Integer idResult) {
        this.idResult = idResult;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
    }

    public File getFileResult() {
        return fileResult;
    }

    public void setFileResult(File fileResult) {
        this.fileResult = fileResult;
    }



}
