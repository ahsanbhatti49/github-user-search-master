package com.test.ahsan.app.utils;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ahsan on 16/10/2018.
 */

public class EmptyUtil {

    public static boolean isValid(EditText... editTexts) {
        boolean valid = true;
        for (EditText editText : editTexts) {
            editText.setError(null);
            if (getString(editText).isEmpty()) {
                editText.setError("Field Required");
                valid = false;
            }
        }
        return valid;
    }

    public static boolean ifValidCnic(EditText editText) {
        boolean valid = true;
        editText.setError(null);
        String text = getString(editText);
        if (text.isEmpty()) {
            editText.setError("CNIC is required");
            valid = false;
        } else if (text.length() < 13) {
            editText.setError("Invalid CNIC");
            valid = false;
        }
        return valid;
    }

    public static boolean ifValidMobileNo(EditText editText) {
        boolean valid = true;
        editText.setError(null);
        String text = getString(editText);
        String tmp = text.substring(0, 2);
        if (text.isEmpty()) {
            editText.setError("Mobile no is required");
            valid = false;
        } else if (text.length() < 11) {
            editText.setError("Invalid mobile no");
            valid = false;
        } else if (!tmp.equals("03")) {
            editText.setError("Invalid mobile no");
            valid = false;
        }
        return valid;
    }

    public static String getString(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, message.length() > 30 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}
