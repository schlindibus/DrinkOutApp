package ch.zli.lk.drinkout.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;

import java.util.concurrent.ThreadLocalRandom;

public class BombService extends Service {
    public enum bombState {
        Expolded,
        Defused
    }

    private final IBinder ibinder = new LocalBinder();

    public BombService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return ibinder;
    }

    public class LocalBinder extends Binder {
        public BombService getService() {
            return BombService.this;
        }
    }

    public void vibrate(Context context, long durationMillis) {
        Vibrator vibratorManager = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibratorManager != null) {
            vibratorManager.vibrate(durationMillis);
        }
    }

    public String defuseBomb(int clicks) {
        int min = 20;
        int max = 30;
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        if (clicks > randomNum) {
            return bombState.Defused.name();
        } else {
            return bombState.Expolded.name();
        }
    }
}