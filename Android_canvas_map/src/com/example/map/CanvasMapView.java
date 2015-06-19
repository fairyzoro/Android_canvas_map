package com.example.map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;


public class CanvasMapView extends View{
    private JSONObject drawString;
    Paint paint = new Paint();
    Path path = new Path();
    public CanvasMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CanvasMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasMapView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(drawString!=null && !"".equals(drawString)){
        	float n = 1f; //设置一个比例
        	paint.setAntiAlias(true);
        	paint.setStrokeWidth(1.0f);
        	paint.setStyle(Paint.Style.FILL_AND_STROKE);
            JSONArray jsonArray = (JSONArray) drawString.get("data");
            String textName = "";
            float pathX = 0;
            float pathY = 0;
            JSONArray jsonInfo = null;
            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                jsonInfo = (JSONArray) jsonObject.get("coordinates");
                textName = (String) jsonObject.get("name");
                
                if("HD".equals(textName))
                	paint.setColor(Color.LTGRAY);
                else if("BY".equals(textName))
                	paint.setColor(Color.GREEN);
                else if("LW".equals(textName))
                	paint.setColor(Color.GRAY);
                else if("YX".equals(textName))
                	paint.setColor(Color.RED);
                else if("HZ".equals(textName))
                	paint.setColor(Color.DKGRAY);
                else if("TH".equals(textName))
                	paint.setColor(Color.LTGRAY);
                else if("PY".equals(textName))
                	paint.setColor(Color.YELLOW);
                else if("NS".equals(textName))
                	paint.setColor(Color.BLUE);
                else if("LG".equals(textName))
                	paint.setColor(Color.CYAN);
                else if("ZC".equals(textName))
                	paint.setColor(Color.GRAY);
                else
                	paint.setColor(Color.MAGENTA);
                
                
                List<Long> list = (List<Long>) jsonInfo.get(0);
                pathX = list.get(0).intValue()/n;
                pathY = list.get(1).intValue()/n;
                path = new Path();
                path.moveTo(pathX,pathY);
                for(int j=1;j<jsonInfo.size();j++){
                    list = (List<Long>) jsonInfo.get(j);
                    pathX = list.get(0).intValue()/n;
                    pathY = list.get(1).intValue()/n;
                    path.lineTo(pathX,pathY);
                }
                canvas.drawPath(path,paint);
            }
        }
    }


    public void setData(JSONObject jsonInfo) {
        drawString = jsonInfo;
        invalidate();
    }
}
