package com.ghedamsisabri.sharedelements

import androidx.compose.runtime.*

/**
 * When [visible] becomes false, if transition is running, delay the exit of the content until
 * transition finishes. Note that you may need to call [SharedElementsRootScope.prepareTransition]
 * before [visible] becomes false to start transition immediately.
 */
private enum class DelayExitState {
    Invisible, Visible, ExitDelayed
}
@Composable
fun SharedElementsRootScope.DelayExit(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    var state by remember { mutableStateOf(DelayExitState.Invisible) }

    when (state) {
        DelayExitState.Invisible -> {
            if (visible) state = DelayExitState.Visible
        }
        DelayExitState.Visible -> {
            if (!visible) {
                state = if (isRunningTransition) DelayExitState.ExitDelayed else DelayExitState.Invisible
            }
        }
        DelayExitState.ExitDelayed -> {
            if (!isRunningTransition) state = DelayExitState.Invisible
        }
    }

    if (state != DelayExitState.Invisible) content()
}

