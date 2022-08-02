package com.lucasyago.guest.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasyago.guest.activity.GuestFormActivity
import com.lucasyago.guest.constants.DataBaseConstants
import com.lucasyago.guest.databinding.FragmentPresentBinding
import com.lucasyago.guest.view.adapter.GuestsAdapter
import com.lucasyago.guest.view.listener.OnGuestListener
import com.lucasyago.guest.viewmodel.PresentViewModel

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private val binding get() = _binding!!
    private val adapter = GuestsAdapter()
    private lateinit var viewModel: PresentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this)[PresentViewModel::class.java]
        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        //layout
        binding.recyclerPresentGuests.layoutManager = LinearLayoutManager(context)
        //adapter
        binding.recyclerPresentGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getPresence()
            }
        }

        adapter.attachListener(listener)

        observer()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPresence()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observer() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updatedGuests(it)
        }
    }
}