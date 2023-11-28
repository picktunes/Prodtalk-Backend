
package utils.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;


public class BlobUtils {
    public static String blobToString(java.sql.Blob blob) throws SQLException, IOException {
    if (blob == null) {
        return "";
    }

    try (InputStream is = blob.getBinaryStream();
         ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        return baos.toString("UTF-8"); 
    }
}


    public java.sql.Blob stringToBlob(String data, Connection connection) throws Exception {
        if (data == null) {
            return null;
        }

        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        java.sql.Blob blob = connection.createBlob();
        blob.setBytes(1, bytes);
        return blob;
    }
}
