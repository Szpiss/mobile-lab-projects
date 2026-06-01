package cn.edu.android.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {
    private List<NoteBean> list = new ArrayList<>();
    private final Context mContext;
    private final ItemClickListener itemClickListener;

    public NoteAdapter(Context context, ItemClickListener itemClickListener) {
        this.mContext = context;
        this.itemClickListener = itemClickListener;
    }

    public void setData(List<NoteBean> list) {
        this.list = list == null ? new ArrayList<>() : list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        View itemView = holder.itemView;
        NoteBean bean = list.get(position);
        holder.tvTitle.setText(bean.getTitle());
        holder.tvContent.setText(bean.getContent());
        holder.tvTime.setText(bean.getTime());
        itemView.setOnClickListener(v -> itemClickListener.onItemClick(itemView, holder.getBindingAdapterPosition()));
        itemView.setOnLongClickListener(v -> {
            itemClickListener.onItemLongClick(itemView, holder.getBindingAdapterPosition());
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        TextView tvTime;

        public MyViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tv_title);
            tvContent = view.findViewById(R.id.tv_content);
            tvTime = view.findViewById(R.id.tv_time);
        }
    }

    public interface ItemClickListener {
        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);
    }
}
