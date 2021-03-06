package com.colombiagames.biciclick.utils

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.blikoon.qrcodescanner.QrCodeActivity
import com.colombiagames.biciclick.BaseContext.BaseContext
import com.colombiagames.biciclick.Login.LoginActivities
import com.colombiagames.biciclick.R
import com.colombiagames.biciclick.TakeBici.TakeBici2Fragment
import com.colombiagames.biciclick.TakeBici.TakeBiciInterfaces
import com.colombiagames.biciclick.TakeBici.TakeBiciPresenters
import com.colombiagames.biciclick.local_data.LocalData
import com.colombiagames.biciclick.objects.BikeData
import com.omni.support.ble.BleModuleHelper
import com.omni.support.ble.core.IResp
import com.omni.support.ble.core.ISessionCall
import com.omni.support.ble.core.NotifyCallback
import com.omni.support.ble.core.SessionCallback
import com.omni.support.ble.protocol.bike.model.BLLockResult
import com.omni.support.ble.rover.CommandManager
import com.omni.support.ble.session.SimpleSessionListener
import com.omni.support.ble.session.sub.Bike3In1Session

class BikeTestActivity : Fragment(), TakeBiciInterfaces.activities {
    private lateinit var session: Bike3In1Session
    private lateinit var btn_leerQr: Button
    private lateinit var txt1: TextView
    private lateinit var txt2: TextView
    private lateinit var txt3: TextView
    var REQUEST_CODE_QR=20
    var transaction: FragmentTransaction? = null
    lateinit var fragmentTrip1: TakeBici2Fragment
    lateinit var presenter: TakeBiciPresenters
    lateinit var localData: LocalData

    //val permiso= arrayOf("android.permission.BLUETOOTH", "android.permission.BLUETOOTH")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.activity_bike, container, false)

        this.activity?.let { BleModuleHelper.init(it.application) }
        val permissions = arrayOf("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_PHONE_STATE", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.CAMERA", "android.permission.BLUETOOTH", "android.permission.BLUETOOTH_ADMIN")
        val requestCode = 200

        //type text verdana
        txt1=view.findViewById<TextView>(R.id.txt1)
        txt2=view.findViewById<TextView>(R.id.txt2)
        txt3=view.findViewById<TextView>(R.id.txt3)
        txt1.setTypeface(Typeface.createFromAsset(txt1.context.assets, "fonts/verdana.ttf"))
        txt2.setTypeface(Typeface.createFromAsset(txt2.context.assets, "fonts/verdana.ttf"))
        txt3.setTypeface(Typeface.createFromAsset(txt3.context.assets, "fonts/verdana.ttf"))


        val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }
        if (bluetoothAdapter?.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, requestCode)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
        localData= LocalData();
        btn_leerQr=view.findViewById<Button>(R.id.buttonQR)
        btn_leerQr.setTypeface(Typeface.createFromAsset(btn_leerQr.context.assets, "fonts/verdana.ttf"))
        fragmentTrip1 = TakeBici2Fragment()
        //initListener()
        btn_leerQr.setOnClickListener {
            leerQR()
        }
//        buttonShutdown.setOnClickListener { shutdown() }
        presenter = TakeBiciPresenters(this, null)

