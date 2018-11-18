package com.nextwin.pianikasabyan;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

/* Created by faozi on 02/01/18.*/

public class Utama extends AppCompatActivity {
    Button btnDo, btnRe, btnMi, btnFa, btnSol, btnLa, btnSi, btnDoo, btnDo2, btnRe2, btnMi2, btnFa2, btnSol2, btnLa2, btnSi2;
    Button notHitam1,notHitam2,notHitam3,notHitam4,notHitam5,notHitam6,notHitam7,notHitam78,notHitam8,notHitam9,notHitam10;
    RelativeLayout layBg, layUtama;
    private static int defaultStates[];
    private final static int[] STATE_PRESSED = {
            android.R.attr.state_pressed,
            android.R.attr.state_focused
                    | android.R.attr.state_enabled };
    private AdView mAdView, mAdView2;
    ImageView imgNote1, imgNote2, imgNote3, imgBack;
    Button btnClose;
    TextView teksNote;
    LinearLayout linearNote;
    MediaPlayer mpSound1 = new MediaPlayer();

    private int ss1;
    private int ss2;
    private int ss3;
    private int ss4;
    private int ss5;
    private int ss6;
    private int ss7;
    private int ss8;
    private int ss9;
    private int ss10;
    private int ss11;
    private int ss12;
    private int ss13;
    private int ss14;
    private int ss15;
    private int hitam1;
    private int hitam2;
    private int hitam3;
    private int hitam4;
    private int hitam5;
    private int hitam6;
    private int hitam7;
    private int hitam78;
    private int hitam8;
    private int hitam9;
    private int hitam10;

    private SoundPool sp;
    private InterstitialAd mInterstitialAd;
    boolean isFirst1 = false, isFirst2 = false, isFirst3 = false;

