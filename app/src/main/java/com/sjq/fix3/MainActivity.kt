package com.sjq.fix3

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var people = People()

        // Example of a call to a native method
        sample_text.text = stringFromJNI()

        bt_use_error.setOnClickListener {
            var caculator = Caculator()
            sample_text.text = caculator.caculator().toString()
        }
        bt_fix_error.setOnClickListener {
            PermissionTool.getInstance().chekPermissions(this, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ), object : PermissionTool.IPermissionsResult {
                override fun passPermissons() {
                    fix()
                }

                override fun forbitPermissons() {
                    Toast.makeText(
                        this@MainActivity,
                        "reject perssion apply",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
        }

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

    fun fix() {

        var file = File(Environment.getExternalStorageDirectory().absolutePath + "/fix.dex")
        var dexManager = DexManager(this@MainActivity)
        dexManager.loadFile(file)

    }
}
