package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class colorsFragment extends Fragment {

    private MediaPlayer mAudioFile;
    private AudioManager mAudioManger;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mAudioFile.pause();
                mAudioFile.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mAudioFile.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mComplet = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    public colorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        mAudioManger = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<word> words = new ArrayList<>();

        words.add(new word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));


        ArrayAdapter adapter = new wordAdapter(getActivity(), words, R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word word = words.get(position);
                releaseMediaPlayer();
                int result = mAudioManger.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mAudioFile = MediaPlayer.create(getActivity(), word.getmAudioFile());
                    mAudioFile.start();
                    mAudioFile.setOnCompletionListener(mComplet);
                }
            }
        });

        return rootView;
    }



    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mAudioFile != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mAudioFile.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mAudioFile = null;
            mAudioManger.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
