package model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Praveen on 29-Nov-17.
 */

public class Product extends RealmObject {

    @PrimaryKey
    public String productId;
    public String productName;
    public String productDescription;
    public double productRegularPrice;
    public double productSalePrice;
    public String productImage;
    public RealmList<Color> productColors;
    public RealmList<Store> productStores;
}


