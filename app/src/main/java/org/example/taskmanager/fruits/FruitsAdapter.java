package org.example.taskmanager.fruits;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.example.taskmanager.R;
import org.example.taskmanager.base.ApiItemDto;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jamarfal on 5/2/18.
 */

public class FruitsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<ApiItemDto> bunchOfItemsFruit;

    public FruitsAdapter(Context context) {
        this.bunchOfItemsFruit = Collections.emptyList();
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = layoutInflater.inflate(R.layout.item_fruit, parent, false);
        return new FruitsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ApiItemDto apiItemDto = bunchOfItemsFruit.get(position);
        ((FruitsViewHolder) holder).bindTo(apiItemDto);
    }

    @Override
    public int getItemCount() {
        return this.bunchOfItemsFruit != null ? this.bunchOfItemsFruit.size() : 0;
    }


    public void updateFruits(List<ApiItemDto> bunchOfItemsFruit) {
        this.bunchOfItemsFruit = bunchOfItemsFruit;
    }

    static class FruitsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_fruit_category_label)
        TextView categoryLabel;
        @BindView(R.id.item_fruit_name_label)
        TextView nameLabel;
        @BindView(R.id.item_fruit_farm_name_label)
        TextView farmNameLabel;
        @BindView(R.id.item_fruit_farm_zipcode_label)
        TextView farmZipCodeLabel;


        public FruitsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindTo(ApiItemDto apiItemDto) {
            categoryLabel.setText(apiItemDto.getCategory());
            nameLabel.setText(apiItemDto.getItem());
            farmNameLabel.setText(apiItemDto.getFarmName());
            farmZipCodeLabel.setText(apiItemDto.getZipcode());
        }

    }
}
