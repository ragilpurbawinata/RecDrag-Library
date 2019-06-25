# RecDrag-Library
Simple library to dragable recyclerview

## Installing
To get a Git project into your build (Using gradle) :

Step 1. Add the JitPack repository to your build file
```java
allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency
```java
dependencies {
    implementation 'com.github.ragilpurbawinata:RecDrag-Library:Tag'
}
```

## How to use
## 1. In your activity, add the following code
  * Implement OnStartDragListener
```java
public class MenuGridActivity extends AppCompatActivity implements OnStartDragListener {
....
}
```

  * Add a variable
```java
private ItemTouchHelper mItemTouchHelper;
```

  * After your recyclerview set adapter, add this line code
```java
ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(rvAdapter);
mItemTouchHelper = new ItemTouchHelper(callback);
mItemTouchHelper.attachToRecyclerView(rv);
```

  * In methode onStartDrag, add this line code
```java
@Override
public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
  mItemTouchHelper.startDrag(viewHolder);
}
```

## 2. In your recyclerview adapter, add the following code
  * Implement ItemTouchHelperAdapter
```java
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.Holder> implements ItemTouchHelperAdapter {
....
}
````

  * Add a variable
```java
private OnStartDragListener mDragStartListener;
```

  * Pass the listener via constructor
```java
public RvAdapter(List<ModelMenu> modelMenus, OnStartDragListener dragStartListener) {
  this.mDragStartListener = dragStartListener;
  this.modelMenus = modelMenus;
}
```

  * In methode onBindViewHolder, add this line code
```java
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
```

  * In methode onItemMove, add this line code
```java
@Override
public boolean onItemMove(int fromPosition, int toPosition) {
  Collections.swap(modelMenus, fromPosition, toPosition);
  notifyItemMoved(fromPosition, toPosition);
  return true;
}
```

  * In methode onItemDismiss, add this line code
```java
@Override
public void onItemDismiss(int position) {
  modelMenus.remove(position);
  notifyItemRemoved(position);
}
```
