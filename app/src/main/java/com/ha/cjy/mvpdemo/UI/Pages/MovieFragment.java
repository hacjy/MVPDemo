package com.ha.cjy.mvpdemo.UI.Pages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ha.cjy.mvpdemo.Base.BaseFragment;
import com.ha.cjy.mvpdemo.Presenter.UserPresenter;
import com.ha.cjy.mvpdemo.R;

/**
 * 登录页面
 * Created by cjy on 18/1/24.
 */

public class MovieFragment extends BaseFragment {
    private Button mBtnAddMovie;
    private Button mBtnDownload;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mBtnAddMovie = view.findViewById(R.id.btn_login);
        mBtnDownload = view.findViewById(R.id.btn_download);
        mBtnAddMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((UserPresenter)mPresenter).addMovie();
            }
        });
        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((UserPresenter)mPresenter).download();
            }
        });

        mPresenter = new UserPresenter();
        mPresenter.attachView(this);

        return view;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fg_movie;
    }




}
