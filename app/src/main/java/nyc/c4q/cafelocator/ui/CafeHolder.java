package nyc.c4q.cafelocator.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import nyc.c4q.cafelocator.R;
import nyc.c4q.cafelocator.pojo.ResultsNear;
import nyc.c4q.cafelocator.pojo.StoreHours;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */

class CafeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @BindView(R.id.store_image)
    ImageView store_image;

    @BindView(R.id.store_name)
    TextView storeName;
    @BindView(R.id.store_street)
    TextView storeStreet;
    @BindView(R.id.store_city)
    TextView storeCity;

    @BindView(R.id.store_status)
    TextView store_status;

    @BindView(R.id.store_current_status)
    TextView storeHours;

    private RecycleViewOnItemClickListener mListener;

    CafeHolder(@NonNull View itemView,RecycleViewOnItemClickListener listener) {
        super(itemView);
        mListener = listener;
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }


    void onBind(ResultsNear resultsNear) {
        Picasso.get()
                .load(resultsNear.imageUrl)
                .placeholder(R.mipmap.blue_bottle_coffee_cupping)
                .into(store_image);

        storeName.setText(resultsNear.name);
        storeStreet.setText(resultsNear.getStreet_address());
        storeCity.setText(resultsNear.getCity_address());

        delegateHoursList(resultsNear.getStoreHoursList());
    }

    private void delegateHoursList(List<StoreHours> storeHoursList) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;

        StoreHours temp = storeHoursList.get(dayOfWeek);

        if (!temp.isOpen()) {
            store_status.setText(R.string.closed);
        }

        String time = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(c.getTime());
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date open = null;
        Date closing = null;
        Date current = null;
        try {
            open = parseFormat.parse(temp.getOpenTime());
            closing = parseFormat.parse(temp.getCloseTime());
            current = parseFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert closing != null;
        assert current != null;
        if (isDuringStoreHours(open, closing, current)) {
            store_status.setText(R.string.open);
            String closing_at = itemView.getResources().getString(R.string.close_at);
            Log.e("day",temp.getDayOfWeek());
            storeHours.setText(String.format(closing_at, temp.getCloseTime()));
        } else {
            store_status.setText(R.string.closed);
            String nextOpenDay = findNextOpenDate(dayOfWeek+1, storeHoursList);
            storeHours.setText(nextOpenDay);
        }
    }

    private boolean isDuringStoreHours(Date open, Date closing, Date current) {
        return (open.before(current) && closing.after(current));
    }

    private String findNextOpenDate(int i, List<StoreHours> storeHoursList) {
        for (int j = i; j < storeHoursList.size(); j++) {
            if (storeHoursList.get(i).isOpen()) {
                String opens = itemView.getResources().getString(R.string.open_at);
                String shortenDay = storeHoursList.get(i).getDayOfWeek().substring(0,3);
                return String.format(opens,shortenDay,storeHoursList.get(i).getOpenTime());
            }
        }
        return itemView.getResources().getString(R.string.closed_until);
    }

    @Override
    public void onClick(View v) {
        mListener.onItemClick(v, getAdapterPosition());
    }
}
