package com.example.zyy.video;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/2/8.
 */
public class SinWave {
    public static final int RATE = 44100;
    /** 正弦波的高度 **/
    public static final int HEIGHT = 127;
    /** 2PI **/
    public static final double TWOPI = 2 * 3.1415;
    byte[] wave;
    AudioTrack audioTrack;
    int length;
    int wavelen;
    /**
     * 生成正弦波
     * @param wave
     * @param waveLen 每段正弦波的长度
     * @param length 总长度
     * @return
     */
    public static byte[] sin(byte[] wave, int waveLen, int length) {
        for (int i = 0; i < length; i++) {

            wave[i] = (byte) (HEIGHT * (1 - Math.sin(TWOPI
                    * ((i % waveLen) * 1.00 / waveLen))));
        }
        return wave;
    }
    /**
     * 设置频率
     * @param rate
     */
    public void start(int rate){
        if(rate>0){
            int Hz=rate;
            int waveLen = 44100/ Hz;
            wavelen = waveLen;
            length = waveLen * Hz;

            audioTrack=new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
                    AudioFormat.CHANNEL_CONFIGURATION_STEREO, // CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_8BIT,length, AudioTrack.MODE_STATIC);
            //生成正弦波
            wave = new byte[RATE];
            wave=SinWave.sin(wave, waveLen, length);
        }else{
            return;
        }
    }

    public int play(){
            int hi = 111;
            if (audioTrack != null) {
                audioTrack.write(wave,1000, length-2000);
                hi = audioTrack.setLoopPoints(0 , wavelen  , -1) ;
                audioTrack.play();


            }
        return hi;
    }

    public void stop(){
        if(audioTrack!=null){
            audioTrack.stop();
            audioTrack.release();
            audioTrack=null;
        }
    }
}