        if (!localData.getRegister("START_POINT").isEmpty()){
            mostrarFragment(localData.getRegister("START_DATE"),localData.getRegister("START_POINT"), localData.getRegister("CHRONOMETER_S"))
        }
        return view
    }

    fun leerQR() {
        val i = Intent(context, QrCodeActivity::class.java)
        startActivityForResult(i, REQUEST_CODE_QR)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(context, "No se pudo obtener una respuesta", Toast.LENGTH_SHORT).show()
            if (data == null) {
            } else {
                val resultado = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image")
                if (resultado != null) {
                    Toast.makeText(context, "No se pudo escanear el c??digo QR", Toast.LENGTH_SHORT).show()
                }
                return
            }
//            session.disConnect()
        }
        if (requestCode == REQUEST_CODE_QR) {
            if (data != null) {
                val lectura = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult")
                Toast.makeText(context, "Le??do: $lectura", Toast.LENGTH_SHORT).show()
//                if(lectura=="66155000612"){
//                    Log.e("LEYO EL CODIGO", lectura)
//                }
                if (lectura != null) {

                }
                presenter.sendCodPresenter(lectura)
            }
        }
    }
    fun unlock(){

        session.call(CommandManager.blCommand.unlock(0, System.currentTimeMillis() / 1000, 0))
                .timeout(3000)
                .enqueue(object : SessionCallback<Boolean> {
                    override fun onSuccess(
                            call: ISessionCall<Boolean>, data:
                            IResp<Boolean>
                    ) {
                        val isSuccess = data.getResult() ?: false
                        if(isSuccess) {
                            session.disConnect()
                            //peticion crear-mostrar fragment
                            presenter.createTripPresenter()
                        }
                        Toast.makeText(context, if (isSuccess)
                            "Successfully unlocked" else "Failed to unlock", Toast.LENGTH_SHORT
                        )
                                .show()
                        // Unlock reply
                        session.call(CommandManager.blCommand.unlockReply()).execute()

                    }

                    override fun onFailure(call: ISessionCall<Boolean>, e: Throwable) {

                        Toast.makeText(
                                context, e.toString(), Toast.LENGTH_SHORT
                        )

                    }
                })
    }
//    fun shutdown(){
//        session.call(CommandManager.blCommand.shutdown())
//            .enqueue(object : SessionCallback<BLShutdownResult> {
//                override fun onSuccess(
//                    call: ISessionCall<BLShutdownResult>,
//                    data: IResp<BLShutdownResult>
//                ) {
//                    val result = data.getResult()
//                    if (result != null) {
//                        Log.d("=====", result.toString())
//                    }
//                }
//                override fun onFailure(
//                    call: ISessionCall<BLShutdownResult>,
//                    e: Throwable
//                ) {
//
//                }
//            })
//    }


    override fun onDestroy() {
        super.onDestroy()
        try{
            if (session!=null){
                if (session.isConnect() == true){
                    session.disConnect()
                }
            }
        }catch (exception:Exception){
        }
    }

    override fun sesionCod(data: BikeData) {
        session = Bike3In1Session.Builder()
                //"E3:1D:F1:0F:33:5B"
            .address(data.getMac_lock())//Replace your mac address
            .deviceKey("yOTmK50z")
            .deviceType("A1")
            .updateKey("Vgz7")
            .build()
        session.connect()
        //mientras pruebas
//        presenter.createTripPresenter()

        session.setListener(object : SimpleSessionListener() {
            override fun onConnecting() {
                Log.e("conectando", "conectando")
            }

            override fun onConnected() {
                Log.e("conecto", "conecto")
                Handler().postDelayed({
                    unlock()
                }, 1000)
//                unlock()
            }

            override fun onDisconnected() {
                Log.e("No conecto", "No conecto")
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
    }

    override fun setErrorCod(message: String?) {
        Toast.makeText(BaseContext.getContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun setErrorTrip(message: String?) {
        Toast.makeText(BaseContext.getContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun mostrarFragment(date:String, point:String, time:String) {
        btn_leerQr.setEnabled(false)
//        btn_leerQr.setBackgroundColor(-0x777778)
        val TAG:String = "MyActivity"
        try {
            var bundle:Bundle = Bundle()
            bundle.putString("start_point", point)
            bundle.putString("start_date", date)
            bundle.putString("chronometer", localData.getRegister("CHRONOMETER_S"))
            localData.register(point,"START_POINT")
            localData.register(date, "START_DATE")

            fragmentTrip1.arguments=bundle
            transaction = childFragmentManager.beginTransaction()
            transaction!!.replace(R.id.contenedorFragmentTrip, fragmentTrip1,null)
            transaction!!.addToBackStack(null)
            transaction!!.commit()
        } catch (excepcion: Exception) {
            Log.e(TAG, "error")
        }
//        fragmentTrip1.setData(data)
    }

    override fun lanzarLogin() {
        Toast.makeText(BaseContext.getContext(), "Su sesio ha expirado, inicie sesion de nuevo", Toast.LENGTH_LONG).show()
        val i = Intent(BaseContext.getContext(), LoginActivities::class.java)
        startActivity(i)
    }
}
