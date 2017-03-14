package com.zwh.gitsearch.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zwh.gitsearch.R;
import com.zwh.gitsearch.bean.UsersBean;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewItem> {
    private UsersBean usersBean;
    private Context context;

    public UserAdapter(Context context, UsersBean users) {
        this.usersBean = users;
        this.context = context;
    }

    @Override
    public UserAdapter.ViewItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewItem(View.inflate(context, R.layout.user_info_item, null));
    }

    @Override
    public void onBindViewHolder(ViewItem holder, int position) {
        UsersBean.ItemsEntity itemsEntity = usersBean.getItems().get(position);

        holder.mUserName.setText(itemsEntity.getLogin());
        Glide.with(context).load(itemsEntity.getAvatar_url()).into(holder.mAvatar);
    }

    @Override
    public int getItemCount() {
        return usersBean == null ? 0 : usersBean.getItems().size();
    }

    public static class ViewItem extends RecyclerView.ViewHolder {
        ImageView mAvatar;
        TextView mLanguage;
        TextView mMostLanguage;
        TextView mUserName;

        public ViewItem(View itemView) {
            super(itemView);
            mAvatar = (ImageView) itemView.findViewById(R.id.avatar);
            mLanguage = (TextView) itemView.findViewById(R.id.language);
            mMostLanguage = (TextView) itemView.findViewById(R.id.most_language);
            mUserName = (TextView) itemView.findViewById(R.id.user_name);
        }
    }
}
