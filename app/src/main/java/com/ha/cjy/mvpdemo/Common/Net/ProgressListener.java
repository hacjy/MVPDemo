package com.ha.cjy.mvpdemo.Common.Net;

/**
 * 下载进度监听器
 * Created by cjy on 18/1/24.
 */

public interface ProgressListener {
    /**
     * @param progress     已经下载或上传字节数
     * @param total        总字节数
     * @param done         是否完成
     */
    void onProgress(long progress, long total, boolean done);
}
