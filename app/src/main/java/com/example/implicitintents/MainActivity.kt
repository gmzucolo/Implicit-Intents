package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.android.implicitintents.R

import androidx.core.app.ShareCompat


class MainActivity : AppCompatActivity() {

    private var mWebsiteEditText: EditText? = null
    private var mLocationEditText: EditText? = null
    private var mShareTextEditText: EditText? = null
    private var mWebButton: Button? = null
    private var mLocationButton: Button? = null
    private var mShareButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        listeners()

    }

    private fun findViews() {
        mWebsiteEditText = findViewById(R.id.website_edittext)
        mLocationEditText = findViewById(R.id.location_edittext)
        mShareTextEditText = findViewById(R.id.share_edittext)
        mWebButton = findViewById(R.id.open_website_button)
        mLocationButton = findViewById(R.id.open_location_button)
        mShareButton = findViewById(R.id.share_text_button)
    }

    private fun listeners() {
        mWebButton?.setOnClickListener { openWebsite() }
        mLocationButton?.setOnClickListener { openLocation() }
        mShareButton?.setOnClickListener { shareText() }
    }

    private fun openWebsite() {
        val url = mWebsiteEditText!!.text.toString()
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        if (intent != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this!")
        }
    }

    private fun openLocation() {
        val loc = mLocationEditText!!.text.toString()
        val addressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, addressUri)

        if (intent != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!")
        }
    }

    private fun shareText() {
        val txt = mShareTextEditText!!.text.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle(R.string.edittext_share)
            .setText(txt)
            .startChooser()
    }
}