package com.example.biciclik.utils

import android.util.Log
import com.omni.support.ble.core.IResp
import com.omni.support.ble.core.ISessionCall
import com.omni.support.ble.core.NotifyCallback
import com.omni.support.ble.protocol.bike.model.BLLockResult
import com.omni.support.ble.rover.CommandManager
import com.omni.support.ble.session.SimpleSessionListener
import com.omni.support.ble.session.sub.Bike3In1Session

class BikeTestActivity : BaseActivity {


    private lateinit var session: Bike3In1Session


    override fun init() {
        session = Bike3In1Session.Builder()
                .address("C6:01:BD:63:01:70")//Replace your mac address
                .deviceKey("yOTmK50z")
                .deviceType("A1")
                .updateKey("Vgz7")
                .build()
        session.setListener(object : SimpleSessionListener() {
            override fun onConnecting() {
            }

            override fun onConnected() {
            }

            override fun onDisconnected() {
            }

            override fun onDeviceNoSupport() {
            }

            override fun onReady() {
// Lock monitor
                session.call(CommandManager.blCommand.lock())
                        .subscribe(object : NotifyCallback<BLLockResult> {
                            override fun onSuccess(call: ISessionCall<BLLockResult>,
                                                   data: IResp<BLLockResult>) {
                                val result = data.getResult()
                                if (result != null) {
                                    Log.d("=====", result.toString())
                                }
// Close lock reply
                                session.call(CommandManager.blCommand.lockReply()).execute()
                            }
                        })
            }
        })
    }
                override fun initListener() {
// connection
            btn_connect.setOnClickListener {
                session.connect()
            }
// Disconnect
            btn_disconnect.setOnClickListener {
                session.disConnect()
            }
//unlock
            btn_unlock.setOnClickListener {
                session.call(CommandManager.blCommand.unlock(0,
                        System.currentTimeMillis() / 1000, 0))
                        .timeout(3000)
                        .enqueue(object : SessionCallback<Boolean> {
                            override fun onSuccess(call: ISessionCall<Boolean>, data:
                            IResp<Boolean>) {
                                val isSuccess = data.getResult() ?: false
                                Toast.makeText(this@BikeLockTestActivity, if (isSuccess)
                                    "Successfully unlocked" else "Failed to unlock", Toast.LENGTH_SHORT)
                                        .show()
// Unlock reply
                                session.call(CommandManager.blCommand.unlockReply()).execute()
                            }

                            override fun onFailure(call: ISessionCall<Boolean>, e:
                            Throwable) {
                            }
                        })
            }
//Get lock information
            btn_info.setOnClickListener {
                session.call(CommandManager.blCommand.getLockInfo())
                        .enqueue(object : SessionCallback<BLInfoResult> {
                            override fun onSuccess(call: ISessionCall<BLInfoResult>, data:
                            IResp<BLInfoResult>) {
                                val result = data.getResult()
                                if (result != null) {
                                    Log.d("=====", result.toString())
                                }
                            }

                            override fun onFailure(call: ISessionCall<BLInfoResult>, e:
                            Throwable) {
                            }
                        })
            }
//Shutdown
            btn_shutdown.setOnClickListener {
                session.call(CommandManager.blCommand.shutdown())
                        .enqueue(object : SessionCallback<BLShutdownResult> {
                            override fun onSuccess(call: ISessionCall<BLShutdownResult>,
                                                   data: IResp<BLShutdownResult>) {
                                val result = data.getResult()
                                if (result != null) {
                                    Log.d("=====", result.toString())
                                }
                            }

                            override fun onFailure(call: ISessionCall<BLShutdownResult>, e:
                            Throwable) {
                            }
                        })
            }
//Get log
            btn_get_log.setOnClickListener {
                session.call(CommandManager.blCommand.getLog())
                        .asyncTimeout(10000)
                        .asyncCall(object : AsyncCallback<String> {
                            override fun onTimeout() {
                            }

                            override fun onStarted(success: Boolean) {
                            }

                            override fun onReceiving(call: ISessionCall<String>, data:
                            IResp<String>) {
                                Log.d("=====", "${data.getResult()}")
                            }

                            override fun onReceived() {
                            }
                        })
            }
            override fun onDestroy() {
                super.onDestroy()
                session.disConnect()
            }
        }
    }
}