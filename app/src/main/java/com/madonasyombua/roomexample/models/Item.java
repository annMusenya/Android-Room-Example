package com.madonasyombua.roomexample.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;



/**
 * @author madon
 *
 * A basic class representing an entity that is a row in a one-column database table.
 *
 * @ Entity - You must annotate the class as an entity and supply a table name if not class name.
 * @ PrimaryKey - You must identify the primary key.
 * @ ColumnInfo - You must supply the column name if it is different from the variable name.
 *
 */
@Entity(tableName = "items")
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "itemOne")
    private String itemOne;
    private String itemTwo;
    private String itemThree;



    @Ignore
    public Item(@Nullable String itemOne, String itemTwo, String itemThree) {
        this.itemOne = itemOne;
        this.itemTwo = itemTwo;
        this.itemThree = itemThree;
    }

    /**
     * second constructor with an id
     * @param itemOne item one
     * @param itemTwo item two
     * @param itemThree item three
     * @param id id
     */
    public Item(@Nullable String itemOne, String itemTwo, String itemThree, int id) {
        this.itemOne = itemOne;
        this.itemTwo = itemTwo;
        this.itemThree = itemThree;
        this.id = id;
    }

    @Nullable
    public String getItemOne() {
        return itemOne;
    }


    public String getItemTwo() {
        return itemTwo;
    }


    public String getItemThree() {
        return itemThree;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
