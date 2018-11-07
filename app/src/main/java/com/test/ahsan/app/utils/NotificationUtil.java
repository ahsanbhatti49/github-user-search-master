package com.test.ahsan.app.utils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Toast;

import com.test.ahsan.app.R;

/**
 * Created by ahsan on 28/09/2018.
 */

public class NotificationUtil {
    private static NotificationUtil notificationUtils;
    private Dialog dialog = null;

    public static NotificationUtil getInstance() {
        if (notificationUtils == null)
            notificationUtils = new NotificationUtil();

        return notificationUtils;
    }

    public void showErrorAlert(Context context, String title, String data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(data);
        builder.setPositiveButton("Ok", (dialog, which) -> dialog.dismiss()).show();
    }

    public void showLoading(Context context) {
        dialog = new Dialog(context);
        if (!dialog.isShowing()) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.loader_dialog);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();
        }

    }

    public void showDecisionAlert(Context context, String title, String data, final DecisionListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(data);
        builder.setPositiveButton("Ok", (dialog, which) -> {
            listener.onDecide(true);
            dialog.dismiss();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            listener.onDecide(false);
            dialog.dismiss();
        }).show();
    }

    @SuppressLint("ShowToast")
    public void showToast(Context context, String msg , int length){
        Toast.makeText(context,msg,length);
    }

    public void dismissLoading() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public interface DecisionListener {
        void onDecide(boolean decide);
    }

    public interface ChoiceListener {
        void onChoice(String choice);
    }
}