    AdRequest adRequest;
    AudioManager mAudioManager;
    String value = "ca-app-pub-5730449577374867/9811777852";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef2 = database.getReference("isBottomSabyan");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        deklarasi();
        action();
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
    }
    @Override
    public void onPause() {
        // This method should be called in the parent Activity's onPause() method.
        if (mAdView != null) {
            mAdView.pause();
        }
        if (mAdView2 != null) {
            mAdView2.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // This method should be called in the parent Activity's onResume() method.
        if (mAdView != null) {
            mAdView.resume();
        }
        if (mAdView2 != null) {
            mAdView2.resume();
        }
        Menu.isUtama = true;
        if (Menu.mpSound.isPlaying()) {
            Menu.mpSound.stop();
        }
    }

    @Override
    public void onDestroy() {
        // This method should be called in the parent Activity's onDestroy() method.
        if (mAdView != null) {
            mAdView.destroy();
        }
        if (mAdView2 != null) {
            mAdView2.destroy();
        }
        super.onDestroy();
    }

    private void deklarasi() {
        mpSound1 = MediaPlayer.create(this,R.raw.tok);
        imgBack = findViewById(R.id.imgBack);
        linearNote = findViewById(R.id.linearNote);
        btnClose = findViewById(R.id.btnClose);
        teksNote = findViewById(R.id.teksNote);
        teksNote.setVisibility(View.VISIBLE);
        linearNote.setVisibility(View.GONE);

        imgNote1 = findViewById(R.id.imgNote1);
        imgNote2 = findViewById(R.id.imgNote2);
        imgNote3 = findViewById(R.id.imgNote3);
        mAdView = findViewById(R.id.adView);
        mAdView2 = findViewById(R.id.adView2);
        layBg = findViewById(R.id.linearBacground);
        layUtama = findViewById(R.id.rlutama);
        btnDo = findViewById(R.id.btnDo);
        btnRe = findViewById(R.id.btnRe);
        btnMi = findViewById(R.id.btnMi);
        btnFa = findViewById(R.id.btnFa);
        btnSol = findViewById(R.id.btnSol);
        btnLa = findViewById(R.id.btnLa);
        btnSi = findViewById(R.id.btnSi);
        btnDoo = findViewById(R.id.btnDoo);

        btnDo2 = findViewById(R.id.btnDo2);
        btnRe2 = findViewById(R.id.btnRe2);
        btnMi2 = findViewById(R.id.btnMi2);
        btnFa2 = findViewById(R.id.btnFa2);
        btnSol2 = findViewById(R.id.btnSol2);
        btnLa2 = findViewById(R.id.btnLa2);
        btnSi2 = findViewById(R.id.btnSi2);

        notHitam1 = findViewById(R.id.notHitam1);
        notHitam2 = findViewById(R.id.notHitam2);
        notHitam3 = findViewById(R.id.notHitam3);
        notHitam4 = findViewById(R.id.notHitam4);
        notHitam5 = findViewById(R.id.notHitam5);
        notHitam6 = findViewById(R.id.notHitam6);
        notHitam7 = findViewById(R.id.notHitam7);
        notHitam78 = findViewById(R.id.notHitam78);
        notHitam8 = findViewById(R.id.notHitam8);
        notHitam9 = findViewById(R.id.notHitam9);
        notHitam10 = findViewById(R.id.notHitam10);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sp = new SoundPool.Builder().setMaxStreams(5).build();
        } else {
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        ss1 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_01_c3, 1);
        ss2 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_02_d3, 1);
        ss3 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_03_e3, 1);
        ss4 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_04_f3, 1);
        ss5 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_05_g3, 1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ss6 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_06_a3, 1);
                ss7 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_07_b3, 1);
                ss8 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_08_c4, 1);
                ss9 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_09_d4, 1);
                ss10 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_10_e4, 1);
            }
        }, 200);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ss11 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_11_f4, 1);
                ss12 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_12_g4, 1);
                ss13 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_13_a4, 1);
                ss14 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_14_b4, 1);
                ss15 = sp.load(Utama.this, R.raw.hohner_soprano_melodica_15_c5, 1);
                hitam1 = sp.load(Utama.this, R.raw.do_, 1);
                hitam2 = sp.load(Utama.this, R.raw.dohitam, 1);
                hitam3 = sp.load(Utama.this, R.raw.re, 1);
            }
        }, 400);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hitam4 = sp.load(Utama.this, R.raw.rehitam, 1);
                hitam5 = sp.load(Utama.this, R.raw.mi, 1);
                hitam6 = sp.load(Utama.this, R.raw.mihitam, 1);
                hitam7 = sp.load(Utama.this, R.raw.fa, 1);
                hitam78 = sp.load(Utama.this, R.raw.fahitam, 1);
                hitam8 = sp.load(Utama.this, R.raw.solhitam, 1);
                hitam9 = sp.load(Utama.this, R.raw.lahitam, 1);
                hitam10 = sp.load(Utama.this, R.raw.sihitam, 1);
            }
        }, 600);
        defaultStates = btnRe.getBackground().getState();

        mAdView2.setVisibility(View.GONE);
        mAdView.setVisibility(View.VISIBLE);
        adsTop();

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String bottom = dataSnapshot.getValue(String.class);
                Log.d("Value ", "Value is bottom: " + bottom);
                if (bottom != null) {
                    if (bottom.equals("0")) {
                        mAdView2.setVisibility(View.GONE);
                        mAdView.setVisibility(View.VISIBLE);
                        adsTop();
                    } else {
                        mAdView.setVisibility(View.GONE);
                        mAdView2.setVisibility(View.VISIBLE);
                        adsBottom();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                mAdView2.setVisibility(View.GONE);
                mAdView.setVisibility(View.VISIBLE);
                adsTop();
                Log.w("Load Failed", "Failed to read value bottom ", error.toException());
            }
        });
    }

    void adsTop() {
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }
        });
    }

    void adsBottom() {
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView2.loadAd(adRequest);
        mAdView2.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void action() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        switch (Menu.tema) {
            case 0:
                layBg.setBackground(getResources().getDrawable(R.drawable.bgpianika3));
                break;
            case 1:
                layBg.setBackground(getResources().getDrawable(R.drawable.bgpianika3_kuning));
                break;
            case 2:
                layBg.setBackground(getResources().getDrawable(R.drawable.bgpianika3_merah));
                break;
            case 3:
                layBg.setBackground(getResources().getDrawable(R.drawable.bgpianika3_hitam));
                break;
            case 4:
                layBg.setBackground(getResources().getDrawable(R.drawable.bgpianika3_putih));
                break;
            case 5:
                layBg.setBackground(getResources().getDrawable(R.drawable.bgpianika3_ungu));
                break;
            case 6:
                layBg.setBackground(getResources().getDrawable(R.drawable.bgpianika3_hijau));
                break;
            default:
                layBg.setBackground(getResources().getDrawable(R.drawable.bgpianika3));
                break;
        }

        switch (Menu.bg) {
            case 0:
                layUtama.setBackground(getResources().getDrawable(R.drawable.bg1));
                break;
            case 1:
                layUtama.setBackground(getResources().getDrawable(R.drawable.bg2));
                break;
            case 2:
                layUtama.setBackground(getResources().getDrawable(R.drawable.bg3));
                break;
            case 3:
                layUtama.setBackground(getResources().getDrawable(R.drawable.bg4));
                break;
            case 4:
                layUtama.setBackground(getResources().getDrawable(R.drawable.bg5));
                break;
            default:
                layUtama.setBackground(getResources().getDrawable(R.drawable.bg1));
                break;
        }

        btnDo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(ss1, motionEvent, btnDo);
            }
        });

        btnRe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(ss2, motionEvent, btnRe);
            }
        });

        btnMi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(ss3, motionEvent, btnMi);
            }
        });

        btnFa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(ss4, motionEvent, btnFa);
            }
        });

        btnSol.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound(ss5, motionEvent, btnSol);
            }
        });

        btnLa.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(ss6,motionEvent, btnLa);
            }
        });

        btnSi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(ss7,motionEvent, btnSi);
            }
        });

        btnDoo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(ss8,motionEvent, btnDoo);
            }
        });

        // =======================================================================================
        btnDo2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(ss9,motionEvent, btnDo2);
            }
        });

        btnRe2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound2(ss10,motionEvent, btnRe2);
            }
        });

        btnMi2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound3(ss11,motionEvent, btnMi2);
            }
        });

        btnFa2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound3(ss12,motionEvent, btnFa2);
            }
        });

        btnSol2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound3(ss13,motionEvent, btnSol2);
            }
        });

        btnLa2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound3(ss14,motionEvent, btnLa2);
            }
        });

        btnSi2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSound3(ss15,motionEvent, btnSi2);
            }
        });

        notHitam1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundHitam(hitam1,motionEvent,notHitam1);
            }
        });

        notHitam2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundHitam(hitam2,motionEvent,notHitam2);
            }
        });

        notHitam3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundHitam(hitam3,motionEvent,notHitam3);
            }
        });
        notHitam4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundHitam(hitam4,motionEvent,notHitam4);
            }
        });
        notHitam5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundHitam(hitam5,motionEvent,notHitam5);
            }
        });
        notHitam6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundHitam(hitam6,motionEvent,notHitam6);
            }
        });
        notHitam7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundHitam(hitam7,motionEvent,notHitam7);
            }
        });

        notHitam78.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundHitam(hitam78,motionEvent,notHitam78);
            }
        });

        notHitam8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundHitam(hitam8,motionEvent,notHitam8);
            }
        });
        notHitam9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundHitam(hitam9,motionEvent,notHitam9);
            }
        });
        notHitam10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return playSoundHitam(hitam10,motionEvent,notHitam10);
            }
        });

        teksNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpSound1.start();
                teksNote.setVisibility(View.GONE);
                linearNote.setVisibility(View.VISIBLE);
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpSound1.start();
                teksNote.setVisibility(View.VISIBLE);
                linearNote.setVisibility(View.GONE);
            }
        });

    }

    private boolean playSound(int id, MotionEvent motionEvent, Button btnNot) {
        Log.d("Motionsss  ", String.valueOf(motionEvent));
        if (motionEvent == null || motionEvent.getAction() == 0) {
            btnNot.setBackground(getResources().getDrawable(R.drawable.note12));
            float origionalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            sp.play(id, origionalVolume, origionalVolume, 0, 0, 1.0f);
            imgNote1.setVisibility(View.VISIBLE);
            if (isFirst1) {
                isFirst1 = false;
                imgNote1.setImageDrawable(getResources().getDrawable(R.drawable.musical_note3));
            } else {
                isFirst1 = true;
                imgNote1.setImageDrawable(getResources().getDrawable(R.drawable.musical_note));
            }
            Animation animation = AnimationUtils.loadAnimation(Utama.this, R.anim.zoomin);
            imgNote1.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    imgNote1.setVisibility(View.GONE);
                    imgNote1.startAnimation(AnimationUtils.loadAnimation(Utama.this, R.anim.zoomin2));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            btnNot.setBackground(getResources().getDrawable(R.drawable.note1));
            sp.autoPause();
        }
        return true;
    }

    private boolean playSound2(int id, MotionEvent motionEvent, Button btnNot) {
        Log.d("Motionsss  ", String.valueOf(motionEvent));
        if (motionEvent == null || motionEvent.getAction() == 0) {
            btnNot.setBackground(getResources().getDrawable(R.drawable.note12));
            float origionalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            sp.play(id, origionalVolume, origionalVolume, 0, 0, 1.0f);
            imgNote2.setVisibility(View.VISIBLE);
            if (isFirst2) {
                isFirst2 = false;
                imgNote2.setImageDrawable(getResources().getDrawable(R.drawable.musical_note5));
            } else {
                isFirst2 = true;
                imgNote2.setImageDrawable(getResources().getDrawable(R.drawable.musical_note2));
            }
            Animation animation = AnimationUtils.loadAnimation(Utama.this, R.anim.zoomin);
            imgNote2.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    imgNote2.setVisibility(View.GONE);
                    imgNote2.startAnimation(AnimationUtils.loadAnimation(Utama.this, R.anim.zoomin2));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            btnNot.setBackground(getResources().getDrawable(R.drawable.note1));
            sp.autoPause();
        }
        return true;
    }

    private boolean playSound3(int id, MotionEvent motionEvent, Button btnNot) {
        Log.d("Motionsss  ", String.valueOf(motionEvent));
        if (motionEvent == null || motionEvent.getAction() == 0) {
            btnNot.setBackground(getResources().getDrawable(R.drawable.note12));
            float origionalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            sp.play(id, origionalVolume, origionalVolume, 0, 0, 1.0f);
            imgNote3.setVisibility(View.VISIBLE);
            if (isFirst3) {
                isFirst3 = false;
                imgNote3.setImageDrawable(getResources().getDrawable(R.drawable.musical_note4));
            } else {
                isFirst3 = true;
                imgNote3.setImageDrawable(getResources().getDrawable(R.drawable.musical_note1));
            }
            Animation animation = AnimationUtils.loadAnimation(Utama.this, R.anim.zoomin);
            imgNote3.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    imgNote3.setVisibility(View.GONE);
                    imgNote3.startAnimation(AnimationUtils.loadAnimation(Utama.this, R.anim.zoomin2));
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            btnNot.setBackground(getResources().getDrawable(R.drawable.note1));
            sp.autoPause();
        }
        return true;
    }

    private boolean playSoundHitam(int id, MotionEvent motionEvent,Button btnNot) {
        Log.d("Motionsss  ",String.valueOf(motionEvent));
        if (motionEvent == null || motionEvent.getAction() == 0) {
            btnNot.setBackground(getResources().getDrawable(R.drawable.not_hitam2));
            float origionalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            sp.play(id, origionalVolume, origionalVolume, 0, 0, 1.0f);
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            btnNot.setBackground(getResources().getDrawable(R.drawable.not_hitam));
            sp.autoPause();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        iklan();
        finish();
    }

    private void iklan() {
        mInterstitialAd = new InterstitialAd(Utama.this);
        mInterstitialAd.setAdUnitId(value);
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

}
