package ir.atitec.everythingmanager.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by white on 2016-08-17.
 */
public abstract class BaseRVBindingHolder<T> extends RecyclerView.ViewHolder {
    protected ViewDataBinding binding;
    protected Context context;

    public BaseRVBindingHolder(ViewDataBinding binding, Context context) {
        super(binding.getRoot());
        this.binding = binding;
        this.context = context;

    }

    public abstract void fill(Context context, T t, int pos, int viewType, Object... objects);

}
