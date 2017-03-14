package com.zwh.gitsearch.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zwh.gitsearch.R;
import com.zwh.gitsearch.bean.LanguageBean;
import com.zwh.gitsearch.bean.UsersBean;
import com.zwh.gitsearch.presenter.RepoPresenter;

/**
 * Created by zhengwenhui on 14/03/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewItem> implements IRepoView {
    private UsersBean mUsersBean;
    private Context mContext;
    private RepoPresenter mRepoPresenter;

    public UserAdapter(Context context, UsersBean users) {
        this.mUsersBean = users;
        this.mContext = context;
        mRepoPresenter = new RepoPresenter(this);
    }

    @Override
    public UserAdapter.ViewItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewItem(View.inflate(mContext, R.layout.user_info_item, null));
    }

    @Override
    public void onBindViewHolder(ViewItem holder, int position) {
        UsersBean.ItemsEntity itemsEntity = mUsersBean.getItems().get(position);

        holder.mUserName.setText(itemsEntity.getLogin());
        Glide.with(mContext).load(itemsEntity.getAvatar_url()).into(holder.mAvatar);

        LanguageBean languageBean = itemsEntity.getLanguageBean();
        if (languageBean == null) {
            mRepoPresenter.loadRepos(itemsEntity.getLogin());

            holder.mLanguage.setText(null);
            holder.mMostLanguage.setText(null);
        } else {
            holder.mLanguage.setText(languageBean.getLanguage());
            holder.mMostLanguage.setText(languageBean.getMostUsedLanguage());
        }
    }

    @Override
    public int getItemCount() {
        return mUsersBean == null ? 0 : mUsersBean.getItems().size();
    }


    @Override
    public void setRepo(LanguageBean languageBean) {
        for (UsersBean.ItemsEntity itemsEntity : mUsersBean.getItems()) {
            if (itemsEntity.getLogin().equals(languageBean.getLogin())) {
                itemsEntity.setLanguageBean(languageBean);
                this.notifyDataSetChanged();
                break;
            }
        }
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
