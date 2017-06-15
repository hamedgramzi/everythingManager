package ir.atitec.everythingmanager.manager;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;

import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Hamed Ghayour on 9/22/2015.
 */
public class MyFragmentManager {

    private static HashMap<Class, MyFragmentManager> hashes = new HashMap<>();
    private FragmentActivity activity;
    private FragmentManager fm;
    private int myRes;
    private Integer resTitle;
    private MyFragmentChangable myFragmentChangable;

    /**
     * you should call this method at onCreate Activity
     *
     * @param activity
     * @param defaultResId     resource Id of FrameLayout you want fragment place there
     * @param resTitleTextview recource Id of title textview of activity, it can NULL
     */
    public static void init(FragmentActivity activity, int defaultResId, Integer resTitleTextview) {
        if (hashes == null) {
            hashes = new HashMap<>();
        }
        MyFragmentManager mfm = hashes.get(activity.getClass());
        if (mfm == null) {
            FrameLayout frameLayout = (FrameLayout) activity.findViewById(defaultResId);
            hashes.put(activity.getClass(), new MyFragmentManager(activity, defaultResId, resTitleTextview));
        }
    }

    public static MyFragmentManager instance(FragmentActivity activity) {
        MyFragmentManager mfm = hashes.get(activity.getClass());
        if (mfm == null) {
            throw new NullPointerException("you should call init() at onCreate activity");
        }
        return mfm;
    }

    private MyFragmentManager(final FragmentActivity activity, int first, Integer resTitleTextview) {
        this.activity = activity;
        myRes = first;
        resTitle = resTitleTextview;
        fm = activity.getSupportFragmentManager();
        //if (resTitle != null) {
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @SuppressLint("11")
            @Override
            public void onBackStackChanged() {
                Fragment fragment = fm.findFragmentById(myRes);
                /*    if (resTitle != null) {
                        ((TextView) activity.findViewById(resTitle)).setText(fragment.getTag());

                    }*/
                if (myFragmentChangable != null) {
                    myFragmentChangable.onChange(fragment);
                }

            }
        });
        //}
    }

    /**
     * if you want inform fragments stack changed, call this method
     *
     * @param myFragmentChangable
     */
    public void setMyFragmentChangable(MyFragmentChangable myFragmentChangable) {
        this.myFragmentChangable = myFragmentChangable;
    }

    /**
     * if you have main fragment, so it's not changed in life cycle of app -> call this method
     *
     * @param frag
     * @param resStringTag address of fragment tag in string file
     * @return true if no fragment set before
     */
    public boolean setMainFragment(Fragment frag, int resStringTag) {
        Fragment fragment = fm.findFragmentById(myRes);
        if (fragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(myRes, frag, activity.getString(resStringTag));
            //ft.addToBackStack(tag);
            ft.commitAllowingStateLoss();
            return true;
        }
        return false;
    }

    /**
     * if you want change main fragment, call this method
     *
     * @param frag
     * @param resStringTag address of fragment tag in string file
     */
    public void replaceMainFragment(Fragment frag, int resStringTag) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(myRes, frag, activity.getString(resStringTag));
        ft.commitAllowingStateLoss();
    }

    /**
     * for add fragment at top of the stack
     *
     * @param frag
     * @param resStringTag address of fragment tag in string file
     */
    public void addFragment(final Fragment frag, int resStringTag) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(myRes, frag, activity.getString(resStringTag)).addToBackStack(activity.getString(resStringTag)).commitAllowingStateLoss();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    frag.getView().setClickable(true);
                } catch (Exception e) {

                }
            }
        }, 100);

    }

    /**
     * for remove fragment by tag name
     *
     * @param resStringTag address of fragment tag in string file
     */
    public void removeFragment(int resStringTag) {
        Fragment f = fm.findFragmentByTag(activity.getString(resStringTag));
        removeFragment(f);
    }

    /**
     * for remove fragment
     *
     * @param frag
     */
    public void removeFragment(Fragment frag) {
        fm.beginTransaction().remove(frag).commitAllowingStateLoss();
    }

    public static void destory() {
        if (hashes != null)
            hashes.clear();
        hashes = null;
    }

}
