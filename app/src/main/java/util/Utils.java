package util;
import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Praveen on 28-Nov-17.
 */

public class Utils
{

  //  JSONObject obj = new JSONObject(readJSONFromAsset());

    /*
    *  Convert Json file to String file
    */

    public static String readJSONFromAsset(Context context,String fileName) {

        String json = null;

        try {
            InputStream is = context.getResources().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
