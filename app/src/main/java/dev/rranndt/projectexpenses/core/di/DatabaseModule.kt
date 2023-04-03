package dev.rranndt.projectexpenses.core.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rranndt.projectexpenses.data.local.db.ExpenseDao
import dev.rranndt.projectexpenses.data.local.db.ExpenseDatabase
import dev.rranndt.projectexpenses.presentation.ui.utils.Constant.DB_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideExpenseDatabase(
        app: Application,
    ): ExpenseDatabase {
        return Room.databaseBuilder(
            app,
            ExpenseDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideExpenseDao(
        db: ExpenseDatabase,
    ): ExpenseDao {
        return db.expenseDao()
    }
}