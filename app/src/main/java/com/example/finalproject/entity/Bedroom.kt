package com.example.finalproject.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bedroom(
        var bedname: String? = null,
        var size: String? = null,
        var material: String? = null,
        var describe: String? = null,
        var cost: String? = null,
        var image: String? = null,
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var bedId: Int = 0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    ) {
        bedId = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(bedname)
        parcel.writeString(size)
        parcel.writeString(material)
        parcel.writeString(cost)
        parcel.writeString(describe)
        parcel.writeString(image)
        parcel.writeInt(bedId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
