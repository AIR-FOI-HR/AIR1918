package hr.foi.a2fit4u.nfc;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.items.NFCItem;
import com.example.core.items.WeightItem;
import com.example.core.managers.DBManager;

import java.util.List;

import butterknife.OnLongClick;
import hr.foi.a2fit4u.R;
import hr.foi.a2fit4u.weight.WeightRecyclerAdapter;

public class NFCRecyclerAdapter extends RecyclerView.Adapter<NFCRecyclerAdapter.ViewHolder> {

    private List<NFCItem> nfcItemList;
    private Context context;

    public NFCRecyclerAdapter(List<NFCItem> nfcItemList, Context context) {
        this.nfcItemList = nfcItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public NFCRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nfc_item, parent, false);

        return new NFCRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NFCRecyclerAdapter.ViewHolder holder, int position) {
        NFCItem nfcItem = nfcItemList.get(position);

        holder.textViewNFCName.setText("Name: " + nfcItem.getName());
        holder.textViewNFCTag.setText("Tag: " + nfcItem.getTag());

    }

    @Override
    public int getItemCount() {
        return nfcItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewNFCName;
        public TextView textViewNFCTag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNFCName = itemView.findViewById(R.id.nfc_item_name);
            textViewNFCTag = itemView.findViewById(R.id.nfc_item_tag);

            textViewNFCName.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {


                    androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Are you sure you want to delete the NFC device?");

                    View viewInflated = LayoutInflater.from(v.getContext()).inflate(R.layout.nfc_dialog, (ViewGroup) v.getRootView(), false);


                    builder.setView(viewInflated);

                    builder.setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            DBManager.getInstance().DeleteData(textViewNFCTag.getText().toString());
                        }
                    });

                    builder.setNegativeButton(R.string.dialog_reject, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();

                    return false;
                }
            });
        }
    }
}
