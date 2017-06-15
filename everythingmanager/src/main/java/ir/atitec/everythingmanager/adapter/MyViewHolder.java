package ir.atitec.everythingmanager.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

/**
 * Created by mr.hamed on 21/11/2015.
 */
public abstract class MyViewHolder<T> {
    protected View view;
    protected Context context;
    protected ArrayAdapter<T> arrayAdapter;
    public MyViewHolder(Context context, View view,ArrayAdapter<T> arrayAdapter){
        this.view = view;
        this.context = context;
        this.arrayAdapter = arrayAdapter;
    }

    public abstract void fill(T t,int pos,Object...objects);
}
