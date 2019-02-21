package nyc.c4q.cafelocator.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nyc.c4q.cafelocator.R;
import nyc.c4q.cafelocator.pojo.ResultsNear;
import nyc.c4q.cafelocator.pojo.StoreHours;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    @BindView(R.id.detail_store_image)
    ImageView store_image;
    @BindView(R.id.detail_store_name)
    TextView storeName;
    @BindView(R.id.detail_store_address)
    TextView storeAddress;
    @BindView(R.id.hours_list)
    RecyclerView hour_recycler;

    ResultsNear storeHolder;

    View view;


    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        if (storeHolder != null) {
            Picasso.get()
                    .load(storeHolder.imageUrl)
                    .fit()
                    .placeholder(R.mipmap.blue_bottle_coffee_cupping)
                    .into(store_image);



            storeName.setText(storeHolder.name);
            String resource = view.getResources().getString(R.string.full_store_address);
            String address = String.format(resource, storeHolder.getStreet_address(), storeHolder.getCity_address());
            storeAddress.setText(address);

            List<StoreHours> temp =storeHolder.getStoreHoursList();
            temp.add(temp.size() , temp.get(0));
            temp.remove(0);
            hour_recycler.setAdapter(new HoursAdaptor(temp));
        }
        return view;
    }

    public void passStoreSelected(ResultsNear resultsNear) {
        storeHolder = resultsNear;
    }
}
