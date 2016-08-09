package com.cdh.mynote;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class ServiceChatHead extends Service implements View.OnTouchListener{
    private WindowManager windowManager;
    private ImageView chatImage;
    private WindowManager.LayoutParams params;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        chatImage = new ImageView(this);
        chatImage.setImageResource(R.mipmap.ic_launcher);

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        chatImage.setOnTouchListener(this);

        windowManager.addView(chatImage, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(chatImage != null) windowManager.removeView(chatImage);
    }

    private int initialX, diffX;
    private int initialY, diffY;
    private float initialTouchX;
    private float initialTouchY;
    private boolean moved;

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
                if(!moved) onClick();
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

    private void onClick() {
        Toast.makeText(this, "onClick", Toast.LENGTH_SHORT).show();
    }
}
