package com.rahul.jhakadi.Expanded;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahul.jhakadi.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpandedMenuAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<ExpandedMenuModel> mListHeader;
    private HashMap<ExpandedMenuModel, ArrayList<String>> mListChild;
    private ExpandableListView mExpandableListView;

    public ExpandedMenuAdapter(Context context, ArrayList<ExpandedMenuModel> listHeader, HashMap<ExpandedMenuModel, ArrayList<String>> listChild, ExpandableListView expandableListView) {
        this.mContext = context;
        this.mListHeader = listHeader;
        this.mListChild = listChild;
        this.mExpandableListView = expandableListView;
    }

    @Override
    public int getGroupCount() {
        return this.mListHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int childCount = 0;
        if (groupPosition == 0 || groupPosition == 1 || groupPosition == 3 || groupPosition == 4 || groupPosition == 5) {
            childCount = this.mListChild.get(this.mListHeader.get(groupPosition)).size();
        }
        return childCount;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mListHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.mListChild.get(this.mListHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View groupView = convertView;
        ExpandedMenuModel header = (ExpandedMenuModel) getGroup(groupPosition);

        if (groupView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            groupView = inflater.inflate(R.layout.list_header, null);
        }

        TextView headerName = groupView.findViewById(R.id.header_title);
        ImageView headerIcon = groupView.findViewById(R.id.header_icon);

        headerName.setTypeface(null, Typeface.NORMAL);
        headerName.setText(header.getItemName());
        // headerIcon.setImageResource(header.getItemIcon());

        return groupView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View childView = convertView;
        String childText = (String) getChild(groupPosition, childPosition);

        if (childView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            childView = inflater.inflate(R.layout.list_child, null);
        }

        TextView childName = childView.findViewById(R.id.child_title);
        childName.setText(childText);

        return childView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
