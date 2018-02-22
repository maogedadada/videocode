#中文版 (http://blog.csdn.net/maogedadada/article/details/79348704).

#About using ffmpeg on android
+ A variety of audio formats (: M4A, AMR, AAC, WMA, WAV, FLAC, etc.) turn MP3
+ Video muted
+ Several audio merge(put together)

##how to use(so easy)
###Several audio merge(put together)
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

###Video muted
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


### A variety of audio formats (: M4A, AMR, AAC, WMA, WAV, FLAC, etc.) turn MP3
 	/** add commands
     * AMR
     * AAC,
     * M4A,
     * WMA,
     * WAV,
     * FLAC to MP3
     */
    public static String[] amr(String audio, String outputUrl) {
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
