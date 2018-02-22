package com.videocode.videos;

import android.content.Context;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;

/**
 * Created by Sl on 2018/2/22.
 */

public class FFmpegUtils {
    public static void getResult(final Context context, final String[] arr, final FFmpegCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FFmpeg.getInstance(context).execute(arr, new FFmpegExecuteResponseHandler() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onProgress(String message) {
                        }

                        @Override
                        public void onSuccess(String message) {
                            callback.onSuccess(message);
                        }

                        @Override
                        public void onFailure(String message) {
                            callback.onFailure(message);
                        }

                        @Override
                        public void onFinish() {
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Utils.showLog(e.toString());
                }
            }
        }).start();
    }
}
