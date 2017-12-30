package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.RoostBean;
import com.fengxun.funsun.model.listener.OnSelectorInterestListener;
import com.fengxun.funsun.utils.LogUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/30.
 * Holle Android
 */

public class SelectorInterestAdapter extends BaseAdapter {


    private Context context;
    private List<RoostBean> roostBeanList;

    private int[] bg={   R.drawable.selector_shenghuo,R.drawable.selector_changjiu
                        ,R.drawable.selector_erciyuan,R.drawable.selector_gaoxiao
                        ,R.drawable.selector_keji,R.drawable.selector_jianwen
                        ,R.drawable.selector_liuxue,R.drawable.selector_meishi
                        ,R.drawable.selector_shishang,R.drawable.selector_tiyv
                        ,R.drawable.selector_xiaoyuan,R.drawable.selector_xuexi
                        ,R.drawable.selector_yinyue,R.drawable.selector_yingshi
                        ,R.drawable.selector_youji,R.drawable.selector_youxi
                        ,R.drawable.selector_yundong,R.drawable.selector_zhuixing  };
    public SelectorInterestAdapter(Context context, List<RoostBean> roostBeanList) {
        this.context = context;
        this.roostBeanList = roostBeanList;
    }

    @Override
    public int getCount() {
        return roostBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return roostBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SelettorlItem item;
        if (convertView==null){
            convertView =  LayoutInflater.from(context).inflate(R.layout.selector_interest_item,parent,false);
            CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.selector_checkbox);
            TextView textView= (TextView) convertView.findViewById(R.id.selector_tex);
            item = new SelettorlItem(checkBox,textView);
            convertView.setTag(item);
        }else {
            item = (SelettorlItem) convertView.getTag();
        }

        
        //设置数据 头像和Name
        item.mIcon.setBackgroundResource(bg[position]);
        item.mName.setText(roostBeanList.get(position).interestName);
        item.mIcon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String interesId = roostBeanList.get(position).interesId;
                if (isChecked){
                   listener.addInterestIdListener(interesId);
                }else {
                    listener.deldeteInterestListenr(interesId);
                }
            }
        });

        return convertView;
    }


    private OnSelectorInterestListener listener;
    public void setSelectorIntereestListener(OnSelectorInterestListener listener){
        this.listener  = listener;
    }

    class SelettorlItem{
        protected CheckBox mIcon;
        protected TextView mName;
        public SelettorlItem(CheckBox icon, TextView name) {
            this.mName = name;
            this.mIcon = icon;
        }

    }




}
