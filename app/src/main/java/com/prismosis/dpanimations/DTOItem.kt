package com.prismosis.dpanimations

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Ehsan Saddique on 2020-03-18
 */
data class DTOItem(val title: String, val price: String,
                   val image: Int, val backgroudColor: Int, val page: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(price)
        parcel.writeInt(image)
        parcel.writeInt(backgroudColor)
        parcel.writeInt(page)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DTOItem> {
        override fun createFromParcel(parcel: Parcel): DTOItem {
            return DTOItem(parcel)
        }

        override fun newArray(size: Int): Array<DTOItem?> {
            return arrayOfNulls(size)
        }
    }
}