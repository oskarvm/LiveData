package com.example.livedata;

import androidx.lifecycle.LiveData;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Poder {

    interface PoderListener {
        void cuandoDePoder(String poder);
    }

    Random random = new Random();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> poder;

    void iniciarPoder(final PoderListener poderListener) {
        if (poder == null || poder.isCancelled()) {
            poder = scheduler.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    int imagen = random.nextInt(5) + 1;
                    poderListener.cuandoDePoder("PODER" + imagen);
                }
            }, 0, 3, SECONDS);
        }
    }

    void pararPoder() {
        if (poder != null) {
            poder.cancel(true);
        }
    }

    LiveData<String> poderLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            iniciarPoder(new PoderListener() {
                @Override
                public void cuandoDePoder(String poder) {
                    postValue(poder);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();

            pararPoder();
        }
    };
}


