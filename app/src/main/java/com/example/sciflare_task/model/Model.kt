package com.example.sciflare_task.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull


@Entity(tableName = "details", indices = [Index(value = ["_id"], unique = true)])
class Model {
    @PrimaryKey
    @SerializedName("_id")
    @ColumnInfo(name = "_id")
    var id: String = ""

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    var mobile: String? = null

    @SerializedName("gender")
    @ColumnInfo(name = "gender")
    var gender: String? = null

    @SerializedName("email")
    @ColumnInfo(name = "email")
    var email: String? = null

}

