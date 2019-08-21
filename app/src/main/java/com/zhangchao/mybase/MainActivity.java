package com.zhangchao.mybase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.zhangchao.common.media.Camera2Activity;
import com.zhangchao.common.media.CameraActivity;
import com.zhangchao.mybase.test.AActivity;
import com.zhangchao.mybase.test.MediaActivity;
import com.zhangchao.mybase.test.UIActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private RecyclerView listView;

  private static List<Item> data = new ArrayList<Item>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initList();
    listView = findViewById(R.id.main_list);
    LinearLayoutManager manager = new LinearLayoutManager(this);
    listView.setLayoutManager(manager);
    MainAdapter adapter = new MainAdapter(this,data);
    listView.setAdapter(adapter);
  }

  private void initList() {
    UIActivity activity = new UIActivity();
    data.add(new Item("UI测试",activity.getClass()));
    AActivity aActivity = new AActivity();
    data.add(new Item("Actvity生命周期测试",aActivity.getClass()));
    MediaActivity mediaActivity = new MediaActivity();
    data.add(new Item("多媒体功能测试",mediaActivity.getClass()));
    CameraActivity cameraActivity = new CameraActivity();
    data.add(new Item("自定义相机",cameraActivity.getClass()));
    Camera2Activity camera2Activity = new Camera2Activity();
    data.add(new Item("自定义相机2",camera2Activity.getClass()));
  }

  public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VH>{

    private Context context;
    private List<Item> data;

    public MainAdapter(Context context, List<Item> data){
      this.context = context;
      this.data = data;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(this.context).inflate(R.layout.main_item,parent,false);
      return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {

      holder.mBtGo.setText(data.get(position).name);
      holder.mBtGo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent intent = new Intent(context,data.get(position).cls);
          context.startActivity(intent);
        }
      });
    }

    @Override
    public int getItemCount() {
      return data.size();
    }

    public class VH extends RecyclerView.ViewHolder{

      public Button mBtGo;

      public VH(View itemView) {
        super(itemView);
        mBtGo = itemView.findViewById(R.id.btn_go);
      }
    }
  }

  public class Item{

    public Item(String name, Class<?> cls){
      this.name = name;
      this.cls = cls;
    }

    public String name;
    public Class<?> cls;
  }


}
