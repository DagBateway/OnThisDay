package com.albertocamillo.onthisday.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.albertocamillo.onthisday.R

/**
 * Composable utility to display a loading animation using a Lottie animation file.
 *
 * This animation plays infinitely and is meant to provide visual feedback while content is loading.
 * It references a Lottie JSON animation stored in the project's raw resources as `loading_zep.json`.
 *
 * @param modifier Modifier used to size and position the animation.
 */
@Composable
fun LoadingLottieAnimation(modifier: Modifier) {

    // Load the Lottie animation from the raw resource folder
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_zep))

    // Play the animation in a loop
    LottieAnimation(
        composition = composition,
        modifier = modifier,
        iterations = LottieConstants.IterateForever,
    )
}
