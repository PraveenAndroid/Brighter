package controller;
import android.support.annotation.Nullable;
import java.util.List;
import io.realm.Realm;
import model.Product;

/**
 * Created by Praveen on 29-11-2017.
 */

public class Storage {

    /*
    *
    * removeAll() method is used for remove all data from database
    */


    public static void removeAll() {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Product.class).findAll().deleteAllFromRealm();
            }
        });
    }


    /*
    *
    * removeSingle() method is used for remove Single  data from database
    */


    public static void removeSingle(final String id) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Product food = realm.where(Product.class).equalTo("productId", id).findFirst();
                if (food != null) {

                    if (!realm.isInTransaction()) {
                        realm.beginTransaction();
                    }
                    food.deleteFromRealm();

                    realm.commitTransaction();
                }
            }
        });
    }

    /*
    *
    * saveOrUpdate() method is used for Insert Single product or Update Product in Database
    */


    public static void saveOrUpdate(final Product data) {

        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                realm.copyToRealmOrUpdate(data);

            }
        });

    }

    /*
    *
    * save() method is used for Insert List of Product in Database
    */

    public static void save(final List<Product> dataList) {
        executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(dataList);
            }
        });
    }


    /*
    *
    * executeTransaction() method is used for Create Instance of Realm Database
    */

    private static void executeTransaction(Realm.Transaction transaction) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(transaction);
        } catch (Throwable e) {
            e.printStackTrace();
            // L.e("executeTransaction",e);
        } finally {
            close(realm);
        }

    }


    public static void close(@Nullable Realm realm) {
        if (realm != null) {
            realm.close();
        }

    }
}
