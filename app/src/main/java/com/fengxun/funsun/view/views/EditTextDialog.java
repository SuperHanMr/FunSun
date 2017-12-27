package com.fengxun.funsun.view.views;

import android.annotation.SuppressLint;
import android.app.Dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.fengxun.funsun.R;
import com.fengxun.funsun.model.KEY;
import com.fengxun.funsun.model.bean.CommentContentBean;
import com.fengxun.funsun.model.request.NetworkReuset;
import com.fengxun.funsun.model.request.RequestUrl;
import com.fengxun.funsun.model.request.onCallBack;
import com.fengxun.funsun.utils.LogUtils;
import com.lzy.okgo.model.HttpParams;

import okhttp3.Call;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/8.
 * Holle Android
 */

public class EditTextDialog extends DialogFragment implements TextView.OnEditorActionListener {


    //点击发表，内容不为空时的回调
    public SendBackListener sendBackListener;



    public interface  SendBackListener{
        void sendBack();
    }

    private EditText inputDlg;
    private Dialog dialog;


    private String contnetId;
    private String userId;



    @SuppressLint("ValidFragment")
    public EditTextDialog(SendBackListener sendBackListener){
        this.sendBackListener=sendBackListener;
    }


    public EditTextDialog (String contentId,String userId,SendBackListener listener){
        this.contnetId = contentId;
        this.userId = userId;
        this.sendBackListener = listener;


    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), R.style.BottomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        View contentview = View.inflate(getActivity(), R.layout.comment_dialog_layout, null);
        dialog.setContentView(contentview);
        dialog.setCanceledOnTouchOutside(true);
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.alpha = 1;
        lp.dimAmount = 0.5f;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        inputDlg = (EditText) contentview.findViewById(R.id.dialog_comment_content);
        inputDlg.setOnEditorActionListener(this);
        inputDlg.setFocusable(true);
        inputDlg.setFocusableInTouchMode(true);
        inputDlg.requestFocus();
        return dialog;

    }



    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId== EditorInfo.IME_ACTION_SEND){
            String content = inputDlg.getText().toString().trim();
            sendCommentContent(content);
        }
        return true;
    }


    private void  sendCommentContent(String context){
        HttpParams params = new HttpParams();
        params.put("is_first",1);
        params.put("content_id",contnetId);
        params.put("comment_direction",1);
        params.put("comment_content",context);
        params.put("content_publish_user_id",userId);
        NetworkReuset.getInstance().PostReuset(RequestUrl.COMMENTCONTENT, params, new onCallBack<CommentContentBean>(getActivity()) {
            @Override
            public void onSucceed(CommentContentBean commentContentBean, Call call, String string) {
                LogUtils.e(string);
                sendBackListener.sendBack();
            }
        });


    }

}
