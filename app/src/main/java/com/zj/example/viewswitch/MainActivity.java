package com.zj.example.viewswitch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

/**
 *
 * @author zj
 * @date 2015年5月13日15:46:02
 */
public class MainActivity extends ActionBarActivity {
    public static int mCurrentIndex = 0;
    private Button mBtnPrev;
    private Button mBtnNext;
    private MySwitcher mViewSwitcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mViewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                return View.inflate(MainActivity.this, R.layout.switch_view, null);
            }
        });

        initListener();
    }

    private void initListener() {
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewSwitcher.setInAnimation(MainActivity.this, R.anim.slide_from_right_in);
                mViewSwitcher.setOutAnimation(MainActivity.this, R.anim.slide_to_left_out);

                mCurrentIndex++;
                View switcherView = mViewSwitcher.getNextView();
                TextView textView = (TextView) switcherView.findViewById(R.id.textview);
                textView.setText("View---->" + String.valueOf(mCurrentIndex));
                mViewSwitcher.showNext();
            }
        });

        mBtnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewSwitcher.setInAnimation(MainActivity.this, R.anim.slide_from_left_in);
                mViewSwitcher.setOutAnimation(MainActivity.this, R.anim.slide_to_right_out);

                mCurrentIndex--;
                View switcherView = mViewSwitcher.getNextView();
                TextView textView = (TextView) switcherView.findViewById(R.id.textview);
                textView.setText("View----->" + String.valueOf(mCurrentIndex));
                mViewSwitcher.showPrevious();
            }
        });

    }

    private void initView() {
        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnPrev = (Button) findViewById(R.id.btn_prev);
        mViewSwitcher = (MySwitcher) findViewById(R.id.view_switcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
