package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Praveen on 29-Nov-17.
 */

public class Color extends RealmObject {

    @PrimaryKey
    public String colorName;

}
