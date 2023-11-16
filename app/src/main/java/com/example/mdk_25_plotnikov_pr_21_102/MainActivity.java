package com.example.mdk_25_plotnikov_pr_21_102;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private Resources resourcesLoader;
    private int[] sounds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resourcesLoader = getResources();
        createSoundPool();

        sounds = new int[6];
        sounds[0] = loadSound(R.raw.duck);
        sounds[1] = loadSound(R.raw.fall);
        sounds[2] = loadSound(R.raw.laugh);
        sounds[3] = loadSound(R.raw.nigger);
        sounds[4] = loadSound(R.raw.phone);
        sounds[5] = loadSound(R.raw.sad);

        findViewById(R.id.btnDuck).setOnClickListener(view ->   playSound(sounds[0]));
        findViewById(R.id.btnFall).setOnClickListener(view ->   playSound(sounds[1]));
        findViewById(R.id.btnLaugh).setOnClickListener(view ->  playSound(sounds[2]));
        findViewById(R.id.btnNigger).setOnClickListener(view -> playSound(sounds[3]));
        findViewById(R.id.btnPhone).setOnClickListener(view ->  playSound(sounds[4]));
        findViewById(R.id.btnSad).setOnClickListener(view ->    playSound(sounds[5]));
    }

    private void createSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder().
                setUsage(AudioAttributes.USAGE_GAME).
                setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();

        mSoundPool = new SoundPool.Builder().
                setAudioAttributes(attributes).build();
    }

    private int playSound(int sound) {
        Log.d("play","play");
        return mSoundPool.play(sound,1,1,1,0,1);
    }

    private int loadSound(int r_row_id) {
        AssetFileDescriptor afd;
        try {
            afd = resourcesLoader.openRawResourceFd(r_row_id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }
}