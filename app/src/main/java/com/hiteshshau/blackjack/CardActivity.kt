package com.hiteshshau.blackjack

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.view.animation.AccelerateInterpolator
import com.hiteshsahu.blackjack.R
import com.plattysoft.leonids.ParticleSystem
import kotlinx.android.synthetic.main.activity_card.*
import java.util.*

class CardActivity : AppCompatActivity(), View.OnTouchListener {
    private val PARTICLE_PER_SECOND = 3
    private var dX: Float = 0.toFloat()
    private var dY: Float = 0.toFloat()
    internal var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_card)
        root_container.setOnTouchListener(this)

        //animate entry animation
        doCircularReveal();

        //Welcome user
        Snackbar.make(root_container, "Welcome To BlackJack !!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        // show code
        showCode.setOnClickListener { view ->
            startActivity(Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/hiteshsahu/Black-Jack")))
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

                MotionEvent.ACTION_MOVE -> {

                    touchToWIn.animate().x(event.rawX).y(event.rawY).setDuration(0).start()

                    val cardIndex = random.nextInt(3)

                    if (cardIndex == 0) {

                        ParticleSystem(this@CardActivity, 1, R.drawable.card_1, 5000)
                                .setAcceleration(-0.002f, 270)
                                .setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
                                .setRotationSpeed(144f)
                                .setFadeOut(200, AccelerateInterpolator())
                                .emitWithGravity(findViewById(R.id.touchToWIn),
                                        Gravity.TOP,
                                        PARTICLE_PER_SECOND,
                                        1000)
                    } else if (cardIndex == 1) {
                        ParticleSystem(this@CardActivity, 1, R.drawable.card_2, 5000)
                                .setAcceleration(-0.002f, 270)
                                .setSpeedModuleAndAngleRange(0f, 1.0f, 180, 360)
                                .setRotationSpeed(144f)
                                .setFadeOut(200, AccelerateInterpolator())
                                .emitWithGravity(findViewById(R.id.touchToWIn),
                                        Gravity.TOP, PARTICLE_PER_SECOND,
                                        1000)
                    } else if (cardIndex == 2) {
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

    override fun onBackPressed() {
        animateExitScreen()
    }

    private fun animateExitScreen() {

        //Circular exit Animation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val anim = exitReveal(root_container)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            anim!!.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    supportFinishAfterTransition()
                }
            })
            anim.start()
        } else {
            finish()
        }
    }

    private fun doCircularReveal() {

        root_container.setVisibility(View.INVISIBLE)

        window.decorView.findViewById<View>(android.R.id.content)
                .setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))

        val viewTreeObserver = root_container.getViewTreeObserver()
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {

                    circularRevealView(root_container)
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        root_container.getViewTreeObserver().removeGlobalOnLayoutListener(this)
                    } else {
                        root_container.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                    }
                }
            })
        }
    }

    private fun circularRevealView(revealLayout: View) {

        val cx = revealLayout.width / 2
        val cy = revealLayout.height / 2

        val finalRadius = Math.max(revealLayout.width, revealLayout.height).toFloat()

        // create the animator for this view (the start radius is zero)
        var circularReveal: Animator? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circularReveal = ViewAnimationUtils.createCircularReveal(revealLayout, cx, cy, 0f, finalRadius)

            circularReveal!!.duration = 1000

            // make the view visible and start the animation
            revealLayout.visibility = View.VISIBLE

            circularReveal.start()
        } else {
            revealLayout.visibility = View.VISIBLE
        }
    }

    fun exitReveal(myView: View): Animator? {
        // previously visible view

        // get the center for the clipping circle
        val cx = myView.measuredWidth / 2
        val cy = myView.measuredHeight / 2


        // get the initial radius for the clipping circle
        val initialRadius = myView.width / 2

        // create the animation (the final radius is zero)
        var anim: Animator? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius.toFloat(), 0f)

            // make the view invisible when the animation is done
            anim!!.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    myView.visibility = View.INVISIBLE
                }
            })
        }
        // start the animation
        return anim
    }
}
