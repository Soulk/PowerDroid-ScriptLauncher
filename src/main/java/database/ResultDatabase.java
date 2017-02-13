package database;

import model.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Geoffrey on 03/02/2017.
 */
public class ResultDatabase {


    public static void insertResult(Result result){
        PreparedStatement stmt = null;
        String query = "UPDATE result AS r SET data = ?, status = true WHERE idresult = ?";

        try {
            stmt = Connexion.getDbconnect().prepareStatement(query);
            stmt.setBinaryStream(1, Files.newInputStream(result.getFileResult().toPath()));
            stmt.setInt(2, result.getIdResult());
            stmt.executeUpdate();
        } catch (SQLException e ) {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
