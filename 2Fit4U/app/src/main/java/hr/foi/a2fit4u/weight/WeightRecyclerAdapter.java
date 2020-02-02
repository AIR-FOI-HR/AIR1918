package hr.foi.a2fit4u.weight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hr.foi.a2fit4u.R;

public class WeightRecyclerAdapter extends RecyclerView.Adapter<WeightRecyclerAdapter.ViewHolder> {

    private List<WeightItem> weightItemList;
    private Context context;

    public WeightRecyclerAdapter(List<WeightItem> weightItemList, Context context) {
        this.weightItemList = weightItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeightItem weightItem = weightItemList.get(position);

        holder.weightDate.setText("Date: " + weightItem.getDateValue());
        holder.weightValue.setText("Weight" + weightItem.getWeightValue());
    }

    @Override
    public int getItemCount() {
        return weightItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView weightValue;
        public TextView weightDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            weightValue = itemView.findViewById(R.id.weight_item_weight);
            weightDate = itemView.findViewById(R.id.weight_item_date);
        }
    }

}
