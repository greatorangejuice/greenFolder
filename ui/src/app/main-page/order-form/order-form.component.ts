import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss']
})
export class OrderFormComponent implements OnInit {
  currentDate: Date;
  maxDate = new Date();
  form: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {

    this.currentDate = new Date();
    this.maxDate.setDate(this.maxDate.getDate() + 90);

    // localStorage.getItem()
    this.form = this.formBuilder.group({
      name: ['', [Validators.required]],
      surname: ['', [Validators.required]],
      university: ['', [Validators.required]],
      course: ['', [Validators.required]],
      universityOnly: [true, [Validators.required]],
      deadline: ['', [Validators.required]],
      price: [''],
    }
    );
  }

}
