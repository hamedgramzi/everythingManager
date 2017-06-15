package ir.atitec.everythingmanager.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by white on 2016-08-17.
 */
public class BaseRVBindingAdapter<T extends BaseRVBindingHolder> extends RecyclerView.Adapter<BaseRVBindingHolder> {
    private Context context;
    private int[] layout;
    private Class<T> holder;
    private ArrayList items;
    private Object[] objects;

    public BaseRVBindingAdapter(Context context, Class<T> holder, ArrayList items, int...layout){
        this.context=context;
        this.holder=holder;
        this.layout=layout;
        this.items=items;
    }

    @Override
    public BaseRVBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),layout[viewType],parent,false);
        //View view= LayoutInflater.from(parent.getContext()).inflate(layout[viewType], parent,false);
        try {
            return holder.getConstructor(ViewDataBinding.class,Context.class).newInstance(binding,context);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getItem(int position){
        if(position<items.size())
            return items.get(position);
        return null;
    }

    @Override
    public void onBindViewHolder(BaseRVBindingHolder holder, int position) {
        if(holder!=null)
            holder.fill(context,items.get(position),position,getItemViewType(position),getObjects());

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setObjects(Object...args) {
        this.objects = args;
    }

    public Object[] getObjects() {
        return objects;
    }
}

