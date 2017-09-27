package ir.atitec.everythingmanager.adapter.recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by white on 2016-08-17.
 */
public class BaseRVBindingAdapter<T extends BaseRVBindingHolder, MODEL> extends RecyclerView.Adapter<BaseRVBindingHolder> {
    private Context context;
    private int[] layout;
    private Class<T> holder;
    private ArrayList<MODEL> items;
    private Object[] objects;

    public BaseRVBindingAdapter(Context context, Class<T> holder, ArrayList<MODEL> items, int... layout) {
        this.context = context;
        this.holder = holder;
        this.layout = layout;
        this.items = items;
    }

    @Override
    public BaseRVBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layout[viewType], parent, false);
        //View view= LayoutInflater.from(parent.getContext()).inflate(layout[viewType], parent,false);
        try {
            BaseRVBindingHolder b = holder.getConstructor(ViewDataBinding.class, Context.class).newInstance(binding, context);
            b.setObject(objects);
            b.setAdapter(this);
            return b;
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

    public MODEL getItem(int position) {
        if (position < items.size())
            return items.get(position);
        return null;
    }

    @Override
    public void onBindViewHolder(BaseRVBindingHolder holder, int position) {
        if (holder != null)
            holder.fill(items.get(position), position, getItemViewType(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setObjects(Object... args) {
        this.objects = args;
    }

    public Object[] getObjects() {
        return objects;
    }


    public void removeItem(MODEL model) {
        items.remove(model);
        notifyDataSetChanged();
    }

    public void removeItem(int pos) {
        items.remove(pos);
        notifyDataSetChanged();
    }

    public void addItem(MODEL model) {
        items.add(model);
        notifyDataSetChanged();
    }

    public void addItem(MODEL model, int position) {
        items.add(position, model);
        notifyDataSetChanged();
    }

    public void replaceItem(MODEL model, int pos) {
        if (pos > items.size()) {
            items.add(model);
        } else {
            items.remove(pos);
            items.add(pos, model);
        }
        notifyDataSetChanged();
    }

    public void removeAll() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<MODEL> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(MODEL... list) {
        for (int i = 0; i < list.length; i++) {
            items.add(list[i]);
        }
        notifyDataSetChanged();
    }


    public ArrayList<MODEL> getItems() {
        return items;
    }
}

