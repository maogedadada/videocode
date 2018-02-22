package com.videocode.videos;

/**
 * Created by Sl on 2018/2/22.
 */

 public interface FFmpegCallback {
    void onSuccess(String message);

    void onFailure(String message);
}
