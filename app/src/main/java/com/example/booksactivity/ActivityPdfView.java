package com.example.booksactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class ActivityPdfView extends AppCompatActivity {
    PDFView pdfView;
    int position=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        pdfView=findViewById(R.id.pdfView);
        position=getIntent().getIntExtra("Position",-1);
        viewPdf();
    }

    private void viewPdf() {
        pdfView.fromFile(MainActivity.mFiles.get(position)).enableSwipe(true)
                .enableAnnotationRendering(true).scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
}