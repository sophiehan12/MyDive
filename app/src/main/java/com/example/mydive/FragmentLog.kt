package com.example.mydive

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_log.*
import kotlinx.android.synthetic.main.fragment_log.view.*
import java.io.FileInputStream
import java.io.IOException
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLog.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentLog : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //
    lateinit var fileName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var cal = Calendar.getInstance()
        var cYear = cal.get(Calendar.YEAR)
        var cMonth = cal.get(Calendar.MONTH)
        var cDay = cal.get(Calendar.DAY_OF_MONTH)


        fileName = (Integer.toString(cYear) + "_" + Integer.toString(cMonth+1)+ "_" + Integer.toString(cDay)+".txt")
        var str = readDiary(fileName)
        edtDiary.setText(str)

        view.datePicker.init(cYear, cMonth, cDay){ view, year, monthOfYear, dayOfMonth->
            fileName = (Integer.toString(year) + "_" + Integer.toString(monthOfYear+1)+ "_" + Integer.toString(dayOfMonth)+".txt")

            var str = readDiary(fileName)
            edtDiary.setText(str)
            btnWrite.isEnabled = true
        }
        view.btnWrite.setOnClickListener {
            //ㅍ일쓰기
            var outFs = requireActivity().openFileOutput(fileName, Context.MODE_PRIVATE)
            var str = view.edtDiary.text.toString()
            outFs.write(str.toByteArray())
            outFs.close()
            Toast.makeText(activity, "$fileName 이 저장됨",Toast.LENGTH_SHORT).show()
        }
    }
    fun readDiary(fName: String):String?{
        var diaryStr : String? =null
        var inFs: FileInputStream
        try {
            inFs = requireActivity().openFileInput(fName)
            var txt = ByteArray(500)
            inFs?.read(txt)
            inFs?.close()
            diaryStr = txt.toString(Charsets.UTF_8).trim()
            view?.btnWrite?.text="수정하기"
        }catch (e:IOException){
            view?.edtDiary?.hint = "일기 없음"
            view?.btnWrite?.text = "새로 저장"
        }
        return diaryStr
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentLog.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentLog().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}