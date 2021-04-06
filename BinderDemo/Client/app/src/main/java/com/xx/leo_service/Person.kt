package com.xx.leo_service

import android.os.Parcel
import android.os.Parcelable

//如果有自定义的bean类，其包名需要与aidl的一致，并且aidl中需要添加一个aidl文件专门给该bean类，文件名也要与bean类一致
data class Person(var name:String?,var grade:Int):Parcelable {
    constructor(parcel: Parcel) : this(
        name = parcel.readString(),
        grade = parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(grade)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}