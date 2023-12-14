package com.example.echopilah.ui.splash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.echopilah.R
import com.example.echopilah.databinding.ActivitySplashBinding
import com.example.echopilah.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        setUpAction()
    }

    private fun setUpAction() {
        binding.btnJoin.setOnClickListener{
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun playAnimation() {

        val bg_bawah = ObjectAnimator.ofFloat(binding.rumput, View.ALPHA, 1f).setDuration(1000)
        val bg_wanitamerah = ObjectAnimator.ofFloat(binding.wanitamerah, View.ALPHA, 1f).setDuration(1000)
        val bg_wanitaorange = ObjectAnimator.ofFloat(binding.wanitaorange, View.ALPHA, 1f).setDuration(1000)
        val bg_cowok = ObjectAnimator.ofFloat(binding.priaungu, View.ALPHA, 1f).setDuration(1000)
        val bg_logo = ObjectAnimator.ofFloat(binding.logo, View.ALPHA, 1f).setDuration(1000)
        val bg_desc = ObjectAnimator.ofFloat(binding.desc, View.ALPHA, 1f).setDuration(1000)
        val btn_join = ObjectAnimator.ofFloat(binding.btnJoin, View.ALPHA, 1f).setDuration(1000)


        AnimatorSet().apply {
            playSequentially(bg_bawah, bg_wanitamerah, bg_wanitaorange, bg_cowok, bg_logo, bg_desc, btn_join)
            start()
        }
    }
}