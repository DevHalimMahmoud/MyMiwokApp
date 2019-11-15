package com.example.android.miwok;

public class word {


    private String mDefaultTranslation;


    private String mMiwokTranslation;

    private int mImageResourceId;

    private int mAudioFile;


    public word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioFile) {
        this.mDefaultTranslation = defaultTranslation;
        this.mMiwokTranslation = miwokTranslation;
        this.mImageResourceId = imageResourceId;
        this.mAudioFile = audioFile;
    }


    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }


    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getmAudioFile() {
        return mAudioFile;
    }
}
