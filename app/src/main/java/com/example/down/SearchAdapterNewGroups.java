package com.example.down;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchAdapterNewGroups extends RecyclerView.Adapter<SearchAdapterNewGroups.SearchViewHolder> {
    Context context;
    ArrayList<String> nameList;
    ArrayList<String> emailList;
    ArrayList<Integer> avatarList;
    ArrayList<String> UIDList;
    ArrayList<Boolean> selList;
    ArrayList<String> selectList = new ArrayList<String>();
    ArrayList<String> addListUID = new ArrayList<String>();
    ArrayList<String> addListName = new ArrayList<String>();
    ArrayList<Integer> addListAvatar = new ArrayList<Integer>();
    ArrayList<GroupElement> addList = new ArrayList<GroupElement>();
    String UID;
    Integer avatarIndex;



    class SearchViewHolder extends RecyclerView.ViewHolder {
        TextView name, email;
        ImageView avatarImage;
        View entireView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            avatarImage = (ImageView) itemView.findViewById(R.id.avatarImage);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            entireView = itemView;
        }
    }

    public SearchAdapterNewGroups(Context context, ArrayList<String> nameList, ArrayList<String> emailList, ArrayList<Integer> avatarList, ArrayList<String> UIDList, ArrayList<Boolean> selList) {
        this.context = context;
        this.nameList = nameList;
        this.emailList = emailList;
        this.avatarList = avatarList;
        this.UIDList = UIDList;
        this.selList = selList;
    }

    @Override
    public SearchAdapterNewGroups.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_items, parent, false);
        return new SearchAdapterNewGroups.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchViewHolder holder, final int position) {
        holder.name.setText(nameList.get(position));
        holder.email.setText(emailList.get(position));
        UID = (UIDList.get(position));

        TypedArray avatars = this.context.getResources().obtainTypedArray(R.array.avatar_imgs);
        avatarIndex = (avatarList.get(position));
        Glide.with(context).load(avatars.getDrawable(avatarIndex)).placeholder(R.mipmap.ic_launcher_round).into(holder.avatarImage);

        String arr[] = nameList.get(position).split(" ", 2);
        final GroupElement thisUser = new GroupElement(arr[0], UID, avatarIndex);
        setColor(thisUser, holder);

        //Glide.with(context).load(avatars.getDrawable(avatarIndex)).placeholder(R.mipmap.ic_launcher_round).into(holder.avatarImage);
        //Glide.with(context).load(R.drawable.avatar0).asBitmap().placeholder(R.mipmap.ic_launcher_round).into(holder.avatarImage);
        //Glide.with(context).load(avatarList.get(position)).asBitmap().placeholder(R.mipmap.ic_launcher_round).into(holder.avatarImage);

        holder.entireView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adjustGroup(thisUser);
                setColor(thisUser, holder, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public void adjustGroup(GroupElement thisUser) {
        if (addList.contains(thisUser)) {
            addList.remove(thisUser);
        } else {
            addList.add(thisUser);
        }
    }

    public class GroupElement
    {
        // Instance Variables
        String name;
        String UID;
        int avatarIndex;

        // Constructor Declaration of Class
        public GroupElement(String name, String UID,
                   int avatarIndex)
        {
            this.name = name;
            this.UID = UID;
            this.avatarIndex = avatarIndex;
        }

        // method 1
        public String getName()
        {
            return name;
        }

        // method 2
        public String getUID()
        {
            return UID;
        }

        // method 3
        public int getAvatarIndex()
        {
            return avatarIndex;
        }
    }

    /*
    public void ifSel(final SearchViewHolder holder, int position) {
        if (selectList.get(position)) {
            selectList.set(position, false);
            holder.entireView.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            selectList.set(position, true);
            holder.entireView.setBackgroundColor(Color.parseColor("#909aa0"));
        }

    }
    */

    public void setColor(GroupElement thisUser, final SearchViewHolder holder, Integer position) {
        if (addList.contains(thisUser)) {
            holder.entireView.setBackgroundColor(Color.parseColor("#909aa0"));
        } else {
            holder.entireView.setBackgroundColor(Color.parseColor("#ffffff"));

        }
    }

    public void setColor(GroupElement thisUser, final SearchViewHolder holder) {
        if (addList.contains(thisUser)) {
            holder.entireView.setBackgroundColor(Color.parseColor("#909aa0"));
        } else {
            holder.entireView.setBackgroundColor(Color.parseColor("#ffffff"));

        }
    }
}