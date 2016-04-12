package com.example.androiddraganddrop;

import java.util.Random;

import com.plattysoft.leonids.ParticleSystem;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class MyGLActivity extends Activity {

	Random r = new Random();

	private GLSurfaceView glView; // use GLSurfaceView
	private ParticleSystem ps;
	// Call back when the activity is started, to initialize the view

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		glView = new GLSurfaceView(this); // Allocate a GLSurfaceView
		glView.setRenderer(new MyGLRenderer(this)); // Use a custom renderer
		this.setContentView(glView);

		// This activity sets to GLSurfaceView
	}

	// Call back when the activity is going into the background
	@Override
	protected void onPause() {
		super.onPause();
		glView.onPause();
	}

	// Call back after onPause()
	@Override
	protected void onResume() {
		super.onResume();
		glView.onResume();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction() & MotionEvent.ACTION_MASK) {

		case MotionEvent.ACTION_DOWN:

			int i = r.nextInt(3);

			if (i == 0) {

				// new ParticleSystem(MyGLActivity.this, 1, R.drawable.card_1,
				// 5000).setAcceleration(-0.002f, 270)
				// .setSpeedModuleAndAngleRange(0f, 1.0f, 180,
				// 360).setRotationSpeed(144)
				// .setFadeOut(200, new AccelerateInterpolator())
				// .emit((int) event.getRawX(), (int) event.getRawY(),
				// PARTICLE_PER_SECOND);
				//

				ps = new ParticleSystem(this, 100, R.drawable.card_1, 800);
				ps.setScaleRange(0.7f, 1.3f);
				ps.setSpeedRange(0.05f, 0.1f);
				ps.setRotationSpeedRange(90, 180);
				ps.setFadeOut(200, new AccelerateInterpolator());
				ps.emit((int) event.getX(), (int) event.getY(), 40);

			} else if (i == 1) {

				// new ParticleSystem(MyGLActivity.this, 1, R.drawable.card_2,
				// 5000).setAcceleration(-0.002f, 270)
				// .setSpeedModuleAndAngleRange(0f, 1.0f, 180,
				// 360).setRotationSpeed(144)
				// .setFadeOut(200, new AccelerateInterpolator())
				// .emit((int) event.getRawX(), (int) event.getRawY(),
				// PARTICLE_PER_SECOND);

				ps = new ParticleSystem(this, 100, R.drawable.card_2, 800);
				ps.setScaleRange(0.7f, 1.3f);
				ps.setSpeedRange(0.05f, 0.1f);
				ps.setRotationSpeedRange(90, 180);
				ps.setFadeOut(200, new AccelerateInterpolator());
				ps.emit((int) event.getX(), (int) event.getY(), 40);
			} else if (i == 2) {

				// new ParticleSystem(MyGLActivity.this, 1, R.drawable.card_3,
				// 5000).setAcceleration(-0.002f, 270)
				// .setSpeedModuleAndAngleRange(0f, 1.0f, 180,
				// 360).setRotationSpeed(144)
				// .setFadeOut(200, new AccelerateInterpolator())
				// .emit((int) event.getRawX(), (int) event.getRawY(),
				// PARTICLE_PER_SECOND);

				ps = new ParticleSystem(this, 100, R.drawable.card_3, 800);
				ps.setScaleRange(0.7f, 1.3f);
				ps.setSpeedRange(0.05f, 0.1f);
				ps.setRotationSpeedRange(90, 180);
				ps.setFadeOut(200, new AccelerateInterpolator());
				ps.emit((int) event.getX(), (int) event.getY(), 40);
			}

			break;

		case MotionEvent.ACTION_UP:

			ps.stopEmitting();

			break;

		case MotionEvent.ACTION_MOVE:

			ps.updateEmitPoint((int) event.getX(), (int) event.getY());

			break;
		}

		return true;
	}
}