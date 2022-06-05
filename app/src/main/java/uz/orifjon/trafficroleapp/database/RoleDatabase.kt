package uz.orifjon.trafficroleapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Role::class], version = 1, exportSchema = false)
abstract class RoleDatabase : RoomDatabase() {

    abstract fun roleDao(): RoleDao

    companion object {
        @Volatile
        private var INSTANCE: RoleDatabase? = null

        fun getDatabase(context: Context): RoleDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoleDatabase::class.java,
                    "role_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}