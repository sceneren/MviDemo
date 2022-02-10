package wiki.scene.mvidemo.viewmodel.status

import com.airbnb.mvrx.MavericksState

data class LoginState(
    val userInfo: String = ""
) : MavericksState