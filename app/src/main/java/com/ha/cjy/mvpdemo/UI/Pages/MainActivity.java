package com.ha.cjy.mvpdemo.UI.Pages;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ha.cjy.mvpdemo.Base.BaseActivity;
import com.ha.cjy.mvpdemo.Model.Entity.MovieEntity;
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

        mPresenter = new UserPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(Object data) {
        super.onSuccess(data);

        MovieEntity entity = null;
        if (data instanceof MovieEntity){
           entity = (MovieEntity) data;
        }

        if (mTvText != null && entity != null) {
            mTvText.setText(entity.toString());
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
