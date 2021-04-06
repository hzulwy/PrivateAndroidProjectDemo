// ILeoAidl.aidl
package com.xx.leo_service;

// Declare any non-default types here with import statements
import com.xx.leo_service.Person;//aidl的类名必须手动导入，无论是否在同一目录下，否则会找不到该文件

interface ILeoAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void addPerson (in Person person);
    List<Person> getPersonList();
}
