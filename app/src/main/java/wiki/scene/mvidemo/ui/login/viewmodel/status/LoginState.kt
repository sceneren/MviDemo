package wiki.scene.mvidemo.ui.login.viewmodel.status

import com.airbnb.mvrx.MavericksState

data class LoginState(
    val userInfo: String = ""
) : MavericksState