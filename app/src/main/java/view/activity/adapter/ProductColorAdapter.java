package view.activity.adapter;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.praveen.brighterbrain.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Color;

/**
 * Created by praveen on 29/11/2017.
 */

public class ProductColorAdapter extends RecyclerView.Adapter<ProductColorAdapter.ViewHolder> {

    public Context mContext;
    public List<Color> productColorList;

    public ProductColorAdapter(Activity activity, List<Color> productColorList) {

        this.mContext=activity;
        this.productColorList=productColorList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_productcolor_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        /*
        *
        * get data from Data to List
        */

        Color color=productColorList.get(position);
        holder.productColorName.setText(color.colorName);
    }

    @Override
    public int getItemCount() {

        return productColorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        /*
        * View Injected Using ButterKnife
        */

        @Nullable @BindView(R.id.productColorName) Button productColorName;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            ButterKnife.bind(this,itemView);
        }
    }
}
