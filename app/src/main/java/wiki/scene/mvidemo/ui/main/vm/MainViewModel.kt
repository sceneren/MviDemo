package wiki.scene.mvidemo.ui.main.vm

import com.airbnb.mvrx.MavericksState
import wiki.scene.base.MvRxViewModel
import wiki.scene.base.tab.MainTabEntity
import wiki.scene.mvidemo.R

data class MainState(
    val tabs: List<MainTabEntity> = arrayListOf(
        MainTabEntity(
            "Tab1",
            R.drawable.tab_home_select,
            R.drawable.tab_home_unselect
        ), MainTabEntity(
            "Tab2",
            R.drawable.tab_speech_select,
            R.drawable.tab_speech_unselect
        ), MainTabEntity(
            "Tab3",
            R.drawable.tab_contact_select,
            R.drawable.tab_contact_unselect
        ), MainTabEntity(
            "Tab4",
            R.drawable.tab_more_select,
            R.drawable.tab_more_unselect
        )
    )
) :
    MavericksState

class MainViewModel(initialState: MainState) : MvRxViewModel<MainState>(initialState)