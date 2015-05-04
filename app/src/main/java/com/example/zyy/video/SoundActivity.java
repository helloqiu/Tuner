package com.example.zyy.video;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.triggertrap.seekarc.*;
import com.example.zyy.video.SinWave;

import android.os.Handler;
import java.util.logging.LogRecord;


/**
 * Created by Administrator on 2015/2/8.
 */
public class SoundActivity extends Activity{

    private SeekArc mSeekArc;
    String Pitch , Lpitch;
    private TextView mSeekArcProgress;
    SinWave sinWave;
    static int level;
    int Rate;
    private static Handler handler = new Handler();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new CustomAnimationDemoFragment())
                    .commit();
        }

        mSeekArc = (SeekArc)findViewById(R.id.seekArc);
        mSeekArcProgress = (TextView) findViewById(R.id.seekArcProgress);



        mSeekArc.setArcWidth(100);
        Pitch = "C";
        Lpitch = "C";
        level = 4;
        mSeekArcProgress.setText(Pitch);
        Rate = (int) trans(Pitch , level);
        sinWave = new SinWave();

        mSeekArc.setOnSeekArcChangeListener(changelistener);

    }

    SeekArc.OnSeekArcChangeListener changelistener = new SeekArc.OnSeekArcChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekArc seekArc) {
        }
        @Override
        public void onStartTrackingTouch(SeekArc seekArc) {
        }

        @Override
        public void onProgressChanged(SeekArc seekArc, int progress,
        boolean fromUser) {
            switch ((int)((progress / 100f )*7f)){
                case 0:
                    Pitch = "C";
                    break;
                case 1:
                    Pitch = "D";
                    break;
                case 2:
                    Pitch = "E";
                    break;
                case 3:
                    Pitch = "F";
                    break;
                case 4:
                    Pitch = "G";
                    break;
                case 5 :
                    Pitch = "A";
                    break;
                case 6:
                    Pitch = "B";
                    break;
                case 7:
                    Pitch = "B";
                    break;
            }
            if (!(Pitch.equals(Lpitch))) {
                //new thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mSeekArcProgress.setText(Pitch);
                                Rate = (int) trans(Pitch, level);
                                sinWave.stop();
                                sinWave.start(Rate);
                                sinWave.play();
                                Lpitch = Pitch;
                            }
                        });
                    }
                }).start();

            }
        }
    };

    public double trans(String pitch , int level){
        switch (level){
            case 4:
                if (pitch.equals("C")){
                    return 261.63;
                }
                if (pitch.equals("D")){
                    return 293.66;
                }
                if (pitch.equals(("E"))){
                    return 329.63;
                }
                if (pitch.equals("F")){
                    return 349.23;
                }
                if (pitch.equals(("G"))){
                    return 392.00;
                }
                if (pitch.equals("A")){
                    return 440.00;
                }
                if (pitch.equals("B")){
                    return 493.88;
                }
            case 1:
                if (pitch.equals("C")){
                    return 32.703;
                }
                if (pitch.equals("D")){
                    return 36.708;
                }
                if (pitch.equals("E")){
                    return 41.203;
                }
                if (pitch.equals("F")){
                    return 43.654;
                }
                if (pitch.equals(("G"))){
                    return 48.999;
                }
                if (pitch.equals("A")){
                    return 55.000;
                }
                if (pitch.equals("B")){
                    return 61.735;
                }
            case 2:
                if (pitch.equals("C")){
                    return 65.406;
                }
                if (pitch.equals("D")){
                    return 73.416;
                }
                if (pitch.equals("E")){
                    return 82.407;
                }
                if (pitch.equals("F")){
                    return 87.307;
                }
                if (pitch.equals("G")){
                    return 97.999;
                }
                if (pitch.equals("A")){
                    return 110.00;
                }
                if (pitch.equals("B")){
                    return 123.47;
                }
            case 3:
                if (pitch.equals("C")){
                    return 130.81;
                }
                if (pitch.equals("D")){
                    return 146.83;
                }
                if (pitch.equals("E")){
                    return 164.81;
                }
                if (pitch.equals("F")){
                    return 174.61;
                }
                if (pitch.equals("G")){
                    return 196.00;
                }
                if (pitch.equals("A")){
                    return 220.00;
                }
                if (pitch.equals("B")){
                    return 246.94;
                }
        }
        return 261.63;
    }
    public static class CustomAnimationDemoFragment extends Fragment {

        public CustomAnimationDemoFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            //FloatingActionButton
            ImageView Button0;
            Button0 = new ImageView(getActivity());
            Button0.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new));
            FloatingActionButton FButton =  new FloatingActionButton.Builder(getActivity())
                    .setTheme(FloatingActionButton.THEME_LIGHT)
                    .setContentView(Button0)
                    .setPosition(FloatingActionButton.POSITION_BOTTOM_CENTER)
                    .build();
            //subaction icon
            ImageView icon1 = new ImageView(getActivity());
            ImageView icon2 = new ImageView(getActivity());
            ImageView icon3 = new ImageView(getActivity());
            ImageView icon4 = new ImageView(getActivity());

            icon1.setImageDrawable(getResources().getDrawable(R.drawable.ic_subaction_one));
            icon2.setImageDrawable(getResources().getDrawable(R.drawable.ic_subaction_two));
            icon3.setImageDrawable(getResources().getDrawable(R.drawable.ic_subaction_three));
            icon4.setImageDrawable(getResources().getDrawable(R.drawable.ic_subaction_four));

            SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(getActivity())
                    .setTheme(SubActionButton.THEME_LIGHT);
            //subactionbutton
            SubActionButton button1 = rLSubBuilder.setContentView(icon1).build();
            SubActionButton button2 = rLSubBuilder.setContentView(icon2).build();
            SubActionButton button3 = rLSubBuilder.setContentView(icon3).build();
            SubActionButton button4 = rLSubBuilder.setContentView(icon4).build();

            button1.setOnClickListener(listener1);
            button2.setOnClickListener(listener2);
            button3.setOnClickListener(listener3);
            button4.setOnClickListener(listener4);

            FloatingActionMenu centerBottomMenu = new FloatingActionMenu.Builder(getActivity())
                    .setStartAngle(0)
                    .setEndAngle(-180)
                    .setAnimationHandler(new SlideInAnimationHandler())
                    .addSubActionView(button1)
                    .addSubActionView(button2)
                    .addSubActionView(button3)
                    .addSubActionView(button4)
                    .attachTo(FButton)
                    .build();

            return rootView;
        }
    }
    public static View.OnClickListener listener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            level = 1;
        }
    };
    public static View.OnClickListener listener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            level = 2;
        }
    };
    public static View.OnClickListener listener3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            level = 3;
        }
    };
    public static View.OnClickListener listener4 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            level = 4;

        }
    };
}
