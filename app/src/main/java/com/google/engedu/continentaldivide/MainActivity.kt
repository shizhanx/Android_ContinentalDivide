/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.engedu.continentaldivide

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var continentMap: ContinentMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val container = findViewById<View>(R.id.vertical_layout) as LinearLayout
        // Create the map and insert it into the view.
        continentMap = ContinentMap(this)
        continentMap!!.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        container.addView(continentMap, 0)
    }

    fun onGenerateTerrain(view: View?): Boolean {
        continentMap!!.generateTerrain(3)
        return true
    }

    fun onFindContinentalDivideDown(view: View?): Boolean {
        continentMap!!.buildDownContinentalDivide(false)
        return true
    }

    fun onFindContinentalDivideUp(view: View?): Boolean {
        continentMap!!.buildUpContinentalDivide(false)
        return true
    }

    fun onClearContinentalDivide(view: View?): Boolean {
        continentMap!!.clearContinentalDivide()
        return true
    }
}