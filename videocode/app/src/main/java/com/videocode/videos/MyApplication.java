package com.videocode.videos;

import android.app.Application;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.FFmpegLoadBinaryResponseHandler;

/**
 * Created by Sl on 2018/2/22.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            FFmpeg.getInstance(this).loadBinary(new FFmpegLoadBinaryResponseHandler() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess() {
                    Utils.showLog("success");
                }

                @Override
                public void onFailure() {
                    Utils.showLog("onFailure");
                }

                @Override
                public void onFinish() {

                }
            });
        } catch (Exception e) {
            Utils.showLog(e.toString());
        }
    }
}
