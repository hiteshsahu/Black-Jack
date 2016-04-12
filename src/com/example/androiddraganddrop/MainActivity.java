package com.example.androiddraganddrop;

import java.util.Random;

import com.plattysoft.leonids.ParticleSystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnTouchListener {

	private static final int PARTICLE_PER_SECOND = 3;
	// TextView _view;
	ViewGroup _root;
	private int _xDelta;
	private int _yDelta;
	private ImageView img;
	private float dX;
	private float dY;

	Random r = new Random();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		_root = (ViewGroup) findViewById(R.id.container);

		// _view = new TextView(this);
		// _view.setText("TextView!!!!!!!!");

		// RelativeLayout.LayoutParams layoutParams = new
		// RelativeLayout.LayoutParams(150, 50);
		// layoutParams.leftMargin = 50;
		// layoutParams.topMargin = 50;
		// layoutParams.bottomMargin = -250;
		// layoutParams.rightMargin = -250;
		// _view.setLayoutParams(layoutParams);

		img = (ImageView) findViewById(R.id.imgView);

		_root.setOnTouchListener(this);
	}

	public boolean onTouch(View view, MotionEvent event) {
		final int X = (int) event.getRawX();
		final int Y = (int) event.getRawY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {

		case MotionEvent.ACTION_DOWN:

			dX = view.getX() - event.getRawX();
			dY = view.getY() - event.getRawY();

			// img.setX(X);
			// img.setY(Y);

			// FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams)
			// view.getLayoutParams();
			// _xDelta = X - lParams.leftMargin;
			// _yDelta = Y - lParams.topMargin;

			break;

		case MotionEvent.ACTION_UP:

			break;

		case MotionEvent.ACTION_POINTER_DOWN:

			break;

		case MotionEvent.ACTION_POINTER_UP:

			break;

		case MotionEvent.ACTION_MOVE:

			// img.setX(X);
			// img.setY(Y);

			img.animate().x(event.getRawX()).y(event.getRawY()).setDuration(0).start();

			int i = r.nextInt(3);

			if (i == 0) {

				new ParticleSystem(MainActivity.this, 1, R.drawable.card_1, 5000, R.id.container)
						.setAcceleration(-0.002f, 270).setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
						.setRotationSpeed(144).setFadeOut(200, new AccelerateInterpolator())
						.emitWithGravity(findViewById(R.id.imgView), Gravity.TOP, PARTICLE_PER_SECOND, 1000);
			} else if (i == 1) {
				new ParticleSystem(MainActivity.this, 1, R.drawable.card_2, 5000).setAcceleration(-0.002f, 270)
						.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360).setRotationSpeed(144)
						.setFadeOut(200, new AccelerateInterpolator())
						.emitWithGravity(findViewById(R.id.imgView), Gravity.TOP, PARTICLE_PER_SECOND, 1000);
			} else if (i == 2) {
				new ParticleSystem(MainActivity.this, 1, R.drawable.card_3, 5000).setAcceleration(-0.002f, 270)
						.setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360).setRotationSpeed(144)
						.setFadeOut(200, new AccelerateInterpolator())
						.emitWithGravity(findViewById(R.id.imgView), Gravity.TOP, PARTICLE_PER_SECOND, 1000);
			}

			// FrameLayout.LayoutParams layoutParams =
			// (FrameLayout.LayoutParams) img.getLayoutParams();
			// layoutParams.leftMargin = X - _xDelta;
			// layoutParams.topMargin = Y - _yDelta;
			// layoutParams.rightMargin = -250;
			// layoutParams.bottomMargin = -250;
			// img.setLayoutParams(layoutParams);

			break;
		}

		_root.invalidate();

		return true;
	}
}