package br.com.likeflix.ui

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import br.com.likeflix.BaseActivity
import br.com.likeflix.R
import br.com.likeflix.util.bind
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main_tabs.*


class HomeActivity : BaseActivity(
    R.layout.activity_main_tabs
) {

    private val viewPagerAdapter: ViewPager2 by bind(R.id.vpMovies)
    private val tabs: TabLayout by bind(R.id.tabMovieGenre)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        viewPagerAdapter.adapter = GenresPagerAdapter(this)
        TabLayoutMediator(tabs, viewPagerAdapter) { tab, position ->
            tab.text = (viewPagerAdapter.adapter as GenresPagerAdapter).getTabName(position)
        }.attach()
        vpMovies.isUserInputEnabled = false
    }


}