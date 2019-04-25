package com.example.android.app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView chefName;
    TextView chefSpecialty;
    CircleImageView chefImage;
    TextView chefDesc;
    ImageView ivMenu;

    public UserViewHolder(View itemView) {
        super(itemView);

        chefName = (TextView) itemView.findViewById(R.id.chefName);
        chefDesc = (TextView) itemView.findViewById(R.id.description);
        chefSpecialty = (TextView) itemView.findViewById(R.id.specialty);
        chefImage = (CircleImageView) itemView.findViewById(R.id.chefImage);
        ivMenu = (ImageView) itemView.findViewById(R.id.iv_menu);
    }
}
