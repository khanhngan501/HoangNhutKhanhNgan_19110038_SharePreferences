package hcmute.edu.vn.mssv19110038.hoangnhutkhanhngan_19110038_sharepreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnXacNhan;
    EditText edtUsername, edtPassword;
    CheckBox cbRemember;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

//        Lấy giá trị sharedPreferences
        edtUsername.setText(sharedPreferences.getString("taikhoan", ""));
        edtPassword.setText(sharedPreferences.getString("matkhau", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked", false));

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if(username.equals("khanhngan") && password.equals("1234"))
                {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
//                    Nếu có check
                    if(cbRemember.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", username);
                        editor.putString("matkhau", password);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    }
                    else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("checked");
                        editor.commit();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AnhXa(){
        btnXacNhan = (Button) findViewById(R.id.buttonXacNhan);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
        edtUsername = (EditText) findViewById(R.id.editTextUsername);
        cbRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
    }
}