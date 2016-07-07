package com.example.credit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.credit.Activitys.ClaimDetailsActivity;
import com.example.credit.Activitys.SearchFirmActivty;
import com.example.credit.Entitys.DataManager;
import com.example.credit.R;
import com.example.credit.Services.CallServer;
import com.example.credit.Utils.GsonUtil;
import com.example.credit.Utils.MyhttpCallBack;
import com.example.credit.Utils.URLconstant;
import com.yolanda.nohttp.RequestMethod;

import java.util.List;

public class MyClaim_listAdapter extends BaseAdapter {
    private Context context;
    private List<DataManager.MyClaimUtils.DataBean.ClaimlistBean> list;
    ViewHolder vh = null;
    public MyClaim_listAdapter(Context context, List<DataManager.MyClaimUtils.DataBean.ClaimlistBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_my_claim_item, null);
            vh = new ViewHolder();
            vh.myclaim_name=(TextView) view.findViewById(R.id.myclaim_name);
            vh.myclaim_state=(TextView) view.findViewById(R.id.myclaim_state);
            vh.myclaim_base=(TextView) view.findViewById(R.id.myclaim_base);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.myclaim_name.setText(list.get(position).ENTERNAME);
        vh.myclaim_state.setText(list.get(position).STATUSNAME);
        vh.myclaim_base.setText(list.get(position).REFUSEREASON);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                GsonUtil MyClaimRuerst = new GsonUtil(URLconstant.URLINSER + URLconstant.MYCLAIMURL, RequestMethod.GET);
//                MyClaimRuerst.add("deviceId",(new Build()).MODEL);
//                MyClaimRuerst.add("token", SearchFirmActivty.MD5s("86D9D7F53FCA45DD93E2D83DFCA0CB42" + (new Build()).MODEL));
//                MyClaimRuerst.add("KeyNo","86D9D7F53FCA45DD93E2D83DFCA0CB42");
//                MyClaimRuerst.add("claimId",DataManager.MyClaimUtilsModel.data.Claimlist.get(position).CLAIMID);
//                CallServer.getInstance().add(context,MyClaimRuerst, MyhttpCallBack.getInstance(),0x304,true,false,true);
                Intent i=new Intent(context, ClaimDetailsActivity.class);
                i.putExtra("position",position);
                context.startActivity(i);
            }
        });
        return view;
    }

    public class ViewHolder {
        TextView myclaim_name;//公司名称
        TextView myclaim_state;//法人代表
        TextView myclaim_base;//注册资金
    }
}
