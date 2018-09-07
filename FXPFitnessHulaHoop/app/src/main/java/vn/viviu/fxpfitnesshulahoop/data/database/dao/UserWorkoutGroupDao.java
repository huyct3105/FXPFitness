package vn.viviu.fxpfitnesshulahoop.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import vn.viviu.fxpfitnesshulahoop.data.database.entity.UserWorkoutGroup;

@Dao
public interface UserWorkoutGroupDao {
    @Insert
    void insertUserWorkoutGroup(UserWorkoutGroup... userWorkoutGroups);

    @Query("Select currentDate from UserWorkoutGroup where UserWorkoutGroup.idUser = :idUser and UserWorkoutGroup.idWorkoutGroup = :idUWG")
    int getCurrentDate(int idUser, int idUWG);

    @Query("update UserWorkoutGroup Set currentDate = 0 where idUser = :idUser and idWorkoutGroup = :idWOG")
    void UpdateWorkoutGroupReset(int idUser, int idWOG);

    @Query("delete from UserWorkoutGroup where idUser = :idUser and idWorkoutGroup = :idWOG")
    void UpdateWorkoutGroupRestore(int idUser, int idWOG);

    @Query("update UserWorkoutGroup Set currentDate = :date where idUser = :idUser and idWorkoutGroup = :idWOG")
    int updateWorkoutGroupCurrentDate(int idUser, int idWOG,int date);
}
