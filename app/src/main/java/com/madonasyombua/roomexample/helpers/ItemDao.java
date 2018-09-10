package com.madonasyombua.roomexample.helpers;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.madonasyombua.roomexample.models.Item;

import java.util.List;

@Dao
public interface ItemDao {
    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from items ORDER BY id")
    LiveData<List<Item>> getItems();

    // We do not need a conflict strategy, because the word is our primary key, and you cannot
    // add two items with the same primary key to the database. If the table has more than one
    // column, you can use @Insert(onConflict = OnConflictStrategy.REPLACE) to update a row.

    @Insert
    void inset(Item item);

    @Query("DELETE FROM items")
    void deleteAll();

    @Query("SELECT * FROM items WHERE id = :id")
    LiveData<Item> loadItemsById(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItems(Item item);
}
