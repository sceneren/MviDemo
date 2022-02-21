package wiki.scene.mvidemo.ui.main.vm

import com.airbnb.mvrx.MavericksState
import wiki.scene.base.MvRxViewModel

data class MainState(val tabs: List<String> = arrayListOf("Tab1", "Tab2", "Tab3", "Tab4")) :
    MavericksState

class MainViewModel(initialState: MainState) : MvRxViewModel<MainState>(initialState)