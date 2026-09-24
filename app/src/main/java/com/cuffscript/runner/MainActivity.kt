package com.cuffscript.runner

import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {

    private lateinit var output: TextView

    private val picker = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        if (uri != null) runScript(uri) else finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        output = TextView(this)
        output.setBackgroundColor(0xFF000000.toInt())
        output.setTextColor(0xFFFFFFFF.toInt())
        output.typeface = Typeface.MONOSPACE
        output.setPadding(24, 24, 24, 24)
        output.gravity = Gravity.TOP or Gravity.START
        output.movementMethod = ScrollingMovementMethod()
        setContentView(output)
        picker.launch(arrayOf("*/*"))
    }

    private fun runScript(uri: Uri) {
        output.text = runCuff(readText(uri))
    }

    private fun readText(uri: Uri): String {
        val stream = contentResolver.openInputStream(uri) ?: return ""
        val reader = BufferedReader(InputStreamReader(stream))
        val text = reader.readText()
        reader.close()
        return text
    }

    private external fun runCuff(source: String): String

    companion object {
        init {
            System.loadLibrary("cuffbridge")
        }
    }
}
