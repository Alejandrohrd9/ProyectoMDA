package Db;
import com.dropbox.core.*;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DbxUserFilesRequests;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadBuilder;
import com.dropbox.core.v2.users.FullAccount;
import java.io.*;
import java.util.Locale;

public class Dropbox {
    
    public void create(String folder) throws IOException, DbxException {
        // Get your app key and secret from the Dropbox developers website.
        final String APP_KEY = "6c74lyouvx9b2zm";
        final String APP_SECRET = "jat1ftoil9pw3l1";

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", Locale.getDefault().toString());

        DbxClientV2 client = new DbxClientV2(config, "4V8JflXxYjAAAAAAAAAABiHsDAbMJCFEf1tJdbAjHRCIJkY0J3RnDPRrVfM-6yI3");

        /*FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
        
        ListFolderResult result = client.files().listFolder("");
        while (true) {
        for (Metadata metadata : result.getEntries()) {
        System.out.println(metadata.getPathLower());
        }
        if (!result.getHasMore()) {
        break;
        }
        result = client.files().listFolderContinue(result.getCursor());
        }*/

        try (InputStream in = new FileInputStream("prueba.docx")) {
            DbxUserFilesRequests files = client.files();
            UploadBuilder up = files.uploadBuilder("/PruebaWeb");
            FileMetadata metadata = up.uploadAndFinish(in);
            //FileMetadata metadata = client.files().uploadBuilder("/PruebaWeb/prueba.docx").uploadAndFinish(in);
        }
    }
}
