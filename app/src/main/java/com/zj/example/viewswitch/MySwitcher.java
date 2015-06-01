package com.zj.example.viewswitch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 * create by zhengjiong
 * Date: 2015-05-13
 * Time: 21:43
 */
public class MySwitcher extends ViewSwitcher {
    private String tag = "MySwitcher";
    private Context mContext;
    private MainActivity mainActivity;
    private GestureDetector mGestureDetector;

    public MySwitcher(Context context) {
        this(context, null);
    }

    public MySwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mGestureDetector = new GestureDetector(context, new MyGestureDetector());

        getContext();
    }

    private void showNextPage() {
        setInAnimation(mContext, R.anim.slide_from_right_in);
        setOutAnimation(mContext, R.anim.slide_to_left_out);

        MainActivity.mCurrentIndex++;
        View switcherView = getNextView();
        TextView textView = (TextView) switcherView.findViewById(R.id.textview);
        textView.setText("View---->" + String.valueOf(MainActivity.mCurrentIndex));

        showNext();
    }

    private void showPrevPage() {
        setInAnimation(mContext, R.anim.slide_from_left_in);
        setOutAnimation(mContext, R.anim.slide_to_right_out);

        MainActivity.mCurrentIndex--;
        View switcherView = getNextView();
        TextView textView = (TextView) switcherView.findViewById(R.id.textview);
        textView.setText("View---->" + String.valueOf(MainActivity.mCurrentIndex));

        showPrevious();
    }

    @Override
    protected void onAnimationStart() {
        super.onAnimationStart();
        Log.e(tag, "onAnimationStart");
    }

    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        Log.e(tag, "onAnimationEnd");
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                break;
            default:
                mGestureDetector.onTouchEvent(event);
                break;
        }*/
        return mGestureDetector.onTouchEvent(event);
        //return super.onTouchEvent(event);
    }

    private class MyGestureDetector implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.e(tag, "onDown");
            //必須返回true,後面的move事件才能接收到
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        private boolean isShowPage = false;

        /**
         *
         * @param e1   e1 - The first down motion event that started the scrolling.(滾動的起始點)
         * @param e2   e2 - The move motion event that triggered the current onScroll.(當前滾動到的點)
         * @param distanceX  distanceX - The distance along the X axis that has been scrolled since the last call to onScroll. This is NOT the distance between e1 and e2.
         *                   目前滾動的位置和上一次滾動位置的距離,而不是e1(滾動的起始點)和e2(當前滾動到的點)之間的距離
         *
         * @param distanceY  distanceY - The distance along the Y axis that has been scrolled since the last call to onScroll. This is NOT the distance between e1 and e2.
         * @return
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e(tag, "onScroll distanceX=" + distanceX);
            if (!isShowPage) {
                if (distanceX > 40) {
                    isShowPage = true;
                    showNextPage();
                    //防止一次滾動多個界面
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isShowPage = false;
                        }
                    }, 300);
                }else if (distanceX < -40) {
                    isShowPage = true;
                    showPrevPage();
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isShowPage = false;
                        }
                    }, 300);
                }
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e(tag, "onFling");
            return false;
        }
    }
}
