package com.example.booksactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   public static ArrayList<File> mFiles=new ArrayList<>();
   RecyclerView recyclerView;
    File folder;
    String[] items;
   public  static  final int REQUET_PERMISSION =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv_view_pdf);
        permission();



    }

    private void permission() {
        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                 Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){

                Toast.makeText(this,"please grant permission",Toast.LENGTH_SHORT).show();
            } else{
                  ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUET_PERMISSION);
            }
        }

        else{
            Toast.makeText(this,"PERMISSION Granted", Toast.LENGTH_SHORT).show();
            initViews();


        }
    }
    private void initViews() {
        folder=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        mFiles=getPdfFiles(folder);
        ArrayList<File> myPdf=getPdfFiles(Environment.getExternalStorageDirectory());
        items = new String[myPdf.size()];
        for(int i=0;i<items.length;i++){

            items[i]=myPdf.get(i).getName().replace(".pdf","");
        }

        PDFAdapter bookAdapter=new PDFAdapter(this,mFiles,items);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ,RecyclerView.VERTICAL,false));

    }

    private ArrayList<File> getPdfFiles(File externalStorageDirectory) {
        ArrayList<File> arrayList=new ArrayList<>();
        File[] files=folder.listFiles();
        if(files!=null)
        {
          for(File singleFile :files){

              if(singleFile.isDirectory()&& !singleFile.isHidden()){
                  arrayList.addAll(getPdfFiles(singleFile));}
              else{
                     if(singleFile.getName().endsWith(".pdf"))
                     {

                         arrayList.add(singleFile);
                     }

              }
                          }}
        return  arrayList;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUET_PERMISSION){

            if(     grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                initViews();
            }
            else{
                Toast.makeText(this ,"Please Grant Permission",Toast.LENGTH_SHORT).show();

            }
        }


    }

}
