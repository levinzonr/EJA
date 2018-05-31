package cz.levinzonr.ezpad.domain.models

import android.os.Parcel
import android.os.Parcelable

data class Notebook(
        var id : Long? = null,
        var name: String,
        var notesCount: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeInt(notesCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Notebook> {
        override fun createFromParcel(parcel: Parcel): Notebook {
            return Notebook(parcel)
        }

        override fun newArray(size: Int): Array<Notebook?> {
            return arrayOfNulls(size)
        }
    }
}