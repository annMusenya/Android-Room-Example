package com.madonasyombua.roomexample.helpers;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.madonasyombua.roomexample.models.Item;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private ItemExecutor itemExecutor;

    private LiveData<List<Item>> listLiveData;
    public ItemViewModel(@NonNull Application application) {
        super(application);

        itemExecutor = new ItemExecutor(application);
        listLiveData = itemExecutor.getListLiveData();
    }

   public LiveData<List<Item>> getListLiveData(){
        return listLiveData;
    }

    public void insert(Item item){
        itemExecutor.insert(item);
    }
}
