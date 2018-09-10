package com.madonasyombua.roomexample.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.madonasyombua.roomexample.R;
import com.madonasyombua.roomexample.adapters.ItemAdapter;
import com.madonasyombua.roomexample.helpers.ItemViewModel;
import com.madonasyombua.roomexample.models.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    @BindView(R.id.main_recyclerview)
    RecyclerView mainRecyclerView;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    private ItemViewModel itemViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ItemAdapter itemAdapter = new ItemAdapter();
        mainRecyclerView.setAdapter(itemAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(linearLayoutManager);


        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        itemViewModel.getListLiveData().observe(this, itemAdapter::setItemList);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
            startActivityForResult(intent,REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Item item = new Item(data.getStringExtra(AddItemActivity.EXTRA_REPLAY),
                    data.getStringExtra(AddItemActivity.EXTRA_REPLAY2),
                    data.getStringExtra(AddItemActivity.EXTRA_REPLAY3));
            itemViewModel.insert(item);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
