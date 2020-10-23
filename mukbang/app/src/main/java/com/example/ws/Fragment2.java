package com.example.ws;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


public class Fragment2 extends Fragment {
    SecondActivity s;
    ListView listView;
    LinearLayout container;
    ArrayList<Item> list;

    public Fragment2(SecondActivity s) {
        this.s= s;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = null;
        viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_2,container,false);
        listView = viewGroup.findViewById(R.id.listView);
        container = viewGroup.findViewById(R.id.container);
        list = new ArrayList<>();
        getData();

        return viewGroup;
    }

    private void getData(){
        String url = "http://192.168.0.100/android/bistro.jsp";
        ItemAsync itemAsync = new ItemAsync();
        itemAsync.execute(url);
    }
    class ItemAsync extends AsyncTask<String,Void,String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(s);
            progressDialog.setTitle("Get Data...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0].toString();
            String result = HttpConnect.getString(url);
            return result; // return 하면 onPostExcute 로 간다.
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            JSONArray ja = null;
            try {
                ja = new JSONArray(s);
                for(int i=0;i<ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);
                    String name = jo.getString("name");
                    String id = jo.getString("id");
                    String img = jo.getString("img");
                    int rank = jo.getInt("rank");

                    Item item = new Item(id,name,img,rank);
                    list.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ItemAdapter itemAdapter = new ItemAdapter();
            listView.setAdapter(itemAdapter);
        }
    } // end AsyncTask

    class ItemAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = null;
            LayoutInflater inflater = (LayoutInflater) s.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item,container,true);
            TextView tx_id = itemView.findViewById(R.id.textView3);
            TextView tx_name = itemView.findViewById(R.id.textView4);

            tx_id.setText(list.get(position).getId());
            tx_name.setText(list.get(position).getName());

            final ImageView imageView = itemView.findViewById(R.id.imageView);
            String img = list.get(position).getImg();
            final String url = "http://192.168.0.100/android/img/";
            GetImg t1 = new GetImg(img,url,imageView);
            t1.start();


            // rank에 따른 이미지 삽입
            final ImageView imageView2 = itemView.findViewById(R.id.imageView2);
            int rank = list.get(position).getRank();

            String rimg = null;
            if(rank == 1){
                rimg = "gold.jpg";
            }else if(rank == 2){
                rimg = "silver.jpg";
            }else{
                rimg = "bronze.jpg";
            }
            GetImg t2 = new GetImg(rimg,url,imageView2);
            t2.start();



            return itemView;
        }

        //Thread 클래스
        class GetImg extends Thread{
            String img;
            String url;
            ImageView imageView;
            public GetImg(String img,String url,ImageView imageView){
                this.img = img;
                this.url = url;
                this.imageView = imageView;
            }

            @Override
            public void run() {
                URL httpurl = null;
                InputStream is = null;
                try {
                    httpurl = new URL(url+img);
                    is = httpurl.openStream();
                    final Bitmap bm = BitmapFactory.decodeStream(is);
                    s.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bm);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

}