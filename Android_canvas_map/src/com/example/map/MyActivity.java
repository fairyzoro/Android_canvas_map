package com.example.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

public class MyActivity extends Activity {
    public static final String FILENAME = "map.json";
    private CanvasMapView testView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        testView = (CanvasMapView) findViewById(R.id.testCanvas);
        JSONObject jsonInfo = initData(this,FILENAME);
        testView.setData(jsonInfo);
    }


    private JSONObject initData(Context context,String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int start = stringBuilder.indexOf("{");
        int end = stringBuilder.lastIndexOf("}");
        String result = stringBuilder.substring(start, end+1);

        JSONObject jsonObject = (JSONObject) JSONValue.parse(result);
        return jsonObject;
    }
}
