package com.madonasyombua.roomexample.helpers;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.madonasyombua.roomexample.models.Item;
import com.madonasyombua.roomexample.room.ItemDataBase;

import java.util.List;

public class ItemExecutor {
    private ItemDao itemDao;
    private LiveData<List<Item>> listLiveData;


    ItemExecutor(Application application){
        ItemDataBase itemDataBase = ItemDataBase.getDatabase(application);
        itemDao = itemDataBase.itemDao();
        listLiveData = itemDao.getItems();
    }
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Item>> getListLiveData(){

        return listLiveData;
    }

    public void insert (Item item) {
        new insertAsyncTask(itemDao).execute(item);
    }

    private static class insertAsyncTask extends AsyncTask<Item, Void, Void> {

        private ItemDao mAsyncTaskDao;

        insertAsyncTask(ItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Item... params) {
            mAsyncTaskDao.inset(params[0]);
            return null;
        }
    }
}

