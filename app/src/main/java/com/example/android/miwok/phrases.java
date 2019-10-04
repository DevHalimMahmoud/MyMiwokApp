package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class phrases extends AppCompatActivity {
    private MediaPlayer mAudioFile;
    private MediaPlayer.OnCompletionListener mComplet = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<word> words = new ArrayList<>();
        words.add(new word("Where are you going?", "minto wuksus", 0, R.raw.phrase_where_are_you_going));
        words.add(new word("What is your name?", "tinnә oyaase'nә", 0, R.raw.phrase_what_is_your_name));
        words.add(new word("My name is...", "oyaaset...", 0, R.raw.phrase_my_name_is));
        words.add(new word("How are you feeling?", "michәksәs?", 0, R.raw.phrase_how_are_you_feeling));
        words.add(new word("I’m feeling good.", "kuchi achit", 0, R.raw.phrase_im_feeling_good));
        words.add(new word("Are you coming?", "әәnәs'aa?", 0, R.raw.phrase_are_you_coming));
        words.add(new word("Yes, I’m coming.", "hәә’ әәnәm", 0, R.raw.phrase_yes_im_coming));
        words.add(new word("I’m coming.", "әәnәm", 0, R.raw.phrase_im_coming));
        words.add(new word("Let’s go.", "yoowutis", 0, R.raw.phrase_lets_go));
        words.add(new word("Come here.", "әnni'nem", 0, R.raw.phrase_come_here));


        wordAdapter adapter = new wordAdapter(this, words, R.color.category_phrases);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                word word = words.get(position);
                releaseMediaPlayer();
                mAudioFile = MediaPlayer.create(phrases.this, word.getmAudioFile());
                mAudioFile.start();
                mAudioFile.setOnCompletionListener(mComplet);
            }
        });
    }

    @Override
    protected void onStop() {
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
        }
    }
}
