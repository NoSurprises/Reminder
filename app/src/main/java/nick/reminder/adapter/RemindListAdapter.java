package nick.reminder.adapter;

import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import nick.reminder.R;
import nick.reminder.database.ReminderDataModel;

/**
 * Created by Nick on 1/10/2018.
 */

public class RemindListAdapter extends RecyclerView.Adapter<RemindListAdapter.RemindViewHolder> {

    private Cursor data;

    public RemindListAdapter(Cursor data) {
        this.data = data;
    }

    public void swapCursor(Cursor data) {
        if (this.data != null) {
            this.data.close();
        }
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RemindViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.remind_item, parent, false);
        return new RemindViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RemindViewHolder holder, int position) {
        String title = data.getString(data.getColumnIndex(ReminderDataModel.TITLE));
        holder.title.setText(title);
        // TODO: 1/11/2018 bind description
    }

    @Override
    public int getItemCount() {
        return data.getCount();
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (data != null && !data.isClosed()) {
            data.close();
        }
    }

    public static class RemindViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.title)
        TextView title;


        public RemindViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
