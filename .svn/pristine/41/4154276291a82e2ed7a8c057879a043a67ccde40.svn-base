package com.strategy.intecom.vtc.vigo.view.fragment;

import android.annotation.TargetApi;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.adt.AdtArchitectureAudio;
import com.strategy.intecom.vtc.vigo.model.VtcModelArchitetureAudio;
import com.strategy.intecom.vtc.vigo.model.VtcModelPosition;
import com.strategy.intecom.vtc.vigo.model.VtcModelPositionAudio;
import com.strategy.intecom.vtc.vigo.utils.Const;
import com.strategy.intecom.vtc.vigo.utils.Utils;
import com.strategy.intecom.vtc.vigo.view.base.AppCore;
import com.strategy.intecom.vtc.vigo.view.custom.listcontent.ListViewWrapContent;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by LuanPV on 5/31/2017.
 */

public class FMArchitectureDetails extends AppCore implements View.OnClickListener {
    private TextView tvAudioDescription;
    private List<VtcModelArchitetureAudio> audioList;
    private LinearLayout loutAudioDescription, loutAudioOption, loutDesFull, loutDes;
    private LinearLayout loutViewArticle;
    private RelativeLayout loutDownload;
    private ImageView imgUp, img_thumb;
    private ImageView imgPlay;
    private TextView tvDescription, tvDescriptionFull, tvReadAll, tvtitle, tvState, tvTimeRemain;
    private TextView tvAudioSpeed;

    private AdtArchitectureAudio adtArchitectureAudio;
    private ListViewWrapContent lstAudioGuild;

    private VtcModelPositionAudio vtcModelPositioAudio;
    private MediaPlayer mp;
    private SeekBar seekBarAudio;

    private Handler mHandler = new Handler();

    private int currentArchitureId = 0;

    private AudioManager volumeAmanager;
    private int width = 0;
    private int height = 0;

    private float currentSpeed = 1.0f;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        savedInstanceState = getArguments();
        if (savedInstanceState != null) {
            vtcModelPositioAudio = savedInstanceState.getParcelable(Const.BUNDLE_TYPE_DATA_TYPE);
        }
        return inflater.inflate(R.layout.ui_architecture_details, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initController(view);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void initController(View view) {
        initDumpData();

        int[] screen = Utils.getSizeScreen(getmActivity());


        int offset = screen[0];

        width = offset;

        offset = width / 16;

        height = offset * 9;

        volumeAmanager = (AudioManager) getContext().getSystemService(getContext().AUDIO_SERVICE);

        tvAudioDescription = (TextView) view.findViewById(R.id.tv_audio_description);
        tvAudioDescription.setMovementMethod(new ScrollingMovementMethod());

        LinearLayout loutShrink = (LinearLayout) view.findViewById(R.id.lout_shrink);
        loutShrink.setOnClickListener(this);

        LinearLayout loutSpeed = (LinearLayout) view.findViewById(R.id.lout_speed);
        loutSpeed.setOnClickListener(this);

        loutAudioDescription = (LinearLayout) view.findViewById(R.id.lout_audio_description);
        loutAudioOption = (LinearLayout) view.findViewById(R.id.lout_audio_option);

        imgUp = (ImageView) view.findViewById(R.id.img_up);
        imgUp.setOnClickListener(this);

        lstAudioGuild = (ListViewWrapContent) view.findViewById(R.id.lst_audio_guild);
        adtArchitectureAudio = new AdtArchitectureAudio(getmActivity(), screen);

        loutDesFull = (LinearLayout) view.findViewById(R.id.lout_description_full);
        loutDes = (LinearLayout) view.findViewById(R.id.lout_description);
        loutDownload = (RelativeLayout) view.findViewById(R.id.lout_download);

        loutViewArticle = (LinearLayout) view.findViewById(R.id.lout_view_article);
        loutViewArticle.setOnClickListener(this);


        ImageView btnBack = (ImageView) view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);

        ImageView btnVolume = (ImageView) view.findViewById(R.id.img_volume);
        btnVolume.setOnClickListener(this);

        ImageView btnNext = (ImageView) view.findViewById(R.id.img_next);
        btnNext.setOnClickListener(this);

        ImageView btnPrevious = (ImageView) view.findViewById(R.id.img_previous);
        btnPrevious.setOnClickListener(this);

        ImageView btnReplay = (ImageView) view.findViewById(R.id.img_replay);
        btnReplay.setOnClickListener(this);

        ImageView btnReplay2 = (ImageView) view.findViewById(R.id.img_replay2);
        btnReplay2.setOnClickListener(this);


        imgPlay = (ImageView) view.findViewById(R.id.img_play);
        imgPlay.setOnClickListener(this);

        tvReadAll = (TextView) view.findViewById(R.id.txt_read_all);
        tvReadAll.setOnClickListener(this);

        tvTimeRemain = (TextView) view.findViewById(R.id.tv_audio_time);

        tvDescription = (TextView) view.findViewById(R.id.txt_description);
        tvDescriptionFull = (TextView) view.findViewById(R.id.txt_description_full);
        tvtitle = (TextView) view.findViewById(R.id.txt_title);
        tvState = (TextView) view.findViewById(R.id.tv_audio_state);
        tvAudioSpeed = (TextView) view.findViewById(R.id.tv_audio_speed);

        seekBarAudio = (SeekBar) view.findViewById(R.id.seekbar_audio);
        seekBarAudio.getProgressDrawable().setColorFilter(getResources().getColor(R.color.color_background), PorterDuff.Mode.SRC_IN);
        seekBarAudio.getThumb().setColorFilter(getResources().getColor(R.color.color_background), PorterDuff.Mode.SRC_IN);
        seekBarAudio.setOnClickListener(this);

        seekBarAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == seekBar.getMax()) {
                    imgPlay.setBackgroundResource(R.mipmap.ic_play);
                }
                if (mp != null && fromUser) {
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        checkFooter();

        initData();
    }

