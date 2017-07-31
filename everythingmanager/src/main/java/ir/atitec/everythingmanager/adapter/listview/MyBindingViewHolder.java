package ir.atitec.everythingmanager.adapter.listview;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.ArrayAdapter;

/**
 * Created by mr.hamed on 21/11/2015.
 */
public abstract class MyBindingViewHolder<T> {
    private View view;
    private Context context;
    private ArrayAdapter<T> arrayAdapter;
    private ViewDataBinding binding;
    private Object[] objects;

    public MyBindingViewHolder(Context context, ViewDataBinding binding, ArrayAdapter<T> arrayAdapter) {
        this.context = context;
        this.arrayAdapter = arrayAdapter;
        this.binding = binding;
        view = binding.getRoot();
    }

    public abstract void fill(T t, int pos);

    public View getView() {
        return view;
    }

    public Context getContext() {
        return context;
    }

    public ArrayAdapter<T> getArrayAdapter() {
        return arrayAdapter;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }
}
