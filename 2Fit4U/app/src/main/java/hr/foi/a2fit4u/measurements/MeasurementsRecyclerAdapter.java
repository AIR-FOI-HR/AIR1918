package hr.foi.a2fit4u.measurements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.items.MeasurementItem;

import java.util.List;

import hr.foi.a2fit4u.R;
import hr.foi.a2fit4u.weight.WeightRecyclerAdapter;

public class MeasurementsRecyclerAdapter extends RecyclerView.Adapter<MeasurementsRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<MeasurementItem> measurementItemList;

    public MeasurementsRecyclerAdapter(Context context, List<MeasurementItem> measurementItemList) {
        this.context = context;
        this.measurementItemList = measurementItemList;
    }

    @NonNull
    @Override
    public MeasurementsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.measurements_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeasurementsRecyclerAdapter.ViewHolder holder, int position) {

        MeasurementItem measurementItem = measurementItemList.get(position);

        holder.measurementValue.setText(measurementItem.getType() + ": " + measurementItem.getValue() + " cm");
        holder.measurementDateValue.setText(measurementItem.getDateValue());

    }

    @Override
    public int getItemCount() {
        return measurementItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView measurementDateValue, measurementValue;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            measurementDateValue = itemView.findViewById(R.id.measurements_item_date);
            measurementValue = itemView.findViewById(R.id.measurements_item);
        }
    }
}
