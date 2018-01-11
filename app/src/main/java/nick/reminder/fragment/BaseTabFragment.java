package nick.reminder.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import nick.reminder.MainActivity;
import nick.reminder.R;
import nick.reminder.adapter.RemindListAdapter;
import nick.reminder.database.ReminderDataModel;
import nick.reminder.database.ReminderHelper;

/**
 * Created by Nick on 1/10/2018.
 */

public abstract class BaseTabFragment extends Fragment {
    private static final String TAG = "daywint";
    private final int LAYOUT = R.layout.fragment_example;
    protected View view;
    protected Context mainContext;
    @BindView(R.id.recycler_view)
    RecyclerView recycler;
    private String title;
    private RemindListAdapter adapter;
    private SQLiteDatabase db;


    public abstract int getTitleResource();

    public abstract int getFragmentLayout();

    public RemindListAdapter getAdapterForFragment() {
        return adapter;
    }


    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(getFragmentLayout(), container, false);

        ButterKnife.bind(this, view);
//        recycler = view.findViewById(R.id.recycler_view);

        setMainContext(view.getContext());
        initAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recycler.setAdapter(adapter);
        return view;
    }

    private void initAdapter() {
        db = ((MainActivity) getActivity()).getDb();
        Cursor query = getCursorFromCurrentTab(db);
        adapter = new RemindListAdapter(query);
    }

    public Cursor getCursorFromCurrentTab(SQLiteDatabase db) {
        Cursor cursor = db.query(false, ReminderHelper.DATABASE_NAME,
                null,
                ReminderDataModel.TAB + "=\"" + getTitle() + "\"",
                null,
                null,
                null,
                null, null);

        cursor.moveToFirst();
        Log.d(TAG, "getCursorFromCurrentTab: " + cursor + " " + cursor.getCount());

        return cursor;
    }

    public String getTitle() {
        if (title == null) {
            title = mainContext.getString(getTitleResource());
        }
        return title;
    }

    public void swapCursor(Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    public void swapCursor(SQLiteDatabase db) {
        swapCursor(getCursorFromCurrentTab(db));
    }

    public void setMainContext(Context c) {
        mainContext = c;
    }
}
