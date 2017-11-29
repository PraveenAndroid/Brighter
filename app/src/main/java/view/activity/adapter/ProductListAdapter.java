package view.activity.adapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.praveen.brighterbrain.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Product;
import view.activity.ProductDetailActivity;

/**
 * Created by praveen on 28/11/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    /*
    * Class Varaiable
    */


    public Context mCotext;
    public List<Product> productListSaved;

    public ProductListAdapter(Activity activity, List<Product> productListSaved) {

        this.mCotext=activity;
        this.productListSaved=productListSaved;
    }

    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_adapter_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductListAdapter.ViewHolder holder, int position) {


        /*
        *
        * Get Data from database and set to View
        */


        final Product product=productListSaved.get(position);
        holder.productName.setText(product.productName);
        holder.productDescription.setText(product.productDescription);
        holder.productRegPrice.setText(mCotext.getString(R.string.Rs)+product.productRegularPrice);
        holder.productsalePrice.setText(mCotext.getString(R.string.Rs)+product.productSalePrice);
        holder.productRegPrice.setPaintFlags(holder.productRegPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        Glide.with(mCotext)
                .load(Uri.parse("file:///android_asset/"+product.productImage))
                .into(holder.productThumbImage);



        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mCotext, ProductDetailActivity.class);
                intent.putExtra("productId", product.productId);
                mCotext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return productListSaved.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        /*
        *
        * View Injected Using ButterKnife
        */

        @BindView(R.id.productName) TextView productName;
        @BindView(R.id.productDescription) TextView productDescription;
        @BindView(R.id.productRegPrice) TextView productRegPrice;
        @BindView(R.id.productSalePrice) TextView productsalePrice;
        @BindView(R.id.productThumbImage) ImageView productThumbImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            ButterKnife.bind(this,itemView);
        }
    }
}
