package vn.viviu.fxpfitnesshulahoop.data.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by PHAMHOAN on 3/5/2018.
 */
@Entity
public class Exercises implements Serializable, Parcelable {
    protected Exercises(Parcel in) {
        idExcercises = in.readInt();
        exerciseName = in.readString();
        exerciseDescription = in.readString();
        muscle = in.readString();
        link = in.readString();
        exerciseNotes = in.readString();
        block = in.readString();
        focus = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idExcercises);
        dest.writeString(exerciseName);
        dest.writeString(exerciseDescription);
        dest.writeString(muscle);
        dest.writeString(link);
        dest.writeString(exerciseNotes);
        dest.writeString(block);
        dest.writeString(focus);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Exercises> CREATOR = new Creator<Exercises>() {
        @Override
        public Exercises createFromParcel(Parcel in) {
            return new Exercises(in);
        }

        @Override
        public Exercises[] newArray(int size) {
            return new Exercises[size];
        }
    };

    public int getIdExcercises() {
        return idExcercises;
    }

    public void setIdExcercises(int idExcercises) {
        this.idExcercises = idExcercises;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idExcercises")
    private int idExcercises;
    @ColumnInfo(name = "exerciseName")
    private String exerciseName;
    @ColumnInfo(name = "exerciseDescription")
    private String exerciseDescription;
    @ColumnInfo(name = "muscle")
    private String muscle;
    @ColumnInfo(name = "link")
    private String link;
    @ColumnInfo(name = "exerciseNotes")
    private String exerciseNotes;
    @ColumnInfo(name = "block")
    private String block;
    @ColumnInfo(name = "focus")
    private String focus;
    @ColumnInfo(name = "image")
    private String image;

    public Exercises(String exerciseName, String exerciseDescription, int idExercises, String muscle, String link, String exerciseNotes, String block, String focus, String image) {
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.idExcercises = idExercises;
        this.muscle = muscle;
        this.link = link;
        this.exerciseNotes = exerciseNotes;
        this.block = block;
        this.focus = focus;
        this.image = image;
    }

    public Exercises() {
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public int getIdExercises() {
        return idExcercises;
    }

    public void setIdExercises(int idExercises) {
        this.idExcercises = idExercises;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getExerciseNotes() {
        return exerciseNotes;
    }

    public void setExerciseNotes(String exerciseNotes) {
        this.exerciseNotes = exerciseNotes;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
