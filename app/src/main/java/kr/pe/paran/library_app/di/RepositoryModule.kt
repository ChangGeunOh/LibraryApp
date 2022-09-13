package kr.pe.paran.library_app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.pe.paran.library_app.repository.BookDataStore
import kr.pe.paran.library_app.repository.BookItemDataStore
import kr.pe.paran.library_app.repository.MemberDataStore
import kr.pe.paran.library_app.repository.remote.RemoteBookDataStore
import kr.pe.paran.library_app.repository.remote.RemoteBookItemDataStore
import kr.pe.paran.library_app.repository.remote.RemoteMemberDataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideBookDataSource(): BookDataStore {
        return RemoteBookDataStore()
    }

    @Provides
    @Singleton
    fun provideBookItemDataSource(): BookItemDataStore {
        return RemoteBookItemDataStore()
    }

    @Provides
    @Singleton
    fun provideMemberDataSource(): MemberDataStore {
        return RemoteMemberDataStore()
    }
}
