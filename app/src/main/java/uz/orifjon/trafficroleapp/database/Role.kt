package uz.orifjon.trafficroleapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "role_table")
data class Role(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "role_name")
    var roleName:String,
    @ColumnInfo(name = "role_description")
    var roleDescription:String,
    @ColumnInfo(name = "type_role")
    var typeRole:String,
    @ColumnInfo(name = "is_liked")
    var isLiked:Int,
    var bitmap:ByteArray
)