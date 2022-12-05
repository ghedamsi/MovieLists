package com.ghedamsisabri.movies_lists.presentation.motionAnimation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

public object MotionConstants {
    public const val DefaultMotionDuration: Int = 300
    public const val DefaultFadeInDuration: Int = 150
    public const val DefaultFadeOutDuration: Int = 75
    public val DefaultSlideDistance: Dp = 30.dp

    @Deprecated("Don't use this constant!", level = DeprecationLevel.ERROR)
    public const val motionDurationShort1: Int = DefaultFadeOutDuration
    @Deprecated("Don't use this constant!", level = DeprecationLevel.ERROR)
    public const val motionDurationShort2: Int = DefaultFadeInDuration
    @Deprecated("Don't use this constant!", level = DeprecationLevel.ERROR)
    public const val motionDurationMedium1: Int = 200
    @Deprecated("Don't use this constant!", level = DeprecationLevel.ERROR)
    public const val motionDurationMedium2: Int = 250
    @Deprecated("Don't use this constant!", level = DeprecationLevel.ERROR)
    public const val motionDurationLong1: Int = DefaultMotionDuration
    @Deprecated("Don't use this constant!", level = DeprecationLevel.ERROR)
    public const val motionDurationLong2: Int = 350
}
