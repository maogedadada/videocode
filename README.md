
#About using ffmpeg on android
+ A variety of audio formats (: M4A, AMR, AAC, WMA, WAV, FLAC, etc.) turn MP3
+ Video muted
+ Several audio merge(put together)

##how to use(so easy)

 	/** add commands
     * AMR
     * AAC,
     * M4A,
     * WMA,
     * WAV,
     * FLAC to MP3
     */
    public static String[] amr(String amr, String outputUrl) {
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
