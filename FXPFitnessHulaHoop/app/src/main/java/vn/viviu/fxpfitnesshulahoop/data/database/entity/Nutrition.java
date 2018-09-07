package vn.viviu.fxpfitnesshulahoop.data.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by PHAMHOAN on 3/5/2018.
 */
@Entity
public class Nutrition implements Serializable{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idNutrition")
    private int idNutrition;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "healthyChoices")
    private String healthyChoices;

    public Nutrition(String category, String description, String healthyChoices) {
        this.category = category;
        this.description = description;
        this.healthyChoices = healthyChoices;
    }

    public int getIdNutrition() {
        return idNutrition;
    }

    public void setIdNutrition(int idNutrition) {
        this.idNutrition = idNutrition;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHealthyChoices() {
        return healthyChoices;
    }

    public void setHealthyChoices(String healthyChoices) {
        this.healthyChoices = healthyChoices;
    }
}
