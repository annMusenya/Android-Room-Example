package com.madonasyombua.roomexample;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;



import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LiveDataTest {
    //getting the value of the live data object trying to get a notification within seconds

    public static <T> T getValue(final LiveData<T> tLiveData) throws InterruptedException{

        final Object[] data = new Object[1];
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        android.arch.lifecycle.Observer<T> observer = new android.arch.lifecycle.Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                countDownLatch.countDown();
                tLiveData.removeObserver(this);
            }
        };
        tLiveData.observeForever(observer);
        countDownLatch.await(2, TimeUnit.SECONDS);



        return (T) data[0];
    }
}
