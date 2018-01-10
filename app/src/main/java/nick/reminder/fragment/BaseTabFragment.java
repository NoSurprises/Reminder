package nick.reminder.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nick.reminder.R;
import nick.reminder.adapter.RemindListAdapter;
import nick.reminder.dto.RemindDTO;

/**
 * Created by Nick on 1/10/2018.
 */

public abstract class BaseTabFragment extends Fragment {
    private final int LAYOUT = R.layout.fragment_example;
    protected View view;
    protected Context mainContext;
    @BindView(R.id.recycler_view)
    RecyclerView recycler;
    private String title;


    public abstract int getTitleResource();

    public abstract int getFragmentLayout();

    public RemindListAdapter getAdapterForFragment() {
        return new RemindListAdapter(createMockData());
    }

    private List<RemindDTO> createMockData() {
        List<RemindDTO> data = new ArrayList<>();
        data.add(new RemindDTO("first"));
        data.add(new RemindDTO("second"));
        data.add(new RemindDTO("third"));
        return data;
    }


    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(getFragmentLayout(), container, false);

        ButterKnife.bind(this, view);
//        recycler = view.findViewById(R.id.recycler_view);

        recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recycler.setAdapter(getAdapterForFragment());
        return view;
    }

    public String getTitle() {
        if (title == null) {
            title = mainContext.getString(getTitleResource());
        }
        return title;
    }


    public void setMainContext(Context c) {
        mainContext = c;
    }
}
