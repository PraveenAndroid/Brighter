package model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Praveen on 29-Nov-17.
 */

public class Store extends RealmObject {

    @PrimaryKey
    public String storeId;
    public String storeName;
    public String storeAddress;
}
