package view.activity;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.praveen.brighterbrain.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import controller.Storage;
import io.realm.Realm;
import model.Color;
import model.Product;
import model.Store;
import util.SpacesItemDecoration;
import view.activity.adapter.ProductColorAdapter;
import view.activity.adapter.ProductStoreAdapter;
import view.activity.fragment.ProductImageFullView;

public class ProductDetailActivity extends AppCompatActivity {

     /*
     *  View Injected Using Butter Knife
     */

    @BindView(R.id.productThumbImage) ImageView productThumbImage;
    @BindView(R.id.productName) EditText productName;
    @BindView(R.id.productDescription) EditText productDescription;
    @BindView(R.id.productRegPrice) EditText productRegPrice;
    @BindView(R.id.productSalePrice) EditText productSalePrice;
    @BindView(R.id.productStoreListView) RecyclerView productStoreListView;
    @BindView(R.id.productColorListView) RecyclerView productColorListView;

    /*
    * class Varaible decleare Globeally
    */

    private Product product;
    public  List<Store> productStoreList;
    public  List<Color> productColorList;
    private ProductStoreAdapter productStoreAdapter;
    private ProductColorAdapter productColorAdapter;
    private Activity activity;
    private String productId;
    private Realm realm;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(activity);
        productId = getIntent().getStringExtra("productId");


        /*
        *
        * set Layout Param for RecyclerView
        */


        productStoreListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        productStoreListView.getLayoutManager().scrollToPosition(0);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spaceing);
        productStoreListView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));


        productColorListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        productColorListView.getLayoutManager().scrollToPosition(0);
        productColorListView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        /*
        *
        * Get Realm Instance and then product from realm Database
        */

        realm = Realm.getDefaultInstance();
        product = realm.copyFromRealm(realm.where(Product.class).equalTo("productId", productId).findFirst());
        productStoreList= product.productStores;
        productColorList= product.productColors;

        /*
        *
        *  Set data to Selected Project
        *
        */


        productName.setText(product.productName);
        productDescription.setText(product.productDescription);
        productRegPrice.setText(""+product.productRegularPrice);
        productSalePrice.setText(""+product.productSalePrice);

        Glide.with(activity)
                .load(Uri.parse("file:///android_asset/"+product.productImage))
                .into(productThumbImage);


        /*
        *
        * set the StoreList and ColorList for Selected Product
        *
        */

        productStoreAdapter=new ProductStoreAdapter(activity,productStoreList);
        productStoreListView.setAdapter(productStoreAdapter);

        productColorAdapter=new ProductColorAdapter(activity,productColorList);
        productColorListView.setAdapter(productColorAdapter);


        /*
        *
        *  Full View of Product Implemented
        */

        productThumbImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProductImageFullView.newInstance(product.productImage,"").show(getSupportFragmentManager(),"TAG");
            }
        });


    }

    /*
    *
    * Delete Single Selected Product by ProductID using removeSingle()
    */

    public void onClickDelete(View view) {

        Storage.removeSingle(productId);
        finish();
        Toast.makeText(activity, "Product Deleted!", Toast.LENGTH_SHORT).show();
    }

    /*
    *
    * Update Selected Product
    *
    */

    public void onClickUpdate(View view) {


        product.productName = productName.getText().toString();
        product.productDescription = productDescription.getText().toString();
        product.productRegularPrice = Double.parseDouble(productRegPrice.getText().toString());
        product.productSalePrice =    Double.parseDouble(productSalePrice.getText().toString());
        Storage.saveOrUpdate(product);
        Toast.makeText(activity, "Product Update!", Toast.LENGTH_SHORT).show();
        finish();

    }
}
