package nyc.c4q.cafelocator.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nyc.c4q.cafelocator.R;
import nyc.c4q.cafelocator.pojo.ResultsNear;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements RecycleViewOnItemClickListener {

    ListFragment.SelectedListener selectedListener;

    @BindView(R.id.cafe_list)
    RecyclerView cafeListCycleView;

    View view;

    public void setOnHeadlineSelectedListener(SelectedListener activity) {
        selectedListener = activity;
    }

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void updateArticleView(List<ResultsNear> resultsNear) {
        cafeListCycleView.setAdapter(new CafeListAdapter(resultsNear, this));
    }

    @Override
    public void onItemClick(String position) {

    }

    @Override
    public void onItemClick(View v, int adapterPosition) {
        selectedListener.onItemViewSelected(adapterPosition);
    }

    public void scrollToPosition(int position) {
        cafeListCycleView.scrollToPosition(position);
    }

    public interface SelectedListener {
        void onItemViewSelected(int position);
    }
}
