package uz.orifjon.trafficroleapp.database

import androidx.room.*

@Dao
interface RoleDao {

    @Insert
    fun addRole(role: Role)

    @Update
    fun editRole(role: Role)

    @Query("SELECT * FROM role_table")
    fun listRole(): List<Role>

    @Query("SELECT * FROM role_table WHERE type_role = :type")
    fun listRoleByType(type: String): List<Role>

    @Delete
    fun deleteRole(role: Role)


}