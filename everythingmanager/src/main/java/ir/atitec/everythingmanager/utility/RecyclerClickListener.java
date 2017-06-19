package ir.atitec.everythingmanager.utility;

import android.view.View;

/**
 * Created by hamed on 10/1/2016.
 */
public interface RecyclerClickListener {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
}
