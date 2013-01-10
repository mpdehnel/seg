package com.vectorone;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.Camera.CameraInfo;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

public class CamActivity extends Activity implements SensorEventListener {
	private Camera mCamera;
	private CameraPreview mPreview;
	private SensorManager sensorManager;
	private Sensor sensor;
	private FrameLayout preview;
	private static final String TAG = "My";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_cam);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		// sensorManager.getOrientation(R, values)
		// sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		Log.d(TAG, "asd");
		// Create an instance of Camera
		mCamera = getCameraInstance();

		// Create our Preview view and set it as the content of our activity.
		mPreview = new CameraPreview(this, mCamera);
		preview = (FrameLayout) findViewById(R.id.camera_preview);
		drawPointOVerlay();
		preview.addView(mPreview);
	}

	private boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			// no camera on this device
			return false;
		}
	}

	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}

	private void drawPointOVerlay() {
		preview.setForeground(this.getResources().getDrawable(
				R.drawable.red_dot));
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {

	}
}