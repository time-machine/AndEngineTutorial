package com.PerleDevelopment.AndEngine.tutorial.helper;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class AccelerometerHelper extends Activity
  implements SensorEventListener {
  private final SensorManager mSensorManager;
  private Sensor mAccelerometer;

  public static float TILT;

  public AccelerometerHelper(Activity activity) {
    mSensorManager =
      (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);

    if (mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
      mAccelerometer =
        mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);

      mSensorManager.registerListener(
        this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    TILT = event.values[0];
  }
}
