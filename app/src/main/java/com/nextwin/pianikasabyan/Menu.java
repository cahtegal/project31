package com.nextwin.pianikasabyan;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity {


    ImageView imgPlay,imgShare,imgTheme,imgRateUs, imgBg;
    public static int tema = 0;
    public static int bg = 0;
    InterstitialAd mInterstitialAd;
    final AnimatorSet animatorSet = new AnimatorSet();
    public static boolean iklanOpen = false, isUtama = false;
    public static MediaPlayer mpSound = new MediaPlayer();
    MediaPlayer mpSound1 = new MediaPlayer();
    TextView teksSound;
    String value = "ca-app-pub-5730449577374867/9811777852";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        deklarasi();
        action();
        startAnimationPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mpSound.isPlaying()){
            mpSound.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mpSound.isPlaying()) {
            mpSound.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        iklanOpen = false;
        isUtama = false;
        if (!mpSound.isPlaying()) {
            mpSound = MediaPlayer.create(Menu.this, R.raw.backround_sound);
            mpSound.start();
        }
    }

    private void deklarasi() {
        mpSound1 = MediaPlayer.create(Menu.this,R.raw.tok);
        mpSound.setLooping(true);
        teksSound = findViewById(R.id.teksSound);
        imgPlay = findViewById(R.id.imgPlay);
        imgShare = findViewById(R.id.imgShare);
        imgTheme = findViewById(R.id.imgTheme);
        imgRateUs = findViewById(R.id.btnRateUs);
        imgBg = findViewById(R.id.btnBg);
    }

    private void action() {
        teksSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mpSound.isPlaying()) {
                    mpSound.stop();
                    teksSound.setText("SOUND ON");
                } else {
                    mpSound = MediaPlayer.create(Menu.this, R.raw.backround_sound);
                    mpSound.start();
                    teksSound.setText("SOUND OFF");
                }
            }
        });
        imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpSound1.start();
                Intent sendIntent;
                sendIntent = new Intent(android.content.Intent.ACTION_SEND);
                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Play this Piano Mini Nissa Sabyan for Kids for fun\n\nhttps://play.google.com/store/apps/details?id="+BuildConfig.APPLICATION_ID);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share with"));
            }
        });

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpSound1.start();
                iklan();
            }
        });

        imgTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpSound1.start();
                startActivity(new Intent(Menu.this, WarnaPianika.class));
            }
        });

        imgBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpSound1.start();
                startActivity(new Intent(Menu.this, Background.class));
            }
        });

        imgRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpSound1.start();
                String appPackageName = BuildConfig.APPLICATION_ID; // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        mpSound1.start();
        dialogOut();
    }

    private void dialogOut() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Menu.this);
        alertDialogBuilder.setTitle("Quit");
        alertDialogBuilder
                .setMessage("Quit from the game now ?")
                .setCancelable(false)
                .setPositiveButton("Yeah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton("No, I like this game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void iklan() {
        LayoutInflater inflater = LayoutInflater.from(Menu.this);
        View dialog_layout = inflater.inflate(R.layout.loading, null);

        AlertDialog.Builder dialLoad = new AlertDialog.Builder(Menu.this);
        dialLoad.setView(dialog_layout);
        final AlertDialog theDialog = dialLoad.create();
        theDialog.show();

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(value);
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    if (!iklanOpen) {
                        iklanOpen = true;
                        theDialog.dismiss();
                    }
                }
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                if (!isUtama) {
                    isUtama = true;
                    startActivity(new Intent(Menu.this,Utama.class));
                }
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                if (!isUtama) {
                    theDialog.dismiss();
                    isUtama = true;
                    startActivity(new Intent(Menu.this,Utama.class));
                }
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!iklanOpen) {
                    theDialog.dismiss();
                    iklanOpen = true;
                    if (!isUtama) {
                        isUtama = true;
                        startActivity(new Intent(Menu.this,Utama.class));
                    }
                }
            }
        },3000);
    }

    private void startAnimationPlay() {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imgPlay, "scaleY", 1.5f);
        scaleY.setDuration(200);
        ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(imgPlay, "scaleY", 1f);
        scaleYBack.setDuration(500);
        scaleYBack.setInterpolator(new BounceInterpolator());
        animatorSet.setStartDelay(600);
        animatorSet.playSequentially(scaleY, scaleYBack);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.setStartDelay(500);
                animatorSet.start();
            }
        });
        imgPlay.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animatorSet.start();
    }
}
