package com.soc.todoapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoData(@PrimaryKey(autoGenerate = true) var id: Long?,
                    @ColumnInfo(name = "title") var title: String,
                    @ColumnInfo(name = "content") var content: String,
                    var created_at: Long) {
    constructor(): this(null, "", "", 0)

}