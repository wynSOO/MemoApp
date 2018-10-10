package com.wyn32.my_memo_app.database;

import android.os.SystemClock;

import com.wyn32.my_memo_app.database.util.MemoUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemoVO {

    private String memoKey;
    private String memoCat;
    private String memoTitle;
    private String memoInDate;
    private String memoYetDate;
    private String memoFinishDate;
    private String memoText;

    public MemoVO() {
    }


    public MemoVO(String memoKey, String memoCat, String memoTitle, String memoInDate, String memoYetDate, String memoFinishDate, String memoText) {
        this.memoKey = memoKey;
        this.memoCat = memoCat;
        this.memoTitle = memoTitle;
        this.memoInDate = memoInDate;
        this.memoYetDate = memoYetDate;
        this.memoFinishDate = memoFinishDate;
        this.memoText = memoText;
    }

    // memoText만으로 객체를 생성해서
    // 제목, 입력일자를 자동으로 생성하기
    public MemoVO(String memoText){

        // 현재 시각과 메모의 제목을 자동으로 세팅
        String curDateTime = MemoUtils.getCurrentDate();

        this.memoInDate = curDateTime;
        this.memoTitle = curDateTime + " 에 만든 메모";
        this.memoText = memoText;


    }

    public String getMemoKey() {
        return memoKey;
    }

    public void setMemoKey(String memoKey) {
        this.memoKey = memoKey;
    }

    public String getMemoCat() {
        return memoCat;
    }

    public void setMemoCat(String memoCat) {
        this.memoCat = memoCat;
    }

    public String getMemoTitle() {
        return memoTitle;
    }

    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }

    public String getMemoInDate() {
        return memoInDate;
    }

    public void setMemoInDate(String memoInDate) {
        this.memoInDate = memoInDate;
    }

    public String getMemoYetDate() {
        return memoYetDate;
    }

    public void setMemoYetDate(String memoYetDate) {
        this.memoYetDate = memoYetDate;
    }

    public String getMemoFinishDate() {
        return memoFinishDate;
    }

    public void setMemoFinishDate(String memoFinishDate) {
        this.memoFinishDate = memoFinishDate;
    }

    public String getMemoText() {
        return memoText;
    }

    public void setMemoText(String memoText) {
        this.memoText = memoText;
    }

    @Override
    public String toString() {
        return "MemoVO{" +
                "memoTitle='" + memoTitle + '\'' +
                ", memoInDate='" + memoInDate + '\'' +
                ", memoYetDate='" + memoYetDate + '\'' +
                ", memoFinishDate='" + memoFinishDate + '\'' +
                ", memoText='" + memoText + '\'' +
                '}';
    }

}
