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
  Calendar mCalendar = Calendar.getInstance();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mBinder = ButterKnife.bind(this);
    addControls();
    addEvent();
  }

  private void addControls() {
    tvDate.setText(DateTimeUtil.getStringDate(mCalendar));
    tvTime.setText(DateTimeUtil.getStringTime(mCalendar));
    tvDateTime.setText(DateTimeUtil.getStringDate(mCalendar) + " " + DateTimeUtil.getStringTime(mCalendar));
  }

  private void addEvent() {
    imgDatePicker.setOnClickListener(this);
    imgTimePicker.setOnClickListener(this);
    imgDateTimePicker.setOnClickListener(this);
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
    final Calendar calendar = DateTimeUtil.calendarSetTime(tvDate.getText() + " " + tvTime.getText());
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);
    DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        calendar.set(year, month, day);
        tvDate.setText(DateTimeUtil.getStringDate(calendar));
      }
    }, year, month, day);

    datePickerDialog.show();
  }

  private void showTimePicker() {
    final Calendar calendar = DateTimeUtil.calendarSetTime(tvDate.getText() + " " + tvTime.getText());
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    final int second = calendar.get(Calendar.SECOND);
    TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
      @Override
      public void onTimeSet(TimePicker timePicker, int i, int i1) {
        calendar.set(Calendar.MINUTE, i1);
        calendar.set(Calendar.HOUR_OF_DAY, i);
        tvTime.setText(DateTimeUtil.getStringTime(calendar));
      }
    }, hour, minute, true);

    timePickerDialog.show();
  }

  private void showDateAndTimePicker() {
    final Calendar calendar = DateTimeUtil.calendarSetTime(tvDateTime.getText().toString());
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
            calendar.set(year, month, day, hour, minute, second);
            tvDateTime.setText(DateTimeUtil.getStringDate(calendar) + " " + DateTimeUtil.getStringTime(calendar));
          }
        }, hour, minute, true);
        timePickerDialog.show();
      }
    }, year, month, day);

    datePickerDialog.show();
  }

}
