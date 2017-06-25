package com.harshita.bollywood;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Harshita on 20-03-2017.
 */
public class GuessMovie extends Activity {

 //   private TextView chancesTextView;
    private LinearLayout guessMovieNameLL;
    private TextView scoreText;
    private TextView highScoreText;

    private static final String PREFS_NAME = "BollywoodGamePreferences";
    private static final String HIGH_SCORE = "HighScore";

    private Button b_bollywood;
    private Button o1_bollywood;
    private Button l1_bollywood;
    private Button l2_bollywood;
    private Button w_bollywood;
    private Button o2_bollywood;
    private Button o3_bollywood;
    private Button y_bollywood;
    private Button d_bollywood;
    private Button charButton2;
    private Button charButton3;
    private Button charButton4;
    private Button charButton6;
    private Button charButton7;
    private Button charButton8;
    private Button charButton10;
    private Button charButton11;
    private Button charButton12;
    private Button charButton13;
    private Button charButton14;
    private Button charButton16;
    private Button charButton17;
    private Button charButton18;
    private Button charButton19;
    private Button charButton20;
    private Button charButton22;
    private Button charButton23;
    private Button charButton24;
    private Button charButton25;
    private Button charButton26;
    private Button tryAgainButton;

    private String movieName;
    private String displayName;

    private int noOfChances = 10;
    private int noOfChar;
    private int score=0;
    private int highscore;
    private static int maxWordLen = 8;

    private ArrayList movieList1 = new ArrayList();

