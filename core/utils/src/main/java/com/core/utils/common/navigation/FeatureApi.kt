package com.core.utils.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureApi {

    fun registerGraph(navHostController: NavHostController, navGraphBuilder: NavGraphBuilder)

}