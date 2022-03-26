package com.example.englishdictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.englishdictionary.databinding.ActivityMainBinding
import com.example.englishdictionary.fragmentNavigation.INavigation
import com.example.englishdictionary.fragmentNavigation.MainRouter
import com.example.englishdictionary.view.wordsListFragment.WordsListFragment

class MainActivity : AppCompatActivity(), INavigation {

    //region Properties
    private lateinit var binding: ActivityMainBinding
    private var mainRouter: MainRouter? = null
    //endregion

    //region ActivityMethods
    override fun onResume() {
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mainRouter = MainRouter(supportFragmentManager, binding.mainContainer.id)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val isLandscape = resources.getBoolean(R.bool.isLandscape)
            if (!isLandscape) {
                mainRouter?.navigateToFragment(WordsListFragment.newInstance())
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mainRouter = null
    }
    //endregion

    //region Navigation
    override fun navigateToFragment(fragment: Fragment) {
        mainRouter?.navigateToFragment(fragment)
    }
    //endregion
}