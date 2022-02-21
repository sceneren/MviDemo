package wiki.scene.mvidemo.ui.tab4.vm

import com.airbnb.mvrx.MavericksState
import wiki.scene.base.MvRxViewModel

data class Tab4Status(val scrollDistance: Int = 0) : MavericksState

class Tab4ViewModel(initialState: Tab4Status) : MvRxViewModel<Tab4Status>(initialState)