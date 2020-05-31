package com.nguyen.tekotest.ui.view.splash

import android.os.Bundle
import android.os.Handler
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.nguyen.tekotest.R

class SplashFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        Handler().postDelayed({
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_listProductFragment)
        }, 1000)
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}