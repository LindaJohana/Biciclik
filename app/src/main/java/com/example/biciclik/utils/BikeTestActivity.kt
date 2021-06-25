package com.example.biciclik.utils

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.biciclik.R
import com.omni.support.ble.BleModuleHelper
import com.omni.support.ble.core.*
import com.omni.support.ble.protocol.bike.model.BLInfoResult
import com.omni.support.ble.protocol.bike.model.BLLockResult
import com.omni.support.ble.protocol.bike.model.BLShutdownResult
import com.omni.support.ble.rover.CommandManager
import com.omni.support.ble.session.SimpleSessionListener
import com.omni.support.ble.session.sub.Bike3In1Session

class BikeTestActivity : AppCompatActivity() {
    private lateinit var session: Bike3In1Session
    private lateinit var btn_connect: Button
    private lateinit var btn_disconnect: Button
    private lateinit var btn_unlock: Button
    private lateinit var btn_info: Button
    private lateinit var btn_shutdown: Button
    private lateinit var btn_get_log: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bike)
        BleModuleHelper.init(this.application)
        session = Bike3In1Session.Builder()
            .address("E3:1D:F1:0F:33:5B")//Replace your mac address
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
                        override fun onSuccess(
                            call: ISessionCall<BLLockResult>,
                            data: IResp<BLLockResult>
                        ) {
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
        btn_connect = findViewById<Button>(R.id.btn_connect)
        btn_disconnect = findViewById<Button>(R.id.btn_disconnect)
        btn_unlock = findViewById<Button>(R.id.btn_unlock)
        btn_info = findViewById<Button>(R.id.btn_info)
        btn_shutdown = findViewById<Button>(R.id.btn_shutdown)
        btn_get_log = findViewById<Button>(R.id.btn_get_log)
        initListener()
    }

    fun initListener() {

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
            session.call(CommandManager.blCommand.unlock(0, System.currentTimeMillis() / 1000, 0))
                .timeout(3000)
                .enqueue(object : SessionCallback<Boolean> {
                    override fun onSuccess(
                        call: ISessionCall<Boolean>, data:
                        IResp<Boolean>
                    ) {
                        val isSuccess = data.getResult() ?: false
                        Toast.makeText(
                            this@BikeTestActivity, if (isSuccess)
                                "Successfully unlocked" else "Failed to unlock", Toast.LENGTH_SHORT
                        )
                            .show()
                        // Unlock reply
                        session.call(CommandManager.blCommand.unlockReply()).execute()

                    }

                    override fun onFailure(call: ISessionCall<Boolean>, e: Throwable) {

                        Toast.makeText(
                            this@BikeTestActivity, "", Toast.LENGTH_SHORT
                        )

                    }
                })

        }
        //Get lock information
        btn_info.setOnClickListener {
            session.call(CommandManager.blCommand.getLockInfo())
                .enqueue(object : SessionCallback<BLInfoResult> {
                    override fun onSuccess(
                        call: ISessionCall<BLInfoResult>,
                        data: IResp<BLInfoResult>
                    ) {
                        val result = data.getResult()
                        if (result != null) {
                            Log.d("=====", result.toString())
                        }
                    }

                    override fun onFailure(
                        call: ISessionCall<BLInfoResult>, e:
                        Throwable
                    ) {

                    }
                })
        }

        //Shutdown
        btn_shutdown.setOnClickListener {
            session.call(CommandManager.blCommand.shutdown())
                .enqueue(object : SessionCallback<BLShutdownResult> {
                    override fun onSuccess(
                        call: ISessionCall<BLShutdownResult>,
                        data: IResp<BLShutdownResult>
                    ) {
                        val result = data.getResult()
                        if (result != null) {
                            Log.d("=====", result.toString())
                        }
                    }
                    override fun onFailure(
                        call: ISessionCall<BLShutdownResult>,
                        e: Throwable
                    ) {

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

                    override fun onReceiving(call: ISessionCall<String>, data: IResp<String>) {

                        Log.d("=====", "${data.getResult()}")
                    }
                    override fun onReceived() {

                    }
                })
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        session.disConnect()
    }
}
