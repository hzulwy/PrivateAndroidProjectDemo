package com.example.client

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.xx.leo_service.ILeoAidl
import com.xx.leo_service.Person

class MainActivity : AppCompatActivity() {

    //注意：客户端和服务端的aidl文件夹下面的每个文件名、路径及其内容都必须一模一样

    companion object{
        const val TAG:String = "MainActivity"
    }

    private var iLeoAidl:ILeoAidl? = null
    private lateinit var btn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        bindService()
    }

    private fun initView() {
        btn = findViewById(R.id.but_click)
        btn.setOnClickListener {
            try {
                iLeoAidl?.addPerson(Person("brett", 3))
                var persons = iLeoAidl?.getPersonList()
                //只要客户端进程没有被杀死,无论服务端进程有没被杀死，获得的数据都是从上一次开始叠加
                //例如：如果用户点击了3次按钮，然后杀掉服务端进程，在点击按钮，数据会有4条。
                Log.e(TAG,persons.toString())
            }catch (e:RemoteException){
                e.printStackTrace()
            }
        }

    }

    private fun bindService() {
        val intent = Intent()
        intent.component = ComponentName("com.xx.leo_service", "com.xx.leo_service.LeoAidlService")
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.e(TAG, "onServiceConnected: success")
            iLeoAidl = ILeoAidl.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            Log.e(TAG, "onServiceDisconnected: success")
            iLeoAidl = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }
}