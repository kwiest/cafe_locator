package nyc.c4q.cafelocator.ui;

import android.view.View;

/**
 * Created by jervon.arnoldd on 2/18/19.
 */

public interface RecycleViewOnItemClickListener {
    void onItemClick(String position);

    void onItemClick(View v, int adapterPosition);
}

