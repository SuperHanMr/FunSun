package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.bean.SchoolListBean;
import com.fengxun.funsun.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/8.
 * Holle Android
 */

public class LocationSchoolListAdapter extends RecyclerView.Adapter<LocationSchoolListAdapter.SchoolHodlerView>  {



    private Context context;
    private List< SchoolListBean.DataBean> mList;

    public LocationSchoolListAdapter(Context context) {
        this.context = context;
        mList = new ArrayList<>();
    }


    @Override
    public SchoolHodlerView onCreateViewHolder(ViewGroup parent, int viewType) {
        SchoolHodlerView hodlerView = new SchoolHodlerView(LayoutInflater.from(context).inflate(R.layout.recycler_item_school,parent,false));
        return hodlerView;
    }


    /**
     * @param position
     */
    @Override
    public void onBindViewHolder(SchoolHodlerView holder, final int position) {
        final SchoolListBean.DataBean dataBean = mList.get(position);
        String schoolName = dataBean.getName();
        holder.tvSchoolName.setText(schoolName);
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.oNSchoolNameListener(dataBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size()==0?0:mList.size();
    }

    public void setSchoolData(List<SchoolListBean.DataBean> mList){
        this.mList = mList;
        LogUtils.d(mList.size()+"");
        notifyDataSetChanged();
    }

    public class SchoolHodlerView extends RecyclerView.ViewHolder{
        private final TextView tvSchoolName;
        private final RelativeLayout rl;

        public SchoolHodlerView(View itemView) {
            super(itemView);
            tvSchoolName = (TextView) itemView.findViewById(R.id.school_name);
            rl = (RelativeLayout) itemView.findViewById(R.id.school_list_re);
        }
    }

    public void setOnSchoolNameListener(OnSchoolNameListener listener){
        this.listener = listener;
    }

    private OnSchoolNameListener listener;

    public interface OnSchoolNameListener{
        void oNSchoolNameListener(SchoolListBean.DataBean dataBean);
    }
}
