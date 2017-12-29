package game_of_cards.hiteshsahu.com.blackjack

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.view.animation.AccelerateInterpolator
import com.plattysoft.leonids.ParticleSystem
import kotlinx.android.synthetic.main.activity_card.*
import java.util.*

class CardActivity : AppCompatActivity(), View.OnTouchListener {
    private val PARTICLE_PER_SECOND = 3
    private var dX: Float = 0.toFloat()
    private var dY: Float = 0.toFloat()
    internal var r = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_card)
        root_container.setOnTouchListener(this)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_card, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        if (null != view && null != event) {
            val X = event.rawX.toInt()
            val Y = event.rawY.toInt()
            when (event.getAction() and MotionEvent.ACTION_MASK) {

                MotionEvent.ACTION_DOWN -> {

                    dX = view.getX() - event.rawX
                    dY = view.getY() - event.rawY
                }

                MotionEvent.ACTION_UP -> {
                }

                MotionEvent.ACTION_POINTER_DOWN -> {
                }

                MotionEvent.ACTION_POINTER_UP -> {
                }

                MotionEvent.ACTION_MOVE -> {

                    // img.setX(X);
                    // img.setY(Y);

                    touchToWIn.animate().x(event.rawX).y(event.rawY).setDuration(0).start()

                    val i = r.nextInt(3)

                    if (i == 0) {

                        ParticleSystem(this@CardActivity, 1, R.drawable.card_1, 5000)
                                .setAcceleration(-0.002f, 270)
                                .setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
                                .setRotationSpeed(144f)
                                .setFadeOut(200, AccelerateInterpolator())
                                .emitWithGravity(findViewById(R.id.touchToWIn),
                                        Gravity.TOP,
                                        PARTICLE_PER_SECOND,
                                        1000)
                    } else if (i == 1) {
                        ParticleSystem(this@CardActivity, 1, R.drawable.card_2, 5000)
                                .setAcceleration(-0.002f, 270)
                                .setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
                                .setRotationSpeed(144f)
                                .setFadeOut(200, AccelerateInterpolator())
                                .emitWithGravity(findViewById(R.id.touchToWIn),
                                        Gravity.TOP, PARTICLE_PER_SECOND,
                                        1000)
                    } else if (i == 2) {
                        ParticleSystem(this@CardActivity, 1, R.drawable.card_3, 5000)
                                .setAcceleration(-0.002f, 270)
                                .setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
                                .setRotationSpeed(144f)
                                .setFadeOut(200, AccelerateInterpolator())
                                .emitWithGravity(findViewById(R.id.touchToWIn),
                                        Gravity.TOP, PARTICLE_PER_SECOND,
                                        1000)
                    }
                }
            }

            root_container.invalidate()
        }

        return true
    }
}
