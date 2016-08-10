package com.cdh.mynote;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class ServiceChatHead extends Service implements View.OnTouchListener{
    private WindowManager windowManager;
    private ImageView chatImage;
    private WindowManager.LayoutParams params;
    private LayoutInflater inflater;

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        inflater = LayoutInflater.from(this);

        chatImage = new ImageView(this);
        chatImage.setImageResource(R.mipmap.ic_launcher);
        chatImage.setOnTouchListener(this);

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 0;
        params.y = 100;

        windowManager.addView(chatImage, params);
    }

    private int initialY, diffY;
    private int initialX, diffX;
    private float initialTouchX;
    private float initialTouchY;
    private boolean moved; // if it is a move or only click

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = params.x;
                initialY = params.y;
                initialTouchX = event.getRawX();
                initialTouchY = event.getRawY();
                moved = false;
                break;
            case MotionEvent.ACTION_UP:
                // if there is a click
                if(!moved) onClick(v);
                break;
            case MotionEvent.ACTION_MOVE:
                diffX = (int) (event.getRawX() - initialTouchX);
                diffY = (int) (event.getRawY() - initialTouchY);
                if(Math.abs(diffX) > 3 || Math.abs(diffY) > 3) moved = true;
                params.x = initialX + diffX;
                params.y = initialY + diffY;
                windowManager.updateViewLayout(chatImage, params);
                break;
        }
        return moved;
    }

    private void onClick(View v) {
        showPopup(v);
    }

    private void showPopup(View v) {
        final View view = inflater.inflate(R.layout.popup, null);
        Button button = (Button) view.findViewById(R.id.popup_button);

        final PopupWindow popupWindow = new PopupWindow(view,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast(((EditText) view.findViewById(R.id.popup_editText)).getText().toString());
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(v);
    }

    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(chatImage != null) windowManager.removeView(chatImage);
    }
}
