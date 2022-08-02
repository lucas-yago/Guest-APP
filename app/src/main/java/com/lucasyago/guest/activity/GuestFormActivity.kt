package com.lucasyago.guest.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lucasyago.guest.R
import com.lucasyago.guest.constants.DataBaseConstants
import com.lucasyago.guest.databinding.ActivityGuestFormBinding
import com.lucasyago.guest.model.GuestModel
import com.lucasyago.guest.viewmodel.GuestFormViewModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[GuestFormViewModel::class.java]


        binding.buttonSave.setOnClickListener(this)
        binding.radioPresence.isChecked = true

        observe()

        loadData()
    }

    private fun observe() {
        viewModel.guest.observe(this) {
            binding.editName.setText(it.name)
            if (it.presence) {
                binding.radioPresence.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        }

        viewModel.saveGuest.observe(this) {
            if (it != ""){
                Toast.makeText(applicationContext,it,Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(DataBaseConstants.GUEST.ID)
            viewModel.get(guestId)
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            val name = binding.editName.text.toString()
            val presence = binding.radioPresence.isChecked

            val model = GuestModel(guestId, name, presence)
            viewModel.save(model)
        }
    }
}