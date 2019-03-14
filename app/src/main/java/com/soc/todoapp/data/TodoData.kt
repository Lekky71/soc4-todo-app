package com.soc.todoapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity(tableName = "todos")
data class TodoData(@PrimaryKey(autoGenerate = true) var id: Long?,
                    @ColumnInfo(name = "title") var title: String,
                    @ColumnInfo(name = "content") var content: String,
                    var created_at: Long): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeValue(id)
        dest.writeString(title)
        dest.writeString(content)
        dest.writeLong(created_at)
    }

    override fun describeContents(): Int {
        return Parcelable.CONTENTS_FILE_DESCRIPTOR
    }

    constructor(): this(null, "", "", 0)

    companion object CREATOR : Parcelable.Creator<TodoData> {
        override fun createFromParcel(parcel: Parcel): TodoData {
            return TodoData(parcel)
        }

        override fun newArray(size: Int): Array<TodoData?> {
            return arrayOfNulls(size)
        }
    }

}