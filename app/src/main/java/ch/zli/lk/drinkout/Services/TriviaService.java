package ch.zli.lk.drinkout.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;


public class TriviaService extends Service {
    String[] questions = new String [] {
            "Wie viele Füsse hat ein Elefant?",
            "Wo liegt der Eiffelturm?",
            "Ist die Erde rund?",
            "Wie viel Bier ist zu viel Bier?"
    };
    String[] answers = new String [] {
            "4",
            "vier",
            "paris",
            "ja",
            "gibt es nicht"
    };
    int finalPoints = 0;

    private final IBinder ibinder = new TriviaService.LocalBinder();

    public TriviaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return ibinder;
    }

    public class LocalBinder extends Binder {
        public TriviaService getService() {
            return TriviaService.this;
        }
    }

    public String setQuestion(int question){
        return questions[question].toString();
    }
}