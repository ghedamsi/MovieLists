package com.ghedamsisabri.movies_lists.presentation.motionAnimation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

/**
 * Returns the provided [Dp] as an [Int] value by the [LocalDensity].
 *
 * @param slideDistance Value to the slide distance dimension, 30dp by default.
 */
@Composable
public fun rememberSlideDistance(
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
): Int {
    val density = LocalDensity.current
    return remember(density, slideDistance) {
        with(density) { slideDistance.roundToPx() }
    }
}

private const val ProgressThreshold = 0.35f

private val Int.ForOutgoing: Int
    get() = (this * ProgressThreshold).toInt()

private val Int.ForIncoming: Int
    get() = this - this.ForOutgoing

/**
 * [materialSharedAxisX] allows to switch a layout with shared X-axis transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of transition.
 * @param durationMillis the duration of transition.
 */
@Deprecated(
    message = "Use materialSharedAxisX() without density using rememberSlideDistance().",
)
@ExperimentalAnimationApi
public fun materialSharedAxisX(
    forward: Boolean,
    density: Density,
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ContentTransform = materialSharedAxisX(
    forward = forward,
    slideDistance = with(density) { slideDistance.roundToPx() },
    durationMillis = durationMillis,
)

/**
 * [materialSharedAxisX] allows to switch a layout with shared X-axis transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of transition.
 * @param durationMillis the duration of transition.
 */
@OptIn(ExperimentalAnimationApi::class)
public fun materialSharedAxisX(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ContentTransform = materialSharedAxisXIn(
    forward = forward,
    slideDistance = slideDistance,
    durationMillis = durationMillis
) with materialSharedAxisXOut(
    forward = forward,
    slideDistance = slideDistance,
    durationMillis = durationMillis
)

/**
 * [materialSharedAxisXIn] allows to switch a layout with shared X-axis enter transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@Deprecated(
    message = "Use materialSharedAxisXIn() without density using rememberSlideDistance().",
)
public fun materialSharedAxisXIn(
    forward: Boolean,
    density: Density,
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): EnterTransition = materialSharedAxisXIn(
    forward = forward,
    slideDistance = with(density) { slideDistance.roundToPx() },
    durationMillis = durationMillis,
)

/**
 * [materialSharedAxisXIn] allows to switch a layout with shared X-axis enter transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
public fun materialSharedAxisXIn(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): EnterTransition = slideInHorizontally(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    initialOffsetX = {
        if (forward) slideDistance else -slideDistance
    }
) + fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis.ForIncoming,
        delayMillis = durationMillis.ForOutgoing,
        easing = LinearOutSlowInEasing
    )
)

/**
 * [materialSharedAxisXOut] allows to switch a layout with shared X-axis exit transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
@Deprecated(
    message = "Use materialSharedAxisXOut() without density using rememberSlideDistance().",
)
public fun materialSharedAxisXOut(
    forward: Boolean,
    density: Density,
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ExitTransition = materialSharedAxisXOut(
    forward = forward,
    slideDistance = with(density) { slideDistance.roundToPx() },
    durationMillis = durationMillis,
)

/**
 * [materialSharedAxisXOut] allows to switch a layout with shared X-axis exit transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
public fun materialSharedAxisXOut(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ExitTransition = slideOutHorizontally(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    targetOffsetX = {
        if (forward) -slideDistance else slideDistance
    }
) + fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis.ForOutgoing,
        delayMillis = 0,
        easing = FastOutLinearInEasing
    )
)

/**
 * [materialSharedAxisY] allows to switch a layout with shared Y-axis transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of transition.
 * @param durationMillis the duration of transition.
 */
@Deprecated(
    message = "Use materialSharedAxisY() without density using rememberSlideDistance().",
)
@ExperimentalAnimationApi
public fun materialSharedAxisY(
    forward: Boolean,
    density: Density,
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ContentTransform = materialSharedAxisY(
    forward = forward,
    slideDistance = with(density) { slideDistance.roundToPx() },
    durationMillis = durationMillis,
)

/**
 * [materialSharedAxisY] allows to switch a layout with shared Y-axis transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of transition.
 * @param durationMillis the duration of transition.
 */
@ExperimentalAnimationApi
public fun materialSharedAxisY(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ContentTransform = materialSharedAxisYIn(
    forward = forward,
    slideDistance = slideDistance,
    durationMillis = durationMillis
) with materialSharedAxisYOut(
    forward = forward,
    slideDistance = slideDistance,
    durationMillis = durationMillis
)

/**
 * [materialSharedAxisYIn] allows to switch a layout with shared Y-axis enter transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
@Deprecated(
    message = "Use materialSharedAxisYIn() without density using rememberSlideDistance().",
)
public fun materialSharedAxisYIn(
    forward: Boolean,
    density: Density,
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): EnterTransition = materialSharedAxisYIn(
    forward = forward,
    slideDistance = with(density) { slideDistance.roundToPx() },
    durationMillis = durationMillis,
)

/**
 * [materialSharedAxisYIn] allows to switch a layout with shared Y-axis enter transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of the enter transition.
 * @param durationMillis the duration of the enter transition.
 */
public fun materialSharedAxisYIn(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): EnterTransition = slideInVertically(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    initialOffsetY = {
        if (forward) slideDistance else -slideDistance
    }
) + fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis.ForIncoming,
        delayMillis = durationMillis.ForOutgoing,
        easing = LinearOutSlowInEasing
    )
)

/**
 * [materialSharedAxisYOut] allows to switch a layout with shared Y-axis exit transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
@Deprecated(
    message = "Use materialSharedAxisYOut() without density using rememberSlideDistance().",
)
public fun materialSharedAxisYOut(
    forward: Boolean,
    density: Density,
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ExitTransition = materialSharedAxisYOut(
    forward = forward,
    slideDistance = with(density) { slideDistance.roundToPx() },
    durationMillis = durationMillis,
)

/**
 * [materialSharedAxisYOut] allows to switch a layout with shared Y-axis exit transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param slideDistance the slide distance of the exit transition.
 * @param durationMillis the duration of the exit transition.
 */
public fun materialSharedAxisYOut(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ExitTransition = slideOutVertically(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    targetOffsetY = {
        if (forward) -slideDistance else slideDistance
    }
) + fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis.ForOutgoing,
        delayMillis = 0,
        easing = FastOutLinearInEasing
    )
)

/**
 * [materialSharedAxisZ] allows to switch a layout with shared Z-axis transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param durationMillis the duration of transition.
 */
@ExperimentalAnimationApi
public fun materialSharedAxisZ(
    forward: Boolean,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ContentTransform = materialSharedAxisZIn(
    forward = forward,
    durationMillis = durationMillis
) with materialSharedAxisZOut(
    forward = forward,
    durationMillis = durationMillis
)

/**
 * [materialSharedAxisZIn] allows to switch a layout with shared Z-axis enter transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param durationMillis the duration of the enter transition.
 */
@ExperimentalAnimationApi
public fun materialSharedAxisZIn(
    forward: Boolean,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): EnterTransition = fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis.ForIncoming,
        delayMillis = durationMillis.ForOutgoing,
        easing = LinearOutSlowInEasing
    )
) + scaleIn(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    initialScale = if (forward) 0.8f else 1.1f
)

/**
 * [materialSharedAxisZOut] allows to switch a layout with shared Z-axis exit transition.
 *
 * @param forward whether the direction of the animation is forward.
 * @param durationMillis the duration of the exit transition.
 */
@ExperimentalAnimationApi
public fun materialSharedAxisZOut(
    forward: Boolean,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ExitTransition = fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis.ForOutgoing,
        delayMillis = 0,
        easing = FastOutLinearInEasing
    )
) + scaleOut(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    targetScale = if (forward) 1.1f else 0.8f
)