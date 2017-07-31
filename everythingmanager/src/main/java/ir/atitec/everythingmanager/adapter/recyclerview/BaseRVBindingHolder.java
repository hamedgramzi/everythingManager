package ir.atitec.everythingmanager.adapter.recyclerview;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by white on 2016-08-17.
 */
public abstract class BaseRVBindingHolder<T> extends RecyclerView.ViewHolder {
    private View itemView;
    private ViewDataBinding binding;
    private Context context;
    private Object[] object;

    public BaseRVBindingHolder(ViewDataBinding binding, Context context) {
        super(binding.getRoot());
        this.binding = binding;
        this.context = context;
        itemView = binding.getRoot();
    }

    public abstract void fill(T t, int pos, int viewType);

    public View getItemView() {
        return itemView;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public Context getContext() {
        return context;
    }

    public Object[] getObject() {
        return object;
    }

    public void setObject(Object[] object) {
        this.object = object;
    }
}
