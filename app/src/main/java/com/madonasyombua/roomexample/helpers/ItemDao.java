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

    @Query("SELECT * from items ORDER BY id")
    LiveData<List<Item>> getItems();

    @Insert
    void inset(Item item);

    @Query("DELETE FROM items")
    void deleteAll();

    @Query("SELECT * FROM items WHERE id = :id")
    LiveData<Item> loadItemsById(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItems(Item item);
}
