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

import android.content.Context
import android.graphics.Canvas
import android.view.View
import kotlin.random.Random
import java.util.*

class ContinentMap(context: Context?) : View(context) {
    private var map: Array<Cell?>
    private var boardSize: Int
    private var maxHeight = 0
    private val minHeight = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val size = if (width > height) height else width
        setMeasuredDimension(size, size)
    }

    private inner class Cell {
        var height = 0
        var flowsNW = false
        var flowsSE = false
        var basin = false
        var processing = false
    }

    private fun getMap(x: Int, y: Int): Cell? {
        return if (x >= 0 && x < boardSize && y >= 0 && y < boardSize) map[x + boardSize * y] else null
    }

    fun clearContinentalDivide() {
        for (i in 0 until boardSize * boardSize) {
            map[i]!!.flowsNW = false
            map[i]!!.flowsSE = false
            map[i]!!.basin = false
            map[i]!!.processing = false
        }
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        /**
         *
         * YOUR CODE GOES HERE
         *
         */
    }

    fun buildUpContinentalDivide(oneStep: Boolean) {
        if (!oneStep) clearContinentalDivide()
        var iterated = false
        for (x in 0 until boardSize) {
            for (y in 0 until boardSize) {
                val cell = getMap(x, y)
                if (x == 0 || y == 0 || x == boardSize - 1 || y == boardSize - 1) {
                    buildUpContinentalDivideRecursively(
                            x, y, x == 0 || y == 0, x == boardSize - 1 || y == boardSize - 1, -1)
                    if (oneStep) {
                        iterated = true
                        break
                    }
                }
            }
            if (iterated && oneStep) break
        }
        invalidate()
    }

    private fun buildUpContinentalDivideRecursively(
            x: Int, y: Int, flowsNW: Boolean, flowsSE: Boolean, previousHeight: Int) {
        /**
         *
         * YOUR CODE GOES HERE
         *
         */
    }

    fun buildDownContinentalDivide(oneStep: Boolean) {
        if (!oneStep) clearContinentalDivide()
        while (true) {
            var maxUnprocessedX = -1
            var maxUnprocessedY = -1
            var foundMaxHeight = -1
            for (y in 0 until boardSize) {
                for (x in 0 until boardSize) {
                    val cell = getMap(x, y)
                    if (!(cell!!.flowsNW || cell.flowsSE || cell.basin) && cell.height > foundMaxHeight) {
                        maxUnprocessedX = x
                        maxUnprocessedY = y
                        foundMaxHeight = cell.height
                    }
                }
            }
            if (maxUnprocessedX != -1) {
                buildDownContinentalDivideRecursively(maxUnprocessedX, maxUnprocessedY, foundMaxHeight + 1)
                if (oneStep) {
                    break
                }
            } else {
                break
            }
        }
        invalidate()
    }

    private fun buildDownContinentalDivideRecursively(x: Int, y: Int, previousHeight: Int): Cell {
        /**
         *
         * YOUR CODE GOES HERE
         *
         */
        return Cell()
    }

    fun generateTerrain(detail: Int) {
        val newBoardSize = (Math.pow(2.0, detail.toDouble()) + 1).toInt()
        if (newBoardSize != boardSize * boardSize) {
            boardSize = newBoardSize
            map = arrayOfNulls(boardSize * boardSize)
            for (i in 0 until boardSize * boardSize) {
                map[i] = Cell()
            }
        }
        /**
         *
         * YOUR CODE GOES HERE
         *
         */
        invalidate()
    }

    companion object {
        const val MAX_HEIGHT = 255
        private val DEFAULT_MAP = arrayOf(
                1, 2, 3, 4, 5,
                2, 3, 4, 5, 6,
                3, 4, 5, 3, 1,
                6, 7, 3, 4, 5,
                5, 1, 2, 3, 4)
    }

    init {
        boardSize = Math.sqrt(Companion.DEFAULT_MAP.size.toDouble()).toInt()
        map = arrayOfNulls(boardSize * boardSize)
        for (i in 0 until boardSize * boardSize) {
            map[i] = Cell()
            map[i]!!.height = Companion.DEFAULT_MAP[i]
        }
        maxHeight = Collections.max(Arrays.asList(*Companion.DEFAULT_MAP))
    }
}