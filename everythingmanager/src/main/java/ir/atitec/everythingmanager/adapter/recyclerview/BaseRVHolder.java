package ir.atitec.everythingmanager.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by white on 2016-08-17.
 */
public abstract class BaseRVHolder<T> extends RecyclerView.ViewHolder {
    private View itemView;
    private Context context;
    private Object[] objects;
    private RecyclerView.Adapter adapter;

    public BaseRVHolder(View itemView, Context context) {
        super(itemView);
        this.itemView = itemView;
        this.context = context;
    }

    public abstract void fill(T t, int pos, int viewType);


    public Context getContext() {
        return context;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }

    public View getItemView() {
        return itemView;
    }

    void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public RecyclerView.Adapter getAdapter(){
        return adapter;
    }
}
