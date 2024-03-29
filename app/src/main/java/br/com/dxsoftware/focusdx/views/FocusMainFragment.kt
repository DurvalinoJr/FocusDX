package br.com.dxsoftware.focusdx.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.dxsoftware.focusdx.R

class FocusMainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_focus_main, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FocusMainFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
