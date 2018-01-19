package com.ha.cjy.mvpdemo.Base;

import com.ha.cjy.mvpdemo.Model.Manager.DataManager;

/**
 * 通用的业务逻辑
 * -将Presenter与Activity绑定，与其生命周期绑定，避免出现内存泄漏
 * -解绑
 * Created by cjy on 18/1/19.
 */

public abstract class BasePresenter {
    public IBaseInterface mView;

    public DataManager getDataManager(){
        return DataManager.getInstance();
    }

    public void attachView(IBaseInterface view){
        this.mView = view;
    }

    public void destoryView(){
        if (this.mView == null)
            return;
        this.mView = null;
    }

    /**
     * 获取数据
     * @return
     */
    public abstract void getData();
}