    final MediaPlayer mp = new MediaPlayer();
    AssetFileDescriptor afd;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        initView();
    }

    private void initView() {
        setContentView(R.layout.guess_movie);
        b_bollywood = (Button) findViewById(R.id.B_bollywood);
        o1_bollywood = (Button) findViewById(R.id.O1_bollywood);
        l1_bollywood = (Button) findViewById(R.id.L1_bollywood);
        l2_bollywood = (Button) findViewById(R.id.L2_bollywood);
        y_bollywood = (Button) findViewById(R.id.Y_bollywood);
        w_bollywood = (Button) findViewById(R.id.W_bollywood);
        o2_bollywood = (Button) findViewById(R.id.O2_bollywood);
        o3_bollywood = (Button) findViewById(R.id.O3_bollywood);
        d_bollywood = (Button) findViewById(R.id.D_bollywood);
        charButton2 = (Button) findViewById(R.id.b);
        charButton3 = (Button) findViewById(R.id.c);
        charButton4 = (Button) findViewById(R.id.d);
        charButton6 = (Button) findViewById(R.id.f);
        charButton7 = (Button) findViewById(R.id.g);
        charButton8 = (Button) findViewById(R.id.h);
        charButton10 = (Button) findViewById(R.id.j);
        charButton11 = (Button) findViewById(R.id.k);
        charButton12 = (Button) findViewById(R.id.l);
        charButton13 = (Button) findViewById(R.id.m);
        charButton14 = (Button) findViewById(R.id.n);
        charButton16 = (Button) findViewById(R.id.p);
        charButton17 = (Button) findViewById(R.id.q);
        charButton18 = (Button) findViewById(R.id.r);
        charButton19 = (Button) findViewById(R.id.s);
        charButton20 = (Button) findViewById(R.id.t);
        charButton22 = (Button) findViewById(R.id.v);
        charButton23 = (Button) findViewById(R.id.w);
        charButton24 = (Button) findViewById(R.id.x);
        charButton25 = (Button) findViewById(R.id.y);
        charButton26 = (Button) findViewById(R.id.z);
        guessMovieNameLL = (LinearLayout) findViewById(R.id.guess_movie_linear_layout);
        setMovieName();
    }

    private String getMovieNameFromFile() {
        // TODO Auto-generated method stub
        AssetManager am = getApplicationContext().getAssets();
        try {
            InputStream is = am.open("movie.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String s = br.readLine();
            while (s != null)
            {
                if (isAllWordsAreLessThanMaxLength(s)) {
                    movieList1.add(s);
                }
                s = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Random r = new Random();
        int n=r.nextInt(movieList1.size());
        return movieList1.get(n).toString();
    }

    boolean isAllWordsAreLessThanMaxLength(String movie) {
        int len=0;
        Log.d("Harshita","max len movie name : "+movie);
        for(int i=0;i<movie.length();i++) {
            len++;
            Log.d("Harshita","len is : "+len);
            if (len > maxWordLen) {
                return false;
            }
            if(movie.charAt(i) == ' ' && (len-1)<=maxWordLen) {
                len=0;
            }
        }
        if(len<=maxWordLen)
            return true;
        return false;
    }

    private void setMovieName() {
        String name = getMovieNameFromFile();
        movieName = name.toUpperCase();
        Log.d("Harshita","movieName is : "+movieName);
        displayName = movieName;
        noOfChar = movieName.length();
        for(int i=0;i<noOfChar;i++){
            if((!isVowel(displayName.charAt(i))) && !(displayName.charAt(i)==' ')) {
                displayName = displayName.replace(displayName.charAt(i),'_');
            }
        }
        setViewForDisplayName(displayName);
        Animation zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        guessMovieNameLL.setAnimation(zoomin);
    }

    private boolean isVowel(char ch) {
        if (ch == 'A' || ch == 'a' || ch == 'E' || ch == 'e' || ch == 'I' || ch == 'i' || ch == 'O' || ch == 'o' || ch == 'U' || ch == 'u') {
            return true;
        } else {
            return false;
        }
    }

    private String getNewMovieName() {
        String[] movieList = {"RAM LAKHAN","HUM TUMHARE HAIN SANAM", "AMAR AKBAR ANTHONY","BEFIKRE", "MERA NAAM JOKER", "MAINE PYAR KIYA", "KUCH KUCH HOTA HAI", "BORDER", "JAB TAK HAI JAAN", "DANGAL", "HUM TUM",
        "AAJ KA ARJUN", "KARAN ARJUN", "KAL HO NA HO", "KABHI KHUSHI KABHI GHAM", "DIL TOH PAGAL HAI", "MURDER", "TAARE ZAMEEN PAR"};
        Random r = new Random();
        int n=r.nextInt(movieList.length);
        return movieList[n];
    }

    // To be called only be A-Z alphabets
    public void onClick(View view) {
        char ch = (((Button)view).getText()).toString().charAt(0);
        view.setEnabled(false);
        checkForCharInMovieName(ch);
        if (displayName.equals(movieName)) {
            playSound("magic-chime.mp3");
            score++;
            resetMovieNameAndEnableButtons();
        }
    }

    private void checkForCharInMovieName(char ch) {
        // if match display the modified new displayName
        // else reduce the chance count
        boolean isMatchFound = false;
        for (int i=0;i<noOfChar;i++) {
            char movieChar = movieName.charAt(i);
            if (ch == movieName.charAt(i)) {
                displayName = displayName.substring(0,i)+ch+displayName.substring(i+1);
                isMatchFound = true;
                playSound("correctCharacter.mp3");
            }
        }
        setViewForDisplayName(displayName);
        if(!isMatchFound) {
            noOfChances--;
            if (noOfChances > 0) {
                playSound("wrongCharacter.mp3");
             //   chancesTextView.setText("Chances left : " + noOfChances);
                reduceNoOfChances();
                if (noOfChances == 1) {
                    gameOver();
                }
            }
        }
    }

    private void setViewForDisplayName(String displayName) {
        guessMovieNameLL.removeAllViews();
        for(int i=0;i<noOfChar;) {
            if (displayName.charAt(i)==' ') i++;
            if(i==0 || displayName.charAt(i-1)==' ') {
                // Dynamic linear layout creation
                LinearLayout newLL = new LinearLayout(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.gravity= Gravity.CENTER;
                lp.topMargin = 10;
                newLL.setOrientation(LinearLayout.HORIZONTAL);
                newLL.setLayoutParams(lp);
                guessMovieNameLL.addView(newLL);
                while(i<noOfChar && displayName.charAt(i) != ' ') {
                // Dynamic button creation
                    Button newButton = new Button(this);
                    LinearLayout.LayoutParams blp = new LinearLayout.LayoutParams(130, 130);
                    blp.rightMargin = 5;
                    newButton.setLayoutParams(blp);
                    char c = displayName.charAt(i);
                    newButton.setText(String.valueOf(c));
                    newButton.setClickable(false);
                    newButton.setBackgroundResource(R.drawable.moviebutton);
                    newLL.addView(newButton);
                    i++;
                }
            }
        }
    }

    private void reduceNoOfChances() {
        switch (noOfChances) {
            case 1 :TextView tv = (TextView) d_bollywood;
                    tv.setPaintFlags(tv.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            case 2 :TextView tv1 = (TextView) o3_bollywood;
                tv1.setPaintFlags(tv1.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            case 3 :TextView tv2 = (TextView) o2_bollywood;
                tv2.setPaintFlags(tv2.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            case 4 :TextView tv3 = (TextView) w_bollywood;
                tv3.setPaintFlags(tv3.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            case 5 :TextView tv4 = (TextView) y_bollywood;
                tv4.setPaintFlags(tv4.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            case 6 :TextView tv5 = (TextView) l2_bollywood;
                tv5.setPaintFlags(tv5.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            case 7 :TextView tv6 = (TextView) l1_bollywood;
                tv6.setPaintFlags(tv6.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            case 8 :TextView tv7 = (TextView) o1_bollywood;
                tv7.setPaintFlags(tv7.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            case 9 :TextView tv8 = (TextView) b_bollywood;
                tv8.setPaintFlags(tv8.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                break;
            default:break;
        }
    }

    private void resetMovieNameAndEnableButtons() {
        // show next movie name to guess
        // and enable all disabled buttons

            setMovieName();
            charButton2.setEnabled(true);
            charButton3.setEnabled(true);
            charButton4.setEnabled(true);
            charButton6.setEnabled(true);
            charButton7.setEnabled(true);
            charButton8.setEnabled(true);
            charButton10.setEnabled(true);
            charButton11.setEnabled(true);
            charButton12.setEnabled(true);
            charButton13.setEnabled(true);
            charButton14.setEnabled(true);
            charButton16.setEnabled(true);
            charButton17.setEnabled(true);
            charButton18.setEnabled(true);
            charButton19.setEnabled(true);
            charButton20.setEnabled(true);
            charButton22.setEnabled(true);
            charButton23.setEnabled(true);
            charButton24.setEnabled(true);
            charButton25.setEnabled(true);
            charButton26.setEnabled(true);
    }

    private void gameOver() {
        setContentView(R.layout.try_again);
        scoreText = (TextView) findViewById(R.id.score);
        highScoreText  = (TextView) findViewById(R.id.bestscore);
        tryAgainButton = (Button) findViewById(R.id.try_again_button);
        scoreText.setText(""+score);
        highscore = prefs.getInt(HIGH_SCORE,0);
        Log.d("Harshita", "1 highscore is : " + highscore);
        if (score>highscore) {
            Log.d("Harshita", "score greater is : " + score);
            editor = prefs.edit();
            editor.putInt(HIGH_SCORE, score);
            editor.commit();
        }
            highScoreText.setText(""+prefs.getInt(HIGH_SCORE,0));
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noOfChances=10;
                score=0;
                initView();
            }
        });
    }

    private void playSound(String sound) {
        try {
            mp.reset();
            afd = getAssets().openFd(sound);
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit  BOLLYWOOD")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }).create().show();
    }
}
