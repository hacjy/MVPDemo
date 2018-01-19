package com.ha.cjy.mvpdemo.Model.Manager;

import java.util.Map;

/**
 * 数据管理器的接口
 * Created by cjy on 18/1/19.
 */

public interface IDataManager {
    void getData(Map<String,Object> params,Class cls,DataResponseCallBack callBack);
}
