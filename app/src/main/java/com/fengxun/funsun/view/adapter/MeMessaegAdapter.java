package com.fengxun.funsun.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fengxun.funsun.R;
import com.fengxun.funsun.view.activity.CommentariesPromtingActivity;
import com.fengxun.funsun.view.activity.ToViewPromtingActivity;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/10.
 * Holle Android
 */

public class MeMessaegAdapter  extends RecyclerView.Adapter<MeMessaegAdapter.MessageViewHolder> {


    private Context context;
    public MeMessaegAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        MessageViewHolder holder = new MessageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_system_message_recyclercview,parent,false));

        return null;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ToViewPromtingActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{

        public MessageViewHolder(View itemView) {
            super(itemView);
        }
    }
}
