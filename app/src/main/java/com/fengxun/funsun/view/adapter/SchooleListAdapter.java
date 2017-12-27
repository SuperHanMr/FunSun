package com.fengxun.funsun.view.adapter;



import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.SchoolBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/13.
 * Holle Android
 */

public class SchooleListAdapter extends BaseAdapter {

    private Context context;

    private List<SchoolBean.DataBean> list;

   private LinearLayout.LayoutParams params;
    private LayoutInflater mInflater;

    public SchooleListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
    }





    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SchoolItem schoolItem;
        if (convertView==null){
            convertView = mInflater.inflate(R.layout.item_schoole_list,parent,false);

           CircleImageView head = (CircleImageView) convertView.findViewById(R.id.item_school_list_head);
           TextView tvName = (TextView) convertView.findViewById(R.id.item_school_list_name);
            schoolItem = new SchoolItem(head,tvName);
            convertView.setTag(schoolItem);
        }else {
            schoolItem = (SchoolItem) convertView.getTag();
        }


        //设置数据 头像和Name


        Picasso.with(context).load(list.get(position).getTag_img()).into(schoolItem.mIcon);
        schoolItem.mName.setText(list.get(position).getTag_name());


        return convertView;


    }

    public void setList(List<SchoolBean.DataBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    class SchoolItem{
        protected CircleImageView mIcon;
        protected TextView mName;


        public SchoolItem(CircleImageView icon, TextView name)
        {
            this.mName = name;
            this.mIcon = icon;
        }

    }

}