    private void initData() {


        long countDuration = 0;
        tvtitle.setText(vtcModelPositioAudio.getTitle());
        tvtitle.setSelected(true);

        for (int i = 0; i < audioList.size(); i++) {
            VtcModelArchitetureAudio positionAudio = audioList.get(i);
            countDuration += positionAudio.getDuration();
        }

        adtArchitectureAudio.setData(audioList);
        lstAudioGuild.setAdapter(adtArchitectureAudio);

        initEvent();
        playAudio(audioList.get(0));
    }

    private void initEvent() {
        adtArchitectureAudio.setOnClickItem(new AdtArchitectureAudio.onClickItem() {
            @Override
            public void onClickItem(VtcModelArchitetureAudio service) {
                playAudio(service);
                currentArchitureId = service.getId();
            }
        });
    }

    private void checkFooter() {
        if (loutAudioOption.getVisibility() == View.GONE) {
            loutAudioOption.setVisibility(View.VISIBLE);
            loutAudioDescription.setVisibility(View.VISIBLE);
            imgUp.setVisibility(View.GONE);
        } else {
            loutAudioOption.setVisibility(View.GONE);
            loutAudioDescription.setVisibility(View.GONE);
            imgUp.setVisibility(View.VISIBLE);
        }
    }

    private void showFullDes(boolean value) {
        tvDescriptionFull.setText(audioList.get(currentArchitureId).getContent());
        if (value == true) {
            loutDesFull.setVisibility(View.VISIBLE);
            loutDes.setVisibility(View.GONE);
            lstAudioGuild.setVisibility(View.GONE);
            loutDownload.setVisibility(View.GONE);

        } else {
            loutDesFull.setVisibility(View.GONE);
            loutDes.setVisibility(View.VISIBLE);
            lstAudioGuild.setVisibility(View.VISIBLE);
            loutDownload.setVisibility(View.VISIBLE);
        }
    }

