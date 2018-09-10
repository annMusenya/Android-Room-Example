package com.madonasyombua.roomexample;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.madonasyombua.roomexample.helpers.ItemDao;
import com.madonasyombua.roomexample.models.Item;
import com.madonasyombua.roomexample.room.ItemDataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ItemDaoTest {

    @Rule
    TaskExecutorRuleTest executorRuleTest = new TaskExecutorRuleTest();
    private ItemDao itemDao;
    private ItemDataBase itemDataBase;

    @Test
    public void insertAndGetItems() throws Exception{
        Item item = new Item("item1","item 2","item3");
        itemDao.inset(item);

        List<Item> allItems = LiveDataTest.getValue(itemDao.getItems());
        assertEquals(allItems.get(0).getItemOne(),item.getItemOne());

    }


    @Before
    public void dataBaseCreation(){
        Context context = InstrumentationRegistry.getTargetContext();

        itemDataBase = Room.inMemoryDatabaseBuilder(context,ItemDataBase.class)
                      //this is just allowed here for testing
                .allowMainThreadQueries()
                .build();
        itemDao = itemDataBase.itemDao();
    }

    @After
    public void closeTheDatabase() {
        itemDataBase.close();
    }

    @Test
    public void deleteAll() throws Exception {
        Item item = new Item("item1","item 2","item3");
        itemDao.inset(item);
        itemDao.deleteAll();
        List<Item> allItems = LiveDataTest.getValue(itemDao.getItems());
        assertTrue(allItems.isEmpty());

    }
}
