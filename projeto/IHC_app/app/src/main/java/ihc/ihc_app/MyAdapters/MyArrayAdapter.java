package ihc.ihc_app.MyAdapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ihc.ihc_app.R;

/**
 * Created by MSI on 12/05/2018.
 */

public class MyArrayAdapter<E> extends ArrayAdapter {
    String hiden_obj;
    int index;
    List<String> opc;


    public MyArrayAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public MyArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects, List<String> opcoes) {
        super(context, resource, objects);
        opc = opcoes;

    }

    /*public void hide(String s){
        String h = null;
        if(hiden_obj != null){
           h = hiden_obj;
        }
        hiden_obj = s;
        this.remove(s);
        this.add(h);
        this.sort;
        ///return show_opc.indexOf(s);
    }*/
    public void hide(String s){
        String h = "";
        if(hiden_obj != null){
            h = hiden_obj;
            this.add(h);
        }
        hiden_obj = s;
        this.remove(s);
        /*this.sort(new Comparator<String>() {
            @Override
            public int compare(String obj1, String obj2) {
                if (obj1 == null) {
                    return -1;
                }
                if (obj2 == null) {
                    return 1;
                }
                if (obj1.equals(obj2)) {
                    return 0;
                }
                return obj1.compareTo(obj2);
            }
        });*/
    }

    @Override
    public boolean isEnabled(int position) {
        if (position == 0) {
            // Disable the first item from Spinner
            // First item will be use for hint
            return false;
        } else {
            return true;
        }
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view;
        if (position == 0) {
            // Set the hint text color gray
            tv.setTextColor(Color.GRAY);
        } else {
            tv.setTextColor(Color.BLACK);
        }
        return view;
    }
}
