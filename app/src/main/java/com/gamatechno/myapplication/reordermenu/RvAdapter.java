package com.gamatechno.myapplication.reordermenu;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamatechno.myapplication.R;
import com.gamatechno.myapplication.reordermenu.helper.ItemTouchHelperAdapter;
import com.gamatechno.myapplication.reordermenu.helper.OnStartDragListener;

import java.util.Collections;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Holder> implements ItemTouchHelperAdapter {
    List<ModelMenu> modelMenus;
    private OnStartDragListener mDragStartListener;

    public RvAdapter(List<ModelMenu> modelMenus, OnStartDragListener dragStartListener) {
        this.mDragStartListener = dragStartListener;
        this.modelMenus = modelMenus;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_tem, viewGroup, false);
        return new Holder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int i) {
        ModelMenu modelMenu = modelMenus.get(i);
        holder.ic.setImageResource(modelMenu.getIcon());
        holder.tit.setText(modelMenu.getTitle());

        // Start a drag whenever the handle view it touched
        holder.ic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelMenus.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(modelMenus, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        modelMenus.remove(position);
        notifyItemRemoved(position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        ImageView ic;
        TextView tit;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ic = itemView.findViewById(R.id.icon);
            tit = itemView.findViewById(R.id.title);
        }
    }
}
