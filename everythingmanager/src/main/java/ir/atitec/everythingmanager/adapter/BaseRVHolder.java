package ir.atitec.everythingmanager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by white on 2016-08-17.
 */
public abstract class BaseRVHolder<T> extends RecyclerView.ViewHolder {

    protected Context context;
    public BaseRVHolder(View itemView,Context context) {
        super(itemView);
        this.context=context;

    }

    public abstract void fill(Context context,T t,int pos,int viewType,Object...objects);

}
