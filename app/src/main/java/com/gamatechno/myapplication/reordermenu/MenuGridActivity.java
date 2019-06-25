package com.gamatechno.myapplication.reordermenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.gamatechno.myapplication.R;
import com.gamatechno.myapplication.reordermenu.helper.OnStartDragListener;
import com.gamatechno.myapplication.reordermenu.helper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class MenuGridActivity extends AppCompatActivity implements OnStartDragListener {
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_grid);

        List<ModelMenu> list = new ArrayList<>();
        ModelMenu modelMenu = new ModelMenu();
        modelMenu.icon = R.drawable.ic_adult;
        modelMenu.title = "ITEM 1";
        list.add(modelMenu);

        modelMenu = new ModelMenu();
        modelMenu.icon = R.drawable.ic_airplane_shape;
        modelMenu.title = "ITEM 2";
        list.add(modelMenu);

        modelMenu = new ModelMenu();
        modelMenu.icon = R.drawable.ic_airport;
        modelMenu.title = "ITEM 3";
        list.add(modelMenu);

        modelMenu = new ModelMenu();
        modelMenu.icon = R.drawable.ic_launcher_background;
        modelMenu.title = "MORE";
        list.add(modelMenu);

        RecyclerView rv = findViewById(R.id.rv);
        RvAdapter rvAdapter = new RvAdapter(list, this);
        rv.setLayoutManager(new GridLayoutManager(this, 4));
        rv.setAdapter(rvAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(rvAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rv);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
