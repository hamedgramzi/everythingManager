package ir.atitec.everythingmanager.utility;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Date;

import ir.anamsoftware.persiandateultimate.ManamPDUltimate;

/**
 * Created by hamed on 6/15/2017.
 */

public class Util {
    public static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }
    }

    public static String getPersianDate(long timeMillies, boolean isFull) {
//        if (dateTime == null) {
//            return "";
//        }
        Date date = new Date(timeMillies);
        ManamPDUltimate m = new ManamPDUltimate(date.getYear() + 1900, date.getMonth()+1, date.getDate());
        if (!isFull)
            return m.getPersianShortDate();
        return m.getPersianLongDate();
    }

    public static boolean setClipboard(Context context, String text) {
        try {
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                clipboard.setText(text);
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
                clipboard.setPrimaryClip(clip);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void shareData(Context activity, String data, String titleDialog) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, data);
        activity.startActivity(Intent.createChooser(sharingIntent, titleDialog));
    }

    public static String readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static int getWindowWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
            return size.x;
        } else {
            return display.getWidth();
        }
    }

    public static int getWindowHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

            display.getSize(size);
            return size.y;
        } else {
            return display.getHeight();
        }
    }

    public static  int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static String convertNumbersToPersian(String str)
    {
        String answer = str;
        answer = answer.replace("1","١");
        answer = answer.replace("2","٢");
        answer = answer.replace("3","٣");
        answer = answer.replace("4","٤");
        answer = answer.replace("5","٥");
        answer = answer.replace("6","٦");
        answer = answer.replace("7","٧");
        answer = answer.replace("8","٨");
        answer = answer.replace("9","٩");
        answer = answer.replace("0","٠");
        return answer;
    }

    public static String convertNumbersToEnglish(String str) {
        String answer = str;
        answer = answer.replace("۱", "1");
        answer = answer.replace("۲", "2");
        answer = answer.replace("۳", "3");
        answer = answer.replace("۴", "4");
        answer = answer.replace("۵", "5");
        answer = answer.replace("۶", "6");
        answer = answer.replace("۷", "7");
        answer = answer.replace("۸", "8");
        answer = answer.replace("۹", "9");
        answer = answer.replace("۰", "0");
        return answer;
    }
}
