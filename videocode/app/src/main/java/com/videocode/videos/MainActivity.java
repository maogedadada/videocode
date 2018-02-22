package com.videocode.videos;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements BaseQuickAdapter.OnItemClickListener {

    private String mDirectory;
    private RecyclerView mRecyclerView;
    public KProgressHUD mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDirectory = Environment.getExternalStorageDirectory().toString();
        Utils.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        Utils.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        mRecyclerView = (RecyclerView) findViewById(R.id.listview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        MyAdapter adapter = new MyAdapter(getData());
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String text = adapter.getData().get(position).toString();
        String[] cmds = null;
        if ("多种音频格式（M4A,AMR,AAC,WMA,WAV,FLAC等）转MP3".equals(text)) {
            cmds = Utils.amr(mDirectory + "/66.amr", mDirectory + "/999.mp3");
        } else if ("视频消音".equals(text)) {
            cmds = Utils.makeVideoNoVoice(mDirectory + "/66.mp4", mDirectory + "/999.mp4");
        } else if ("多段音频合并(拼接)".equals(text)) {
            List<String> lists = new ArrayList<>();
            lists.add(mDirectory + "/66.mp3");
            lists.add(mDirectory + "/67.mp3");
            cmds = Utils.mergeAudio(lists, mDirectory + "/999.mp3");
        }
        showProgress();
        FFmpegUtils.getResult(MainActivity.this, cmds, new FFmpegCallback() {
            @Override
            public void onSuccess(String message) {
                Utils.showLog(message);
                toast("onSuccess");
                closeProgress();
            }

            @Override
            public void onFailure(String message) {
                Utils.showLog(message);
                toast("onFailure");
                closeProgress();
            }
        });
    }


    class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdapter(@Nullable List<String> data) {
            super(R.layout.item_text, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.text, item);
        }
    }

    public List<String> getData() {
        List<String> list = new ArrayList<>();
        list.add("多种音频格式（M4A,AMR,AAC,WMA,WAV,FLAC等）转MP3");
        list.add("视频消音");
        list.add("多段音频合并(拼接)");
        return list;
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showProgress() {
        mProgressBar = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(1)
                .setDimAmount(0.5f)
                .show();
    }

    public void closeProgress() {
        if (mProgressBar != null) {
            mProgressBar.dismiss();
            mProgressBar = null;
        }
    }
}
