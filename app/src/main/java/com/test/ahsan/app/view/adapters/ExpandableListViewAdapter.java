//package com.finja.abl.wallet.view.adapters;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.TextView;
//
//import com.finja.abl.wallet.R;
//
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Created by ahsan on 24/09/2018.
// */
//
//public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
//
//    private Context _context;
//    private List<String> _listDataHeader; // header titles
//    // child data in format of header title, child title
//    private HashMap<String, List<String>> _listDataChild;
//
//    public ExpandableListViewAdapter(Context context, List<String> listDataHeader,
//                                     HashMap<String, List<String>> listChildData) {
//        this._context = context;
//        this._listDataHeader = listDataHeader;
//        this._listDataChild = listChildData;
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosititon) {
//        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
//                .get(childPosititon);
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, final int childPosition,
//                             boolean isLastChild, View convertView, ViewGroup parent) {
//
//        final String childText = (String) getChild(groupPosition, childPosition);
//
//        if (convertView == null) {
//            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            assert infalInflater != null;
//            convertView = infalInflater.inflate(R.layout.nav_menu_child_item, null);
//        }
//
//        TextView txtListChild = (TextView) convertView.findViewById(R.id.tvMenuTitle);
//
//        txtListChild.setText(childText);
//        return convertView;
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        if (!this._listDataChild.isEmpty()) {
//            return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
//        }
//        return -1;
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return this._listDataHeader.get(groupPosition);
//    }
//
//    @Override
//    public int getGroupCount() {
//        return this._listDataHeader.size();
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded,
//                             View convertView, ViewGroup parent) {
//        String headerTitle = (String) getGroup(groupPosition);
//        if (convertView == null) {
//            LayoutInflater infalInflater = (LayoutInflater) this._context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            assert infalInflater != null;
//            convertView = infalInflater.inflate(R.layout.nav_menu_header_item, null);
//        }
//
//        TextView lblListHeader = (TextView) convertView.findViewById(R.id.tvMenuTitle);
//        lblListHeader.setTypeface(null, Typeface.BOLD);
//        lblListHeader.setText(headerTitle);
//
//        return convertView;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//}