package com.example.hanyonghui.mylibrary;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by guorui.he on 2016/6/19.
 */
public class MenuItemAdapter_A extends BaseAdapter{


    private Context context;//运行上下文

    private LayoutInflater listContainer;  //视图容器

    private String[] items;

    public MenuItemAdapter_A(Context _context, String[] items){
        this.context = _context;
        this.listContainer = LayoutInflater.from(_context);
        this.items = items;
    }
    @Override
    public int getCount() {
        return this.items.length;
    }

    @Override
    public Object getItem(int position) {
        if(position >= items.length || position < 0) {
            return null;
        } else {
            return items[position];
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(convertView == null) {
            view = listContainer.inflate(R.layout.menu_item, null);
        }

        TextView textView = (TextView) view.findViewById(R.id.menu_item);
        textView.setText(items[position]);

        if(items.length == 1) {
            textView.setBackgroundResource(R.drawable.bottom_menu_btn_selector);

        } else if(position == 0) {
            textView.setBackgroundResource(R.drawable.bottom_menu_top_btn_selector);
        } else if(position < items.length - 1) {
            textView.setBackgroundResource(R.drawable.bottom_menu_mid_btn_selector);
        } else {
            textView.setBackgroundResource(R.drawable.bottom_menu_bottom_btn_selector);
        }



        if(position==0) {
            textView.setTextColor(ContextCompat.getColor(context, R.color.bottom_menu_btn_text_commom_color));
            textView.setEnabled(false);
        } else {
            textView.setTextColor(ContextCompat.getColor(context, R.color.bottom_menu_btn_text_stress_color));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   String s = items[position];
                    listener.setDiaLogListener(s);

                }
            });
        }
        return view;
    }

    private DialogListener listener;
    public interface DialogListener {
        void setDiaLogListener(String string);
    }

    public void setListener(DialogListener listener){
        this.listener = listener;
    }

}
