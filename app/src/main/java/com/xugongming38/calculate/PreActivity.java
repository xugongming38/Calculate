package com.xugongming38.calculate;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class PreActivity extends Activity {

    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pre);
        iv=(ImageView)findViewById(R.id.fullscreen_content);
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        animation.setAnimationListener(new RemoveAnimationListener());
        iv.setAnimation(animation);
    }

    private class RemoveAnimationListener implements Animation.AnimationListener {

        //动画效果执行完时remove

        public void onAnimationEnd(Animation animation) {

            System.out.println("onAnimationEnd");
            Intent intent=new Intent(PreActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        public void onAnimationRepeat(Animation animation) {

            System.out.println("onAnimationRepeat");

        }

        public void onAnimationStart(Animation animation) {

            System.out.println("onAnimationStart");

        }

    }

}
