package view.activity;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.example.praveen.brighterbrain.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import model.Product;
import util.SpacesItemDecoration;
import view.activity.adapter.ProductListAdapter;

/**
 * Created by praveen on 28/11/2017.
 */

public class ViewProductList extends AppCompatActivity {

    /*
     *  View Injected Using Butter Knife
     */

    @BindView(R.id.productList) RecyclerView productList;

    /*
    * class Variable
    */

    private Activity activity;
    private Realm realm;
    private List<Product> productListSaved;
    private ProductListAdapter productListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=ViewProductList.this;
        setContentView(R.layout.activity_view_product);
        ButterKnife.bind(activity);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*
        *
        * Get Realm Instance and then productList from realm Database
        */

        realm = Realm.getDefaultInstance();
        productListSaved = realm.where(Product.class).findAll();

        /*
        *
        * set Layout Param for RecyclerView
        */


        Log.i("size", productListSaved.size()+"");
        productList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        productList.getLayoutManager().scrollToPosition(0);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spaceing);
        productList.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        /*
        *
        * set the ProductList in RecyclerView
        *
        */

        productListAdapter=new ProductListAdapter(activity,productListSaved);
        productList.setAdapter(productListAdapter);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
