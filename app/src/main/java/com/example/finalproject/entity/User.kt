package com.example.finalproject.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey

data class User(
    var fname : String? = null,
    var lname : String? = null,
    var address: String? = null,
    var phone: String? = null,
    var user : String? = null,
    var password : String? = null

): Parcelable {
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        userId = parcel.readInt()
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fname)
        parcel.writeString(lname)
        parcel.writeString(address)
        parcel.writeString(phone)
        parcel.writeString(user)
        parcel.writeString(password)
        parcel.writeInt(userId)
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