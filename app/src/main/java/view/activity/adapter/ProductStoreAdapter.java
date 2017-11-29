package view.activity.adapter;
import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.praveen.brighterbrain.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Store;

/**
 * Created by praveen on 29/11/2017.
 */

public class ProductStoreAdapter extends RecyclerView.Adapter<ProductStoreAdapter.ViewHolder>  {

    /*
    * Class Variable
    */

    private Context mContext;
    private List<Store> productStoreList;

    public ProductStoreAdapter(Activity activity, List<Store> productStoreList) {

        this.mContext=activity;
        this.productStoreList=productStoreList;
    }

    @Override
    public ProductStoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_productstore_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductStoreAdapter.ViewHolder holder, int position) {

        /*
        *
        * Get Data from database and set to View
        */

        Store store=productStoreList.get(position);

        holder.productStoreName.setText(store.storeName);
        holder.productStoreAddress.setText(store.storeAddress);
    }

    @Override
    public int getItemCount() {

        return productStoreList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        /*
        * View Injected Using ButterKnife
        */

        @BindView(R.id.productStoreName) TextView productStoreName;
        @BindView(R.id.productStoreAddress) TextView productStoreAddress;


        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            ButterKnife.bind(this,itemView);
        }
    }
}
