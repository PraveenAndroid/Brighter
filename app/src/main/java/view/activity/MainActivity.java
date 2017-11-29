package view.activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.praveen.brighterbrain.R;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import controller.Storage;
import io.realm.Realm;
import model.Product;
import util.Utils;

public class MainActivity extends AppCompatActivity {


    /*
    * Inject View Using ButterKnife
    */

    @BindView(R.id.btnCreateProduct) Button createProduct;
    @BindView(R.id.btnShowProduct) Button showProduct;


    /*
    * class Variable
    */

    private Activity activity;
    private int productCount;
    private String productInfoFromJson;
    private Gson gson;
    private List<Product> productListFromJson;
    private List<Product> productListFromDatabaase;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        setContentView(R.layout.activity_main);
        ButterKnife.bind(activity);

    }


    @Override
    protected void onResume() {
        super.onResume();

        /*
        *
        * Get Instance of Relam database , get ProductList and count if available
        */

        realm = Realm.getDefaultInstance();
        productListFromDatabaase= realm.where(Product.class).findAll();
        productCount=productListFromDatabaase.size();
        createProduct.setText("Create Product "+(productCount+1));

    }

    public void onClickCreateProduct(View view) {

        if(productCount<5) {
            insertNewProduct(productCount);
            productCount++;
        }else
        {
            Toast.makeText(activity," Can not Insert more than 5 Product in database",Toast.LENGTH_LONG).show();
        }
    }

    /*
    *
    *  onClickShowProduct method navigate to Product List Page
    *
    */

    public void onClickShowProduct(View view) {


        if(productListFromDatabaase.size()>0) {

            Intent intent = new Intent(activity, ViewProductList.class);
            startActivity(intent);
        }else
        {
            Toast.makeText(activity,"Firstly Insert Product in Database",Toast.LENGTH_LONG).show();
        }

    }

    /*
    *    method is used for insert data from json to Database
    */

    private void insertNewProduct(int count) {

        /*
        *
        *  read product detail from json
        */

        productInfoFromJson = Utils.readJSONFromAsset(activity, "productsample");
        gson = new Gson();
        productListFromJson = Arrays.asList(gson.fromJson(productInfoFromJson.toString(), Product[].class));

        /*
        *  Used method for Insert Single Data
        */

        Storage.saveOrUpdate(productListFromJson.get(count));
        Toast.makeText(activity,"Product "+(productCount+1)+" Insert in Database Sucessfully",Toast.LENGTH_LONG).show();
        createProduct.setText("Create Product "+(productCount+1));

    }

}
