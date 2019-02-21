package nyc.c4q.cafelocator.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.cafelocator.R;
import nyc.c4q.cafelocator.pojo.StoreHours;

/**
 * Created by jervon.arnoldd on 2/19/19.
 */

public class HoursAdaptor extends RecyclerView.Adapter<HoursViewHolder>{

    private List<StoreHours> storeHoursList;


    HoursAdaptor(List<StoreHours> storeHoursList) {
        this.storeHoursList = storeHoursList;
    }

    @NonNull
    @Override
    public HoursViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hour_item,viewGroup,false);
        return new HoursViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursViewHolder hoursViewHolder, int i) {
       hoursViewHolder.onBind(storeHoursList.get(i));
    }

    @Override
    public int getItemCount() {
        return storeHoursList.size();
    }
}
