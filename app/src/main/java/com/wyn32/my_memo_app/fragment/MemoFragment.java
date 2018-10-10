package com.wyn32.my_memo_app.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wyn32.my_memo_app.R;
import com.wyn32.my_memo_app.database.MemoVO;

public class MemoFragment extends Fragment {

    TextView txt_memo;
    Button btn_write_ok;
    Spinner cats;
    String strCat;

    FirebaseDatabase firebaseDatabase ;

    public MemoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.memo_fragment,container,false);

        firebaseDatabase = FirebaseDatabase.getInstance();

        // 만약 카테고리를 선택하지 않으면 기본 카테고리로 '일상메모'를 선택한다.
        strCat = "일상메모";

        txt_memo = view.findViewById(R.id.memo_text);
        btn_write_ok = view.findViewById(R.id.memo_write_ok);
        btn_write_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strMemo = txt_memo.getText().toString();

                MemoVO vo = new MemoVO(strMemo);
                DatabaseReference databaseReference = firebaseDatabase.getReference("my_memo");

                // 새로운 PK를 하나 달라
                String pkey = databaseReference.push().getKey();

                // pk : key를 VO에 할당한다.
                vo.setMemoKey(pkey);
                vo.setMemoCat(strCat);
                databaseReference.child(pkey).setValue(vo);

                txt_memo.setText("");
                cats.setSelection(0);// 카테고리 제목으로 환원

                Toast.makeText(getContext(),"데이터가 저장되었습니다.",Toast.LENGTH_SHORT).show();

            }
        }); //btn


        cats = view.findViewById(R.id.memo_cat);
        cats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCat = parent.getItemAtPosition(position).toString();
                if(strCat.equals("카테고리")) strCat = "일상메모";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
}