    private void seekBarConnection(final int duration) {
        seekBarAudio.setProgress(0);
        seekBarAudio.setMax(duration);

        mHandler.removeCallbacksAndMessages(null);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mp != null) {
                    int mCurrentPosition = mp.getCurrentPosition();

                    long timeRemain = duration - mCurrentPosition;
                    long minutes = (timeRemain / 1000) / 60;
                    int seconds = (int) (timeRemain / 1000) % 60;

                    tvTimeRemain.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));

                    seekBarAudio.setProgress(mCurrentPosition);

                    if ((duration / 1000) == (mCurrentPosition / 1000)) {
                        imgPlay.setBackgroundResource(R.mipmap.ic_play);
                    }
                }
                mHandler.postDelayed(this, 1000);
            }
        });
    }


    private void initDumpData() {
        audioList = new ArrayList<>();

        VtcModelArchitetureAudio architectureAudio = new VtcModelArchitetureAudio();
        architectureAudio.setId(0);
        architectureAudio.setTitle("Four Pillars");
        architectureAudio.setDuration(180);
        architectureAudio.setFileName("audio_1");
        architectureAudio.setPath("raw");
        architectureAudio.setContent("1. Hanoi has been the capital city of Vietnam for over 1,000 years. It is undoubtedly one of the must-visit locations in Vietnam. Hanoi is the best starting point of your journey in Vietnam for its extremely...");
        audioList.add(architectureAudio);

        architectureAudio = new VtcModelArchitetureAudio();
        architectureAudio.setId(1);
        architectureAudio.setTitle("Great portico");
        architectureAudio.setDuration(180);
        architectureAudio.setFileName("audio_2");
        architectureAudio.setPath("raw");
        architectureAudio.setContent("2. Hanoi has been the capital city of Vietnam for over 1,000 years. It is undoubtedly one of the must-visit locations in Vietnam. Hanoi is the best starting point of your journey in Vietnam for its extremely...");
        audioList.add(architectureAudio);

        architectureAudio = new VtcModelArchitetureAudio();
        architectureAudio.setId(2);
        architectureAudio.setTitle("Khuê Văn pavillion");
        architectureAudio.setDuration(180);
        architectureAudio.setFileName("audio_3");
        architectureAudio.setPath("raw");
        architectureAudio.setContent("3. Hanoi has been the capital city of Vietnam for over 1,000 years. It is undoubtedly one of the must-visit locations in Vietnam. Hanoi is the best starting point of your journey in Vietnam for its extremely...");
        audioList.add(architectureAudio);

        architectureAudio = new VtcModelArchitetureAudio();
        architectureAudio.setId(3);
        architectureAudio.setTitle("82 doctor stelae & Well of Heavenly Clarity");
        architectureAudio.setDuration(180);
        architectureAudio.setFileName("audio_4");
        architectureAudio.setPath("raw");
        architectureAudio.setContent("4. Hanoi has been the capital city of Vietnam for over 1,000 years. It is undoubtedly one of the must-visit locations in Vietnam. Hanoi is the best starting point of your journey in Vietnam for its extremely...");
        audioList.add(architectureAudio);

        architectureAudio = new VtcModelArchitetureAudio();
        architectureAudio.setId(4);
        architectureAudio.setTitle("Countyard of the Sage sanctuary");
        architectureAudio.setDuration(180);
        architectureAudio.setFileName("audio_5");
        architectureAudio.setPath("raw");
        architectureAudio.setContent("5. Hanoi has been the capital city of Vietnam for over 1,000 years. It is undoubtedly one of the must-visit locations in Vietnam. Hanoi is the best starting point of your journey in Vietnam for its extremely...");
        audioList.add(architectureAudio);
    }

    private void playAudio(VtcModelArchitetureAudio architectureAudio) {
        adtArchitectureAudio.updaStatus(architectureAudio.getId());

        int state = architectureAudio.getId() + 1;
        tvState.setText(getResources().getString(R.string.title_architecture_Audio) + " " + String.valueOf(state) + " " + getResources().getString(R.string.title_architecture_out_of) + " " + audioList.size());

        tvAudioDescription.setText(architectureAudio.getContent());

        imgPlay.setBackgroundResource(R.mipmap.ic_pause);
        try {
            removeAudio();
            int resID = getResources().getIdentifier(architectureAudio.getFileName(), architectureAudio.getPath(), getContext().getPackageName());
            mp = MediaPlayer.create(getContext(), resID);
            mp.setPlaybackParams(mp.getPlaybackParams().setSpeed(currentSpeed));
            mp.start();

            seekBarConnection(mp.getDuration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeAudio() {
        if (mp != null) {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lout_shrink:
                checkFooter();
                break;
            case R.id.img_up:
                checkFooter();
                break;
            case R.id.lout_view_article:
                showFullDes(true);
                break;
            case R.id.btn_back:
                if (loutDesFull.getVisibility() == View.VISIBLE) {
                    showFullDes(false);
                } else {
                    mHandler.removeCallbacksAndMessages(null);
                    cmdBack();
                    removeAudio();
                }
                break;
            case R.id.txt_read_all:
                if (tvDescription.getMaxLines() == Integer.MAX_VALUE) {
                    tvDescription.setMaxLines(2);
                    tvReadAll.setText(getResources().getString(R.string.title_architecture_read_all));
                } else {
                    tvDescription.setMaxLines(Integer.MAX_VALUE);
                    tvReadAll.setText(getResources().getString(R.string.title_architecture_minimize));
                }
                break;
            case R.id.img_play:
                if (mp == null) {
                    playAudio(audioList.get(0));
                } else {
                    if (mp.isPlaying()) {
                        mp.pause();
                        imgPlay.setBackgroundResource(R.mipmap.ic_play);
                    } else {
                        mp.seekTo(mp.getCurrentPosition());
                        imgPlay.setBackgroundResource(R.mipmap.ic_pause);
                        mp.start();
                    }
                }
                break;
            case R.id.img_volume:
                if (volumeAmanager.isStreamMute(AudioManager.STREAM_MUSIC)) {
                    volumeAmanager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
                } else {
                    volumeAmanager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
                }

                break;
            case R.id.img_next:
                if (audioList.get(currentArchitureId).getId() != audioList.get(audioList.size() - 1).getId()) {
                    playAudio(audioList.get(currentArchitureId + 1));
                    currentArchitureId += 1;
                }
                break;
            case R.id.img_previous:
                if (audioList.get(currentArchitureId).getId() != audioList.get(0).getId()) {
                    playAudio(audioList.get(currentArchitureId - 1));
                    currentArchitureId -= 1;
                }
                break;
            case R.id.img_replay:
                playAudio(audioList.get(currentArchitureId));
                break;
            case R.id.img_replay2:
                playAudio(audioList.get(currentArchitureId));
                break;
            case R.id.lout_speed:
                if (currentSpeed == 1.0f) {
                    mp.setPlaybackParams(mp.getPlaybackParams().setSpeed(1.5f));
                    tvAudioSpeed.setText("1.5x");
                    currentSpeed = 1.5f;
                } else {
                    mp.setPlaybackParams(mp.getPlaybackParams().setSpeed(1.1f));
                    tvAudioSpeed.setText("1.0x");
                    currentSpeed = 1.0f;
                }
                break;

        }
    }
}
