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

        if(measurementItem.getHipValue() == null || measurementItem.getHipValue().isEmpty())
        {
            holder.measurementHipValue.setVisibility(View.INVISIBLE);
        }
        else
        {
            holder.measurementHipValue.setText(measurementItem.getHipValue() + " cm");
        }

        if(measurementItem.getNeckValue() == null || measurementItem.getNeckValue().isEmpty())
        {
            holder.measurementNeckValue.setVisibility(View.INVISIBLE);
        }
        else
        {
            holder.measurementNeckValue.setText(measurementItem.getNeckValue() + " cm");
        }

        if(measurementItem.getWaistValue() == null || measurementItem.getWaistValue().isEmpty())
        {
            holder.measurementWaistValue.setVisibility(View.INVISIBLE);
        }
        else
        {
            holder.measurementWaistValue.setText(measurementItem.getWaistValue() + " cm");
        }

        holder.measurementDateValue.setText(measurementItem.getDateValue());

    }

    @Override
    public int getItemCount() {
        return measurementItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView measurementHipValue, measurementWaistValue, measurementNeckValue, measurementDateValue;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            measurementDateValue = itemView.findViewById(R.id.measurements_item_date);
            measurementHipValue = itemView.findViewById(R.id.measurements_item_hip);
            measurementNeckValue = itemView.findViewById(R.id.measurements_item_neck);
            measurementWaistValue = itemView.findViewById(R.id.measurements_item_waist);
        }
    }
}
