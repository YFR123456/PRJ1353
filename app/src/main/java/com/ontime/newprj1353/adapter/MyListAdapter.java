package com.ontime.newprj1353.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ontime.newprj1353.R;
import com.ontime.newprj1353.activity.MainActivity;
import com.ontime.newprj1353.activity.ProductListActivity;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by yafr1hz1 on 2017/8/31.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;

    public MyListAdapter(Context context) {
        this.context = context;
    }

    private MyItemClickListener mListener;

    public static interface MyItemClickListener {
        public void onItemClick(View view, int postion);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        //TODO 根据json解析的结果找到对应产品的名称,蓝牙标签,图片等进行数据绑定与显示
//        holder.itemName.setText(ProductListActivity.productName);
//        holder.itemCode.setText(ProductListActivity.productCode);
//       // holder.bluetoothTagId.setText("蓝牙标签ID");
//
//        String url = ProductListActivity.picOneUrl;
//        //http://139.224.104.4:8085/iknow/upload/product/1/2017/8/18/1014148680.jpg);
//        Bitmap bm = null;
//        try {
//            URL aURL = new URL(url);
//            URLConnection conn = aURL.openConnection();
//            conn.connect();
//            InputStream is = conn.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//            bm = BitmapFactory.decodeStream(bis);
//            bis.close();
//            is.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        holder.imageView.setImageBitmap(bm);
//
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);

        //TODO 商品详情的按钮,点击之后跳转到商品详情页面进行商品的展示,自动播放视频的无限循环,如果检测到相应的蓝牙标签,则进入产品的图片展示
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 跳转到商品的 Activity
                if (ProductListActivity.productName == null) {
                    Toast.makeText(context,"当前未绑定产品信息", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        //TODO 根据json解析的结果返回相应的数据列表的条数,每个json数据目前只带有一条信息

        //Log.d("-------",ProductListActivity.list.size()+"++++ ");
        return 1;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public static TextView itemName;
        public static TextView itemCode;
        public static TextView bluetoothTagId;
        public static ImageView imageView;
        public Button button;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemCode = (TextView) itemView.findViewById(R.id.item_code);
            bluetoothTagId = (TextView) itemView.findViewById(R.id.bt_id);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            button = (Button) itemView.findViewById(R.id.button);
        }
    }

}
