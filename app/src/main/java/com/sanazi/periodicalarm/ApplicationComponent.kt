package com.sanazi.periodicalarm

import com.sanazi.dependencies.AppDependenciesProvider
import dagger.Component

@Component(
    dependencies = [
        //Provider::class
    ]
)
interface ApplicationComponent: AppDependenciesProvider {

    companion object{
        fun create(): ApplicationComponent{
//            val provider = ProviderObject
            return DaggerApplicationComponent.factory().create(
                //providerObject
            )
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            //provider:Provider
        ): ApplicationComponent
    }
}