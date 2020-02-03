package hr.foi.a2fit4u.nfc;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.core.items.NFCItem;
import com.example.core.managers.DBManager;
import com.example.core.util.Constants;

import java.util.List;

import butterknife.ButterKnife;
import hr.foi.a2fit4u.R;
import hr.foi.a2fit4u.weight.WeightRecyclerAdapter;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class NFCFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<NFCItem> nfcItemList;

    private String NFCTag = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nfc,container,false);

        TextView textViewAddNFCDevice = view.findViewById(R.id.nfc_button_scan);


        textViewAddNFCDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScan(v);
            }
        });


        if(getArguments()!=null)
        {
            NFCTag = getArguments().getString(getString(R.string.bundle_nfc_tag));

            if(!NFCTag.isEmpty())
            {
                startNewDeviceDialog(view);
            }
        }

        return view;
    }

    private void startNewDeviceDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(R.string.nfc_dialog_new_entry);

        View viewInflated = LayoutInflater.from(view.getContext()).inflate(R.layout.nfc_input, (ViewGroup) getView(), false);
        EditText input = viewInflated.findViewById(R.id.nfc_dialog_input);

        builder.setView(viewInflated);

        builder.setPositiveButton(R.string.dialog_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String deviceName = input.getText().toString();
                addNewNFCDevice(deviceName);
                displayNFCDevices(view);
            }
        });

        builder.setNegativeButton(R.string.dialog_reject, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void addNewNFCDevice(String deviceName) {
        DBManager.getInstance().InsertData(Constants.DATA_TYPE_NFC,NFCTag,deviceName);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        displayNFCDevices(view);
    }

    private void displayNFCDevices(View view) {
        recyclerView = view.findViewById(R.id.nfc_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new NFCRecyclerAdapter(DBManager.getInstance().getNFCData(),view.getContext());



        recyclerView.setAdapter(adapter);
    }

    private void startScan(View v) {


        Intent intent = new Intent(v.getContext(),NFCScanActivity.class);
        startActivity(intent);

    }
}
