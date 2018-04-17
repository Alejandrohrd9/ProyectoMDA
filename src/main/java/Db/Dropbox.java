package Db;

import com.dropbox.core.*;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderResult;
import com.dropbox.core.v2.files.DbxUserFilesRequests;
import com.dropbox.core.v2.files.DeleteResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadBuilder;
import java.io.*;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.*;

public class Dropbox {

    private DbxRequestConfig config;
    private DbxClientV2 client;

    public Dropbox(){
        final String APP_KEY = "6c74lyouvx9b2zm";
        final String APP_SECRET = "jat1ftoil9pw3l1";
        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", Locale.getDefault().toString());
        client = new DbxClientV2(config, "4V8JflXxYjAAAAAAAAAABiHsDAbMJCFEf1tJdbAjHRCIJkY0J3RnDPRrVfM-6yI3");
    }

    public void insertFile(String name, InputStream in, HttpServletRequest request, HttpServletResponse response) throws IOException, DbxException, FileUploadException, Exception {

        DbxUserFilesRequests files = client.files();
        UploadBuilder up = files.uploadBuilder(name);
        FileMetadata metadata = up.uploadAndFinish(in);
    }

    public void createFolder(String name) throws DbxException {
        DbxUserFilesRequests files = client.files();
        CreateFolderResult up = files.createFolderV2("/"+name);
    }

    public void deleteFile(String urlPath) throws DbxException {
        DbxUserFilesRequests files = client.files();
        DeleteResult deleteRes = files.deleteV2(urlPath);
    }
}
