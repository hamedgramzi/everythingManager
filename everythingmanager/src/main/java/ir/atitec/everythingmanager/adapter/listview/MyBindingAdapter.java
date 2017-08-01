package ir.atitec.everythingmanager.adapter.listview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Hamed Ghayour on 21/11/2015.
 */
public class MyBindingAdapter<T> extends ArrayAdapter<T> {

    private Class<? extends MyBindingViewHolder> Holder;
    private int res;
    private Context context;
    private Object[] objects;
    private LayoutInflater inflater;


    /**
     * @param context
     * @param resource address of resouce in layout file
     * @param holder   you must write class extend MyViewHolder and pass the class here
     */
    public MyBindingAdapter(Context context, int resource, Class<? extends MyBindingViewHolder> holder) {
        super(context, resource);
        this.context = context;
        myInit(holder, resource);
    }

    /**
     * @param context
     * @param resource address of resouce in layout file
     * @param items    items you want see in your list
     * @param holder   you must write class extend MyViewHolder and pass the class here
     */
    public MyBindingAdapter(Context context, int resource, Class<? extends MyBindingViewHolder> holder, List<T> items) {
        super(context, resource, items);
        this.context = context;
        myInit(holder, resource);

    }

    /**
     * @param context
     * @param resource address of resouce in layout file
     * @param items    items you want see in your list
     * @param holder   you must write class extend MyViewHolder and pass the class here
     */
    public MyBindingAdapter(Context context, int resource, Class<? extends MyBindingViewHolder> holder, T[] items) {
        super(context, resource, items);
        this.context = context;
        myInit(holder, resource);
    }

    private void myInit(Class<? extends MyBindingViewHolder> holder, int res) {
        this.Holder = holder;
        this.res = res;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * if you want pass data to fill method, call this method and pass it anything you want
     * you must cast this objects in fill method to waht ever you like
     *
     * @param objects anything you want
     */
    public void setObjects(Object... objects) {
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyBindingViewHolder h;
        if (convertView == null) {
            ViewDataBinding binding = DataBindingUtil.inflate(inflater, res, parent, false);

            try {
                h = Holder.getConstructor(Context.class, View.class, ArrayAdapter.class).newInstance(context, binding, this);
                h.setObjects(objects);
            } catch (Exception e) {
                return binding.getRoot();
            }
            h.fill(getItem(position), position);
            convertView = binding.getRoot();
            //FontManager.instance().setTypeface(convertView);
            convertView.setTag(h);
        } else {
            h = (MyBindingViewHolder) convertView.getTag();
            h.fill(getItem(position), position);
        }

        return convertView;
    }

}
