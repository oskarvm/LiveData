package com.example.livedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class PoderViewModel extends AndroidViewModel {
    Poder poder;

    LiveData<Integer> poderLiveData;
    //LiveData<String> segundosLiveData;

    public PoderViewModel(@NonNull Application application) {
        super(application);

        poder = new Poder();

        poderLiveData = Transformations.switchMap(poder.poderLiveData, new Function<String, LiveData<Integer>>() {

            String poderAnterior;

            @Override
            public LiveData<Integer> apply(String orden) {

                int imagen;
                switch (orden) {
                    case "PODER1":
                    default:
                        imagen = R.drawable.action1;
                        break;
                    case "PODER2":
                        imagen = R.drawable.action2;
                        break;
                    case "PODER3":
                        imagen = R.drawable.action3;
                        break;
                    case "PODER4":
                        imagen = R.drawable.action4;
                        break;
                }

                return new MutableLiveData<>(imagen);
            }
        });
    }



    LiveData<Integer> obtenerPoder(){
        return poderLiveData;
    }
}