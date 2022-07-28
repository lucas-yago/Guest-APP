package com.lucasyago.guest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lucasyago.guest.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save){
            Toast.makeText(this,"clicou em salvar!", Toast.LENGTH_SHORT).show()
        }
    }
}