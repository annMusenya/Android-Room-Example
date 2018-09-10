package com.madonasyombua.roomexample.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.madonasyombua.roomexample.helpers.ItemDao;
import com.madonasyombua.roomexample.models.Item;

@Database(entities = {Item.class},version = 1,exportSchema = false)
public abstract class ItemDataBase  extends RoomDatabase{

    private static final String LOG_TAG = ItemDataBase.class.getSimpleName();
    public abstract ItemDao itemDao();

    private static ItemDataBase itemDataBaseInstance;
   public static ItemDataBase getDatabase(final Context context){

        if(itemDataBaseInstance == null){
            Log.d(LOG_TAG,"Creating Database");
            itemDataBaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ItemDataBase.class,"item_database")

                    .addCallback(roomCallback)
                    .build();
        }

        return  itemDataBaseInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(itemDataBaseInstance).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ItemDao mDao;

        PopulateDbAsync(ItemDataBase db) {
            mDao = db.itemDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

           return null;
        }
    }

}