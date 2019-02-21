package nyc.c4q.cafelocator.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.cafelocator.R;
import nyc.c4q.cafelocator.pojo.ResultsNear;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */

public class CafeListAdapter extends RecyclerView.Adapter<CafeHolder> {


    private final RecycleViewOnItemClickListener recycleViewOnItemClickListener;
    private List<ResultsNear> itemList;

    CafeListAdapter(List<ResultsNear> resultsNear, RecycleViewOnItemClickListener recycleViewOnItemClickListener) {
        this.itemList=resultsNear;
        this.recycleViewOnItemClickListener = recycleViewOnItemClickListener;
    }

    @NonNull
    @Override
    public CafeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cafe_list_view_holder,viewGroup,false);
        return new CafeHolder(view,recycleViewOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CafeHolder cafeHolder, int position) {
      cafeHolder.onBind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
