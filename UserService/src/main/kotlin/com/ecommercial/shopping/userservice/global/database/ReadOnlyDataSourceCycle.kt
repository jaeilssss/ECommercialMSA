package com.ecommercial.shopping.userservice.global.database

import lombok.Setter

@Setter
class ReadOnlyDataSourceCycle<T> {
    var readOnlyDataSourceLookupKeys : List<T> = emptyList()
    private var index: Int = 0

    fun getReadOnlyDataSourceLookupKey(): T {
        if (index + 1 >= readOnlyDataSourceLookupKeys.size) {
            index = -1
        }
        index += 1
        return readOnlyDataSourceLookupKeys[index]
    }
}