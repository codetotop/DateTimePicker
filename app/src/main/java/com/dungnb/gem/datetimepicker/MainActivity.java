package com.dungnb.gem.datetimepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("SetTextI18n")

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  @BindView(R.id.tvDate)
  TextView tvDate;
  @BindView(R.id.tvTime)
  TextView tvTime;
  @BindView(R.id.tvDateTime)
  TextView tvDateTime;
  @BindView(R.id.imgDatePicker)
  ImageView imgDatePicker;
  @BindView(R.id.imgTimePicker)
  ImageView imgTimePicker;
  @BindView(R.id.imgDateTimePicker)
  ImageView imgDateTimePicker;
  Unbinder mBinder;
  Calendar calendar = Calendar.getInstance();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mBinder = ButterKnife.bind(this);
    addControls();
    addEvent();
  }

  private void addControls() {
    tvDate.setText(getStringDateCurrent());
    tvTime.setText(getStringTimeCurrent());
    tvDateTime.setText(getStringDateCurrent() + " " + getStringTimeCurrent());
  }

  private void addEvent() {
    imgDatePicker.setOnClickListener(this);
    imgTimePicker.setOnClickListener(this);
    imgDateTimePicker.setOnClickListener(this);
  }

  public String getStringDateCurrent() {
    String date_current = "";
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    date_current = day + "-" + month + "-" + year;
    return date_current;
  }

  public String getStringTimeCurrent() {
    String time_current = "";
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    int second = calendar.get(Calendar.SECOND);

    time_current = hour + ":" + minute + ":" + second;
    return time_current;
  }

  @Override
  protected void onDestroy() {
    mBinder.unbind();
    super.onDestroy();
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.imgDatePicker:
        showDatePicker();
        break;
      case R.id.imgTimePicker:
        showTimePicker();
        break;
      case R.id.imgDateTimePicker:
        showDateAndTimePicker();
      default:
        break;
    }
  }

  private void showDatePicker() {
    Calendar calendar = DateTimeUtil.calendarSetTime(tvDate.getText() + " " + tvTime.getText());
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);
    DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String date = day + "-" + (month + 1) + "-" + year;
        tvDate.setText(date);
      }
    }, year, month, day);

    datePickerDialog.show();
  }

  private void showTimePicker() {
    Calendar calendar = DateTimeUtil.calendarSetTime(tvDate.getText() + " " + tvTime.getText());
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    final int second = calendar.get(Calendar.SECOND);
    TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
      @Override
      public void onTimeSet(TimePicker timePicker, int i, int i1) {
        String time = i + ":" + i1 + ":" + second;
        tvTime.setText(time);
      }
    }, hour, minute, true);

    timePickerDialog.show();
  }

  private void showDateAndTimePicker() {
    Calendar calendar = DateTimeUtil.calendarSetTime(tvDateTime.getText().toString());
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);
    final int hour = calendar.get(Calendar.HOUR_OF_DAY);
    final int minute = calendar.get(Calendar.MINUTE);
    final int second = calendar.get(Calendar.SECOND);
    DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker datePicker, final int year, final int month, final int day) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
          @Override
          public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            tvDateTime.setText(day + "-" + month + "-" + year + " " + hour + ":" + minute + ":" + second);
          }
        }, hour, minute, true);
        timePickerDialog.show();
      }
    }, year, month, day);

    datePickerDialog.show();
  }

}
