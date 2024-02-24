package infrastructure.navigation

import moe.tlaster.precompose.navigation.transition.NavTransition

fun myNavTransitions(): NavTransition {
    return NavTransition(
        createTransition = NavTransition().createTransition,
        destroyTransition = NavTransition().destroyTransition,
        pauseTransition = NavTransition().pauseTransition,
        resumeTransition = NavTransition().resumeTransition
    )
}