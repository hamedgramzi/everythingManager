package ir.atitec.everythingmanager.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Created by white on 2016-08-17.
 */
public class BaseRVAdapter<T extends BaseRVHolder, MODEL> extends RecyclerView.Adapter<BaseRVHolder> {
    private Context context;
    private int[] layout;
    private Class<T> holder;
    private ArrayList<MODEL> items;
    private Object[] objects;

    public BaseRVAdapter(Context context, Class<T> holder, ArrayList<MODEL> items, int... layout) {
        this.context = context;
        this.holder = holder;
        this.layout = layout;
        this.items = items;
    }

    public BaseRVAdapter(Context context, Class<T> holder, int... layout) {
        this(context, holder, new ArrayList<MODEL>(), layout);
    }


    @Override
    public BaseRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout[viewType], parent, false);
        try {
            BaseRVHolder b = holder.getConstructor(View.class, Context.class).newInstance(view, context);
            b.setObjects(objects);
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
    public void onBindViewHolder(BaseRVHolder holder, int position) {
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


}

