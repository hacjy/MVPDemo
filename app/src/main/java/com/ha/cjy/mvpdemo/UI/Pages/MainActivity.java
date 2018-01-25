package com.ha.cjy.mvpdemo.UI.Pages;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ha.cjy.mvpdemo.Base.BaseActivity;
import com.ha.cjy.mvpdemo.Common.Utils.PermissionUtils;
import com.ha.cjy.mvpdemo.Model.Entity.GetMovieListResultData;
import com.ha.cjy.mvpdemo.Presenter.UserPresenter;
import com.ha.cjy.mvpdemo.R;

public class MainActivity extends BaseActivity {
    private TextView mTvText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                ((UserPresenter)mPresenter).getMovieList(0,10);
            }
        });
        mTvText = (TextView) findViewById(R.id.tv_content);
        mTvText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.layout_login, new MovieFragment(), "");
                transaction.commit();
            }
        });

        mPresenter = new UserPresenter();
        mPresenter.attachView(this);

        if (!PermissionUtils.check(PermissionUtils.SDCARD)){
            PermissionUtils.request(this,100,new String[]{PermissionUtils.SDCARD});
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        GetMovieListResultData entity = null;
        if (data instanceof GetMovieListResultData){
           entity = (GetMovieListResultData) data;
        }

        if (mTvText != null && entity != null) {
            mTvText.setText("返回"+entity.getMovieList().size()+"条数据");
        }
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
