package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPwd: EditText
    private lateinit var btnSubmit: Button
    private lateinit var rgSex: RadioGroup
    private lateinit var cbSing: CheckBox
    private lateinit var cbDance: CheckBox
    private lateinit var cbRead: CheckBox

    private var name = ""
    private var email = ""
    private var pwd = ""
    private var sex = ""
    private var hobbies = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        etPwd = findViewById(R.id.et_pwd)
        rgSex = findViewById(R.id.rg_sex)
        cbSing = findViewById(R.id.cb_sing)
        cbDance = findViewById(R.id.cb_dance)
        cbRead = findViewById(R.id.cb_read)
        btnSubmit = findViewById(R.id.btn_submit)

        btnSubmit.setOnClickListener(this)

        cbSing.setOnCheckedChangeListener(this)
        cbDance.setOnCheckedChangeListener(this)
        cbRead.setOnCheckedChangeListener(this)

        rgSex.setOnCheckedChangeListener { _, checkedId ->
            sex = when (checkedId) {
                R.id.rb_boy -> getString(R.string.sex_boy)
                R.id.rb_girl -> getString(R.string.sex_girl)
                else -> ""
            }
        }
    }
    private fun getData() {
        name = etName.text.toString().trim()
        email = etEmail.text.toString().trim()
        pwd = etPwd.text.toString().trim()
    }

    override fun onClick(v: View?) {
        if (v?.id != R.id.btn_submit) {
            return
        }

        getData()
        when {
            TextUtils.isEmpty(name) -> toast(R.string.toast_name_required)
            TextUtils.isEmpty(email) -> toast(R.string.toast_email_required)
            TextUtils.isEmpty(pwd) -> toast(R.string.toast_pwd_required)
            TextUtils.isEmpty(sex) -> toast(R.string.toast_sex_required)
            TextUtils.isEmpty(hobbies) -> toast(R.string.toast_hobby_required)
            else -> {
                Toast.makeText(this, R.string.toast_register_success, Toast.LENGTH_SHORT).show()
                Log.i(
                    "MainActivity",
                    "注册的用户信息：姓名：$name，邮箱：$email，性别：$sex，兴趣爱好：$hobbies"
                )
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        val hobby = buttonView.text?.toString().orEmpty()
        if (hobby.isEmpty()) {
            return
        }

        hobbies = if (isChecked) {
            if (hobbies.contains(hobby)) hobbies else hobbies + hobby
        } else {
            hobbies.replace(hobby, "")
        }
    }

    private fun toast(messageRes: Int) {
        Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
    }
}
