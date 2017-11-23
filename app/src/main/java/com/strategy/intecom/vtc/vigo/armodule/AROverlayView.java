package com.strategy.intecom.vtc.vigo.armodule;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Location;
import android.opengl.Matrix;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.strategy.intecom.vtc.vigo.R;
import com.strategy.intecom.vtc.vigo.armodule.helper.LocationHelper;
import com.strategy.intecom.vtc.vigo.armodule.model.ARPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ntdat on 1/13/17.
 */

public class AROverlayView extends View {

    Context context;
    private float[] rotatedProjectionMatrix = new float[16];
    private Location currentLocation;
    private List<ARPoint> arPoints;
    private Canvas mCanVas;
    private Bitmap[] mSpots;
    Paint mPaint = new Paint();


    public AROverlayView(Context context) {
        super(context);

        this.context = context;

//        //Demo points
//        arPoints = new ArrayList<ARPoint>() {{
//            add(new ARPoint("Sun Wheel", 90d, 110.8000, 0));
//            add(new ARPoint("Linh Ung Pagoda", 23.870932d, 180.8000, 0));
//        }};

        initDumpData();
        initBitmapData();
    }

    private void initDumpData() {
        arPoints = new ArrayList<>();

        arPoints.add(new ARPoint("Four Pillars", 90d, 110.8000, 0));
        arPoints.add(new ARPoint("Great portico", 23.870932d, 100.8000, 0));
        arPoints.add(new ARPoint("Khuê Văn pavillion", 5, 130, 0));
        arPoints.add(new ARPoint("82 doctor stelae & Well of Heavenly Clarity", 50, 70, 0));
        arPoints.add(new ARPoint("Countyard of the Sage sanctuary", 100, 20, 0));
    }


    private void initBitmapData() {
        mSpots = new Bitmap[arPoints.size()];
        for (int i = 0; i < mSpots.length; i++) {

            LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View locationMarkerView = mLayoutInflater.inflate(R.layout.tmp_item_point, null);

            TextView tvName = (TextView) locationMarkerView.findViewById(R.id.txt_title);
            tvName.setText(arPoints.get(i).getName());

            mSpots[i] = getViewBitmap(locationMarkerView);

        }
    }

    Bitmap getViewBitmap(View view) {
        //Get the dimensions of the view so we can re-layout the view at its current size
        //and create a bitmap of the same size
        int width = 150;
        int height = 80;

        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);

        //Cause the view to re-layout
        view.measure(measuredWidth, measuredHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        //Create a bitmap backed Canvas to draw the view into
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);

        //Now that the view is laid out and we have a canvas, ask the view to draw itself into the canvas
        view.draw(c);

        return b;
    }

    public void updateRotatedProjectionMatrix(float[] rotatedProjectionMatrix) {
        this.rotatedProjectionMatrix = rotatedProjectionMatrix;
        this.invalidate();
    }

    public void updateCurrentLocation(Location currentLocation) {
//        this.currentLocation = currentLocation;
        Location loc = new Location("Me");
        loc.setLatitude(20.3);
        loc.setLongitude(52.6);
        this.currentLocation = loc;
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (currentLocation == null) {
            return;
        }

        mCanVas = canvas;

        final int radius = 30;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(60);

        for (int i = 0; i < arPoints.size(); i++) {
            Bitmap blip = mSpots[i];

            float[] currentLocationInECEF = LocationHelper.WSG84toECEF(currentLocation);
            float[] pointInECEF = LocationHelper.WSG84toECEF(arPoints.get(i).getLocation());
            float[] pointInENU = LocationHelper.ECEFtoENU(currentLocation, currentLocationInECEF, pointInECEF);

            float[] cameraCoordinateVector = new float[4];
            Matrix.multiplyMV(cameraCoordinateVector, 0, rotatedProjectionMatrix, 0, pointInENU, 0);

            // cameraCoordinateVector[2] is z, that always less than 0 to display on right position
            // if z > 0, the point will display on the opposite
            if (cameraCoordinateVector[2] < 0) {
                float x = (0.5f + cameraCoordinateVector[0] / cameraCoordinateVector[3]) * canvas.getWidth();
                float y = (0.5f - cameraCoordinateVector[1] / cameraCoordinateVector[3]) * canvas.getHeight();

//                canvas.drawCircle(x, y, radius, paint);
//                canvas.drawText(arPoints.get(i).getName(), x - (30 * arPoints.get(i).getName().length() / 2), y - 80, paint);

                canvas.drawBitmap(blip, x, y, mPaint); //radar blip

            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
//        Log.d("Touch---", x + "-" + y);

        for (int i = 0; i < arPoints.size(); i++) {
            float[] currentLocationInECEF = LocationHelper.WSG84toECEF(currentLocation);
            float[] pointInECEF = LocationHelper.WSG84toECEF(arPoints.get(i).getLocation());
            float[] pointInENU = LocationHelper.ECEFtoENU(currentLocation, currentLocationInECEF, pointInECEF);

            float[] cameraCoordinateVector = new float[4];
            Matrix.multiplyMV(cameraCoordinateVector, 0, rotatedProjectionMatrix, 0, pointInENU, 0);

            // cameraCoordinateVector[2] is z, that always less than 0 to display on right position
            // if z > 0, the point will display on the opposite
            if (cameraCoordinateVector[2] < 0) {
                float x2 = (0.5f + cameraCoordinateVector[0] / cameraCoordinateVector[3]) * mCanVas.getWidth();
                float y2 = (0.5f - cameraCoordinateVector[1] / cameraCoordinateVector[3]) * mCanVas.getHeight();
                if (x >= x2 && x < (x2 + 500)
                        && y >= y2 && y < (y2 + 250)) {
                    Log.d("Item---", arPoints.get(i).getName());
                    Toast.makeText(getContext(), arPoints.get(i).getName(), Toast.LENGTH_LONG).show();
                }

            }
        }

        return super.onTouchEvent(event);
    }
}
