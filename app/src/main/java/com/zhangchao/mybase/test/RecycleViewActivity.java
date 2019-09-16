package com.zhangchao.mybase.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.zhangchao.common.base.BaseActivity;
import com.zhangchao.common.ui.DividerGridItemDecoration;
import com.zhangchao.mybase.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间: 2019/08/29
 * 作者: zhangchao042@ke.com
 * 描述: 测试RecycleView的Activity
 */
public class RecycleViewActivity extends BaseActivity {

  private RecyclerView mRecycleView;
  private List<String> data;
  private GridView mGridView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.recycleview_test_layout);
    mRecycleView = findViewById(R.id.recycleview);
    data = new ArrayList<>();
    for(int i = 0; i < 52; i++){
      data.add(i + "");
    }
    //LinearLayoutManager manager = new LinearLayoutManager(this);
    //manager.setOrientation(LinearLayoutManager.VERTICAL);
    GridLayoutManager manager = new GridLayoutManager(this,4);
    MyAdapter adapter = new MyAdapter(this,data);

    mRecycleView.setLayoutManager(manager);
    mRecycleView.setAdapter(adapter);
    mRecycleView.addItemDecoration(new DividerGridItemDecoration(this));

    mGridView = findViewById(R.id.gridview);
    GridAdapter gridAdapter = new GridAdapter(this,data);
    mGridView.setAdapter(gridAdapter);

  }

  public static class GridAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> data;

    public GridAdapter(Context context, List<String> data){
      this.mContext = context;
      this.data = data;
    }

    @Override
    public int getCount() {
      return data.size();
    }

    @Override
    public Object getItem(int position) {
      return data.get(position);
    }

    @Override
    public long getItemId(int position) {
      return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view = LayoutInflater.from(mContext).inflate(R.layout.recycleview_item,parent,false);
      ((TextView)view.findViewById(R.id.tv_show)).setText(data.get(position));
      return view;
    }
  }

  public static class MyAdapter extends RecyclerView.Adapter<VH>{

    private Context mContext;
    private List<String> data;

    public MyAdapter(Context context, List<String> data){
      this.mContext = context;
      this.data = data;
    }

    /**
     * 在Item复用的时候，不会走onCreateViewHolder()方法
     * 重复调用onBindViewHolder()
     */
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      RelativeLayout view = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.recycleview_item,parent,false);
      return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
      String item = data.get(position);
      holder.mTvShow.setText(item);
    }

    @Override
    public int getItemCount() {
      return data.size();
    }

  }

  public static class VH extends RecyclerView.ViewHolder{

    public TextView mTvShow;
    public RelativeLayout mRoot;

    public VH(View itemView) {
      super(itemView);
      mTvShow = itemView.findViewById(R.id.tv_show);
      mRoot=itemView.findViewById(R.id.relative);
    }
  }

}
