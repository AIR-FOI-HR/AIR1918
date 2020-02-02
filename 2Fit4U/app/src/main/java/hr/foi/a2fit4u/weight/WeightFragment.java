package hr.foi.a2fit4u.weight;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import hr.foi.a2fit4u.R;

public class WeightFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<WeightItem> weightItemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this,view);

        recyclerView = view.findViewById(R.id.weight_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        weightItemList = new ArrayList<>();

        for(int i=0;i<=10;i++)
        {
            weightItemList.add(new WeightItem("BOK "+i,"2020-02-0"+i));
        }

        adapter = new WeightRecyclerAdapter(weightItemList,view.getContext());

        recyclerView.setAdapter(adapter);
    }
}
