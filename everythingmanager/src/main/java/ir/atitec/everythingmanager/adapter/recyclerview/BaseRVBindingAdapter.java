package ir.atitec.everythingmanager.adapter.recyclerview;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
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


    public boolean removeItem(MODEL model) {
        int index = items.indexOf(model);
        if (index != -1) {
            items.remove(model);
            notifyItemRemoved(index);
            return true;
        }
        return false;
    }

    public MODEL removeItem(int pos) {
        MODEL m = items.remove(pos);
        notifyItemRemoved(pos);
        return m;
    }

    public void addItem(MODEL model) {
        items.add(model);
        notifyItemInserted(items.size()-1);
//        notifyDataSetChanged();
    }

    public void addItem(MODEL model, int position) {
        items.add(position, model);
        notifyItemInserted(position-1);
    }

    public boolean updateItem(MODEL model) {
        int index;
        if((index = items.indexOf(model)) != -1){
            replaceItem(model,index);
            return true;
        }
        return false;
    }

    public void replaceItem(MODEL model, int pos) {
        if (pos > items.size()) {
            items.add(model);
        } else {
            items.remove(pos);
            items.add(pos, model);
        }
        notifyItemChanged(pos);
//        notifyDataSetChanged();
    }

    public void removeAll() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<MODEL> list) {
        int size = items.size();
        items.addAll(list);
        notifyItemRangeInserted(size,list.size());
    }

    public void addAll(MODEL... list) {
        int size = items.size();

        if(list != null){
            items.addAll(Arrays.asList(list));
            notifyItemRangeInserted(size,list.length);
        }
    }

    public void addAll(int pos,List<MODEL> list) {
        items.addAll(pos,list);
        notifyItemRangeInserted(pos,list.size());
    }

    public void addAll(int pos,MODEL... list) {
        if(list != null){
            items.addAll(pos,Arrays.asList(list));
            notifyItemRangeInserted(pos,list.length);
        }
    }


    public ArrayList<MODEL> getItems() {
        return items;
    }
}

