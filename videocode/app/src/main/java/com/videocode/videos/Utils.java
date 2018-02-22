package com.videocode.videos;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sl on 2018/2/22.
 */

public class Utils {
    public static void showLog(String text) {
        if (text != null) {
            Log.e("tests", text);
        }
    }

    public static void requestPermission(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, 0);
        }
    }

    /**
     * AMR
     * AAC,
     * M4A,
     * WMA,
     * WAV,
     * FLAC;转MP3
     */
    public static String[] audioToMp3(String amr, String outputUrl) {
        ArrayList<String> _commands = new ArrayList<>();
        _commands.add("-y");
        _commands.add("-i");
        _commands.add(amr);
        _commands.add(outputUrl);
        String[] commands = new String[_commands.size()];
        for (int i = 0; i < _commands.size(); i++) {
            commands[i] = _commands.get(i);
        }
        return commands;
    }

    /**
     * 视频消除声音
     */
    public static String[] makeVideoNoVoice(String videoUrl, String outputUrl) {
        ArrayList<String> _commands = new ArrayList<>();
        _commands.add("-y");
        _commands.add("-i");
        _commands.add(videoUrl);
        _commands.add("-vcodec");
        _commands.add("copy");
        _commands.add("-an");
        _commands.add(outputUrl);
        String[] commands = new String[_commands.size()];
        for (int i = 0; i < _commands.size(); i++) {
            commands[i] = _commands.get(i);
        }
        return commands;
    }

    /**
     * 多段音频合并(拼接)
     */
    public static String[] mergeAudio(List<String> voices, String outputUrl) {
        ArrayList<String> _commands = new ArrayList<>();
        _commands.add("-y");
        _commands.add("-i");
        String pathCmd = "";
        for (int i = 0; i < voices.size(); i++) {
            if (i == 0) {
                pathCmd = voices.get(i);
            } else {
                pathCmd = pathCmd + "|" + voices.get(i);
            }
        }
        _commands.add("concat:" + pathCmd);
        _commands.add("-acodec");
        _commands.add("copy");
        _commands.add(outputUrl);
        String[] commands = new String[_commands.size()];
        for (int i = 0; i < _commands.size(); i++) {
            commands[i] = _commands.get(i);
        }
        return commands;
    }
}
