package com.n11.n11testcase.di

import com.n11.n11testcase.data.UserRepositoryImpl
import com.n11.n11testcase.domain.repository.UserRepository
import com.n11.n11testcase.domain.usecase.UserUseCase
import com.n11.n11testcase.domain.usecase.UserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule  {
    @Binds
    @Singleton
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun provideUserUseCase(userUseCaseImpl: UserUseCaseImpl) : UserUseCase
}