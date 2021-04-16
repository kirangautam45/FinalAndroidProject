package com.example.finalproject.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey

data class Order(var name: String? = null,
                 var material: String? = null,
                 var feature: String? = null,
                 var cost: String? = null,
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var orderId: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),

        ) {
        orderId = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(material)
        parcel.writeString(cost)
        parcel.writeString(feature)

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
