package com.lucasyago.guest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lucasyago.guest.R
import com.lucasyago.guest.databinding.ActivityGuestFormBinding
import com.lucasyago.guest.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)


        binding.buttonSave.setOnClickListener(this)
        binding.radioPresence.isChecked = true

    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save){
            Toast.makeText(this,"clicou em salvar!", Toast.LENGTH_SHORT).show()
        }
    }
}