package nyc.c4q.cafelocator.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import nyc.c4q.cafelocator.R;
import nyc.c4q.cafelocator.pojo.StoreHours;

/**
 * Created by jervon.arnoldd on 2/19/19.
 */

class HoursViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.day)
    TextView day;
    @BindView(R.id.hours)
    TextView hours;
    HoursViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    void onBind(StoreHours storeHours) {
        day.setText(storeHours.getDayOfWeek());
        String temp= storeHours.getOpenTime()+" to "+storeHours.getCloseTime();

        if (storeHours.getOpenTime()==null && storeHours.getCloseTime()==null){
            hours.setText(itemView.getResources().getString(R.string.closed));
        } else{
            hours.setText(temp);
        }
    }
}
