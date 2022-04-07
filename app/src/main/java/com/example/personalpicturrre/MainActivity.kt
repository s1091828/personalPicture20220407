package com.example.personalpicturrre

// YT影片連結 : https://youtube.com/shorts/e-JL4CyxXHg?feature=share

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener
    , View.OnTouchListener, SensorEventListener {

    lateinit var mper: MediaPlayer
    lateinit var gDetector: GestureDetector
    var sensorManager: SensorManager? = null
    var sensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img.setOnTouchListener(this)
        gDetector = GestureDetector(this, this)

        // get reference of the service
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        // focus in accelerometer
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager?.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun rndPicture(){
        var counter = (1..7).random()
        //txv.text = counter.toString()

        if (counter == 1){
            img.setImageResource(R.drawable.p1)
            txv.text = "工作地點附近的台灣欒樹"
        }else if (counter == 2){
            img.setImageResource(R.drawable.p2)
            txv.text = "蓋夏圖書館後方"
        }else if (counter == 3){
            img.setImageResource(R.drawable.p3)
            txv.text = "曾經賣過的釋迦"
        }else if (counter == 4){
            img.setImageResource(R.drawable.p4)
            txv.text = "前往阿嬤家的路上..."
        }else if (counter == 5){
            img.setImageResource(R.drawable.p5)
            txv.text = "家裡養的虎皮鸚鵡"
        }else if (counter == 6){
            img.setImageResource(R.drawable.p6)
            txv.text = "曾經賣過的柳丁"
        }else if (counter == 7){
            img.setImageResource(R.drawable.p7)
            txv.text = "曬維生工具中..."
        }

//        when (counter) {
//            1 -> img.setImageResource(R.drawable.p1)
//            2 -> img.setImageResource(R.drawable.p2)
//            3 -> img.setImageResource(R.drawable.p3)
//            4 -> img.setImageResource(R.drawable.p4)
//            5 -> img.setImageResource(R.drawable.p5)
//            6 -> img.setImageResource(R.drawable.p6)
//            7 -> img.setImageResource(R.drawable.p7)
//        }
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN){
            img.setImageResource(R.drawable.p0)
            txv.text = "個人照片翻閱"
            mper = MediaPlayer.create(this, R.raw.paper)
            mper.start()
            mper.isLooping = true
        }
        else if  (event?.action == MotionEvent.ACTION_UP){
            rndPicture()
            mper.stop()
        }
        gDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        txv.text = "按下"
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {
        txv.text = "持續"
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        txv.text = "短按"
        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        txv.text = "拖曳"
        rndPicture()
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {
        txv.text = "長按"
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        txv.text = "快滑"
        return true
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event != null){
            val xValue = Math.abs(event.values[0]) // 加速度 - X 軸方向
            val yValue = Math.abs(event.values[1]) // 加速度 - Y 軸方向
            val zValue = Math.abs(event.values[2]) // 加速度 - Z 軸方向
            if (xValue > 20 || yValue > 20 || zValue > 20) {
                rndPicture()
                mper = MediaPlayer.create(this, R.raw.paper)
                mper.start()
                mper.isLooping = false
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

}